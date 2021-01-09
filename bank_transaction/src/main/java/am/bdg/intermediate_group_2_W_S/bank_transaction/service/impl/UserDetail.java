package am.bdg.intermediate_group_2_W_S.bank_transaction.service.impl;

import am.bdg.intermediate_group_2_W_S.bank_transaction.entity.Role;
import am.bdg.intermediate_group_2_W_S.bank_transaction.entity.User;
import am.bdg.intermediate_group_2_W_S.bank_transaction.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserDetail implements UserDetailsService {

    private final UserRepository repo;

    public UserDetail(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = repo.findByEmail(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return new org.springframework.security.core.userdetails.User(user.getContact().getEmail(), user.getPass(),
                    user.getRoles().stream().map(Role::getType).collect(Collectors.toSet()));
        } else throw new UsernameNotFoundException(String.format("user by email: %s not  found", username));
    }
}
