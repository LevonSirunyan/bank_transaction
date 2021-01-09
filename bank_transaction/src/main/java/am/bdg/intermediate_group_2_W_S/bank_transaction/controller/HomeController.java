package am.bdg.intermediate_group_2_W_S.bank_transaction.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

@RestController
public class HomeController {

    @GetMapping(path = {"/", "/api"})
    void handle(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui/");
    }

    @GetMapping("/api/home")
    public String home(@ApiIgnore Principal principal) {
        return String.format("Welcome to Bank Transaction home %s your roles is %s.",
                principal.getName().toUpperCase(), ((Authentication) principal).getAuthorities());
    }
}
