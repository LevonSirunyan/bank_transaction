package am.bdg.intermediate_group_2_W_S.bank_transaction.service.impl;

import am.bdg.intermediate_group_2_W_S.bank_transaction.dto.BankAccountDto;
import am.bdg.intermediate_group_2_W_S.bank_transaction.entity.BankAccount;
import am.bdg.intermediate_group_2_W_S.bank_transaction.entity.User;
import am.bdg.intermediate_group_2_W_S.bank_transaction.repository.BankAccountRepository;
import am.bdg.intermediate_group_2_W_S.bank_transaction.repository.UserRepository;
import am.bdg.intermediate_group_2_W_S.bank_transaction.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class BankAccountServiceImpl implements BankAccountService {
    private final static String CONSTANT_PREFIX = "1245";
    private final BankAccountRepository bankAccountRepository;
    private final UserRepository userRepository;

    @Autowired
    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository, UserRepository userRepository) {
        this.bankAccountRepository = bankAccountRepository;
        this.userRepository = userRepository;
    }

    @Override
    public BankAccountDto create(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent()) {
            throw new EntityNotFoundException(String.format("User by id: %s not found.", userId));
        }
        User user = userOptional.get();
        String accountNumber = generateAccountNumber(user);
        BankAccount bankAccount = bankAccountRepository.save(new BankAccount(user, accountNumber));
        return Common.buildBankAccountDtoFromBankAccount(bankAccount);
    }

    private String generateAccountNumber(User user) {
        String stringFromUserId = String.format("%1$" + 8 + "s", String.valueOf(user.getId() % 100000000)).replace(' ', '0');
        String returnString = CONSTANT_PREFIX + stringFromUserId;
        Set<BankAccount> bankAccounts = user.getBankAccounts();
        if (bankAccounts.size() == 0) {
            returnString = returnString + "1001";
        } else {
            List<String> accountNumberList = new ArrayList<>();
            bankAccounts.forEach(bankAccount -> accountNumberList.add(bankAccount.getAccountNumber()));
            accountNumberList.sort(String::compareTo);
            String lastAccountNumber = accountNumberList.get(accountNumberList.size() - 1);
            returnString = returnString + (Integer.valueOf(lastAccountNumber.substring(lastAccountNumber.length() - 4)) + 1);
        }
        return returnString;
    }

}
