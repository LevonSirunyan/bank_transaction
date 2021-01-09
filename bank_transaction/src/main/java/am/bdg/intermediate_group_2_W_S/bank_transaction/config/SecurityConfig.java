package am.bdg.intermediate_group_2_W_S.bank_transaction.config;

import am.bdg.intermediate_group_2_W_S.bank_transaction.service.impl.UserDetail;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.firewall.HttpStatusRequestRejectedHandler;
import org.springframework.security.web.firewall.RequestRejectedHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetail userDetail;

    public SecurityConfig(UserDetail userDetail) {
        this.userDetail = userDetail;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/api",  "/swagger-ui/**", "/api/user/register", "resources/webjars/**", "/swagger-resources/**", "/v2/api-docs").permitAll()
                .antMatchers("/api/transaction/accept/**", "/api/account/**", "/api/user/**/role").hasRole("ADMIN")
                .antMatchers("/api/transaction", "/api/transaction/cancel/**", "api/user/**/transaction", "/api/transaction/filter", "/api/transaction/**/filter", "/api/home").hasAnyRole("ADMIN", "USER")
                .anyRequest().authenticated()
                .and()
                .formLogin().defaultSuccessUrl("/swagger-ui/")
                .and()
                .logout();
    }

        @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetail)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public RequestRejectedHandler requestRejectedHandler() {
        return new HttpStatusRequestRejectedHandler();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
