package am.bdg.intermediate_group_2_W_S.bank_transaction.service.impl;

import am.bdg.intermediate_group_2_W_S.bank_transaction.dto.BankAccountDto;
import am.bdg.intermediate_group_2_W_S.bank_transaction.dto.RoleDto;
import am.bdg.intermediate_group_2_W_S.bank_transaction.dto.TransactionDto;
import am.bdg.intermediate_group_2_W_S.bank_transaction.dto.UserDto;
import am.bdg.intermediate_group_2_W_S.bank_transaction.entity.BankAccount;
import am.bdg.intermediate_group_2_W_S.bank_transaction.entity.Role;
import am.bdg.intermediate_group_2_W_S.bank_transaction.entity.Transaction;
import am.bdg.intermediate_group_2_W_S.bank_transaction.entity.User;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

public class Common {
    public static TransactionDto buildTransactionDtoFromTransaction(Transaction transaction) {
        BankAccount bankAccount = transaction.getBankAccount();
        return TransactionDto.builder()
                .bankAccountDto(buildBankAccountDtoFromBankAccount(bankAccount))
                .status(transaction.getStatus())
                .type(transaction.getType())
                .createdDate(transaction.getCreatedDate())
                .id(transaction.getId())
                .amount(transaction.getAmount()).build();
    }

    public static Transaction buildTransactionFromTransactionDto(TransactionDto transactionDto) {
        return Transaction.builder()
                .status(transactionDto.getStatus())
                .type(transactionDto.getType())
                .createdDate(LocalDate.now())
                .id(transactionDto.getId())
                .amount(transactionDto.getAmount()).build();
    }

    public static UserDto buildUserDtoFromUser(User user) {
        Set<Role> roles = user.getRoles();
        return UserDto.builder()
                .contactDto(UserDto.ContactDto.builder()
                        .address(user.getContact().getAddress())
                        .email(user.getContact().getEmail())
                        .telNumber(user.getContact().getTelNumber()).build())
                .id(user.getId())
                .name(user.getName())
                .pass(user.getPass())
                .roles(roles.stream().map(role -> RoleDto.builder()
                        .id(role.getId())
                        .type(role.getType()).build()).collect(Collectors.toSet()))
                .build();
    }

    public static User buildUserFromUserDto(UserDto userDto) {
        Set<RoleDto> roles = userDto.getRoles();
        return User.builder()
                .contact(User.Contact.builder()
                        .address(userDto.getContactDto().getAddress())
                        .email(userDto.getContactDto().getEmail())
                        .telNumber(userDto.getContactDto().getTelNumber()).build())
                .id(userDto.getId())
                .name(userDto.getName())
                .pass(userDto.getPass())
                .roles(roles.stream().map(role -> Role.builder()
                        .id(role.getId())
                        .type(role.getType()).build()).collect(Collectors.toSet()))
                .build();
    }


    public static BankAccountDto buildBankAccountDtoFromBankAccount(BankAccount bankAccount) {
        return BankAccountDto.builder()
                .userDto(Common.buildUserDtoFromUser(bankAccount.getUser()))
                .accountNumber(bankAccount.getAccountNumber())
                .id(bankAccount.getId()).build();
    }

    public static BankAccount buildBankAccountFromBankAccountDto(BankAccountDto bankAccountDto) {
        return BankAccount.builder()
                .user(Common.buildUserFromUserDto(bankAccountDto.getUserDto()))
                .accountNumber(bankAccountDto.getAccountNumber())
                .id(bankAccountDto.getId()).build();
    }
}
