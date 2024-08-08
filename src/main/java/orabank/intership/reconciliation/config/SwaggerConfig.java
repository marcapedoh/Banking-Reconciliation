package orabank.intership.reconciliation.config;


import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import static orabank.intership.reconciliation.constant.Utils.APP_ROOT;


public class SwaggerConfig {
    @Bean
    public Docket api(){
        return  new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(
                        new ApiInfoBuilder()
                                .description("Solution de rapprochement bancaire automatisé")
                                .title("Gestion de rapprochement")
                                .build()
                )
                .groupName("REST API V1")
                .select()
                .apis(RequestHandlerSelectors.basePackage("orabank.intership.reconciliation"))
                .paths(PathSelectors.ant(APP_ROOT+"/**"))
                .build();
    }
}
