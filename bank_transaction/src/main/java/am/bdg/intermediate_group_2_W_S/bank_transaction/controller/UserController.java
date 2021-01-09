package am.bdg.intermediate_group_2_W_S.bank_transaction.controller;

import am.bdg.intermediate_group_2_W_S.bank_transaction.dto.UserDto;
import am.bdg.intermediate_group_2_W_S.bank_transaction.enums.RoleType;
import am.bdg.intermediate_group_2_W_S.bank_transaction.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.register(userDto));
    }

    @PutMapping("/{id}/role")
    public ResponseEntity<?> addRole(@PathVariable Long id, @RequestParam RoleType roleType) {
        return ResponseEntity.ok(userService.changeRole(id, roleType));
    }
}
