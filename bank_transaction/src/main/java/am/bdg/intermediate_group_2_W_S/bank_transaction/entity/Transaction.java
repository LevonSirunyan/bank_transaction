package am.bdg.intermediate_group_2_W_S.bank_transaction.entity;

import am.bdg.intermediate_group_2_W_S.bank_transaction.enums.TransactionStatus;
import am.bdg.intermediate_group_2_W_S.bank_transaction.enums.TransactionType;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @ManyToOne
    private BankAccount bankAccount;

    @Column(nullable = false)
    private LocalDate createdDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionStatus status;

    @Column(nullable = false)
    @Builder.Default
    private BigDecimal amount = BigDecimal.ZERO;//if(type=DEPOSIT)amount>0 else amount<0

}
