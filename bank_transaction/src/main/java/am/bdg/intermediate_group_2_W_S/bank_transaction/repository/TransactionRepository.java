package am.bdg.intermediate_group_2_W_S.bank_transaction.repository;

import am.bdg.intermediate_group_2_W_S.bank_transaction.entity.Transaction;
import am.bdg.intermediate_group_2_W_S.bank_transaction.enums.TransactionStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    @Query("select T from Transaction T where T.bankAccount.user.id = :userId")
    List<Transaction> findAllByUserId(Long userId);

    @Query("select SUM (T.amount) from Transaction T where T.status = 'ACCEPTED' and T.bankAccount.user.id = :userId group by T.bankAccount.user.id")
    BigDecimal calculateSumOfUserId(Long userId);

    List<Transaction> findAllByCreatedDateAndBankAccount_User_Id(LocalDate createdDate, long bankAccount_user_id);

    List<Transaction> findAllByCreatedDateAndStatusAndBankAccount_User_Id(LocalDate createdDate, TransactionStatus status, long bankAccount_user_id);
}
