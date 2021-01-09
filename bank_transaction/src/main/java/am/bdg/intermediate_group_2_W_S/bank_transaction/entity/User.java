package am.bdg.intermediate_group_2_W_S.bank_transaction.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Embedded
    private Contact contact;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Role> roles;

    @OneToMany(mappedBy = "user")
    private Set<BankAccount> bankAccounts;

    private String pass;

    @Builder
    @Embeddable
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class Contact {
        private String address;
        private String telNumber;
        @Column(unique = true)
        private String email;
    }
}
