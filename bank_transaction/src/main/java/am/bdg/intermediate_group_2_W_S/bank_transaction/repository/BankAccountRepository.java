package am.bdg.intermediate_group_2_W_S.bank_transaction.repository;

import am.bdg.intermediate_group_2_W_S.bank_transaction.entity.BankAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends CrudRepository<BankAccount, Long> {
}
