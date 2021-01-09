package am.bdg.intermediate_group_2_W_S.bank_transaction.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "bankAccount")
    private Set<Transaction> transactions;

    @Column(unique = true, nullable = false)
    private String accountNumber;

    @Column(nullable = false, columnDefinition = "Decimal(18,2) default '0.00'", scale = 18, precision = 2)
    private BigDecimal balance;

    public BankAccount(User user, String accountNumber) {
        this.user = user;
        this.accountNumber = accountNumber;
    }
}
