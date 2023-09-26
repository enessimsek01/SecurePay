package com.enessimsek.securepay;

import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.PathSelectors;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


import static springfox.documentation.builders.PathSelectors.regex;

import java.util.ArrayList;

@Configuration
@EnableWebMvc
public class SwaggerConfig {

    @Bean
    public Docket atividadeApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.any())
                .paths(regex("/api.*"))
                .build()
                .apiInfo(metaInfo());
    }

    private ApiInfo metaInfo() {

        ApiInfo apiInfo = new ApiInfo(
                "SecurePay Rest API",
                "Rest API For Secure Pay.",
                "1.0",
                "Terms of Service",
                new Contact("Muhammet Enes Şimşek", "https://www.linkedin.com/in/muhammetenessimsek01/",
                        "enesimsek01@gmail.com "),
                "Github",
                "https://github.com/enessimsek01", new ArrayList<VendorExtension>()
        );

        return apiInfo;
    }
}
