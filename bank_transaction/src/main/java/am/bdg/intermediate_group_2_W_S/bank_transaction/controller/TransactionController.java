package am.bdg.intermediate_group_2_W_S.bank_transaction.controller;

import am.bdg.intermediate_group_2_W_S.bank_transaction.dto.TransactionDto;
import am.bdg.intermediate_group_2_W_S.bank_transaction.enums.TransactionStatus;
import am.bdg.intermediate_group_2_W_S.bank_transaction.service.TransactionService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.security.Principal;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @GetMapping("/")
    public ResponseEntity<?> getTransactions(@RequestParam("user_id") Long userId) {
        return ResponseEntity.ok(service.getAllTransactions(userId));
    }

    @GetMapping("/filter")
    public ResponseEntity<?> getTransactionsByCreatedDate(@RequestParam("user_id") Long userId,
                                                          @RequestParam
                                                          @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                                                          @RequestParam TransactionStatus status) {
        return ResponseEntity.ok(service.getTransactionByCreatedDayAndStatus(userId, date, status));
    }


    @GetMapping("/{id}/filter")
    public ResponseEntity<?> getTransactionsByCreatedDate(@PathVariable Long id,
                                                          @RequestParam
                                                          @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return ResponseEntity.ok(service.getTransactionByCreatedDay(id, date));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody TransactionDto transactionDto) {
        return ResponseEntity.ok(service.create(transactionDto));
    }

    @PatchMapping("/accept/{id}")
    public ResponseEntity<?> accept(@PathVariable Long id) {
        return ResponseEntity.ok(service.accept(id));
    }

    @PatchMapping("/cancel/{id}")
    public ResponseEntity<?> cancel(@PathVariable Long id, @ApiIgnore Principal principal) {
        return ResponseEntity.ok(service.cancel(id, principal));
    }
}
