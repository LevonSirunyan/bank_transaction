package am.bdg.intermediate_group_2_W_S.bank_transaction.repository;

import am.bdg.intermediate_group_2_W_S.bank_transaction.entity.Role;
import am.bdg.intermediate_group_2_W_S.bank_transaction.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findRoleByType(RoleType type);
}
