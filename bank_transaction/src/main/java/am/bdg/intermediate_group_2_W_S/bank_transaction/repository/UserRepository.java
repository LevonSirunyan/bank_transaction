package am.bdg.intermediate_group_2_W_S.bank_transaction.repository;

import am.bdg.intermediate_group_2_W_S.bank_transaction.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where u.contact.email=:email")
    Optional<User> findByEmail(String email);

    @Query("select u from User u where u.contact.email=?1 and pass=?2")
    Optional<User> findByEmailAndPass(String email, String pass);
}
