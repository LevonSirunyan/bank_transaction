package am.bdg.intermediate_group_2_W_S.bank_transaction.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build().apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "BDG Intermediate Group 2-W-S Bank Transaction API.",
                "Rest Endpoints for Bank Transaction API",
                "1.0.0",
                "Terms of service",
                new Contact("BDG Bank Transaction API", "www.bdg.intermediate_group_2_W_S.bank_transaction", "test@bdg.com"),
                "License of BDG Intermediate Group 2-W-S Bank Transaction API", "API license URL", Collections.emptyList());
    }
}
