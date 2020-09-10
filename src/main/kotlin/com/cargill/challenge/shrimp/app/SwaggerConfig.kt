package com.cargill.challenge.shrimp.app

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@EnableSwagger2
@Configuration
class SwaggerConfig {
    @Bean
    fun v1APIConfiguration(): Docket? {
        return Docket(DocumentationType.SWAGGER_2).groupName("Cargill Shrimp Farm App Challenge")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.cargill.challenge.shrimp.controller"))
                .paths(PathSelectors.regex("/api/v1.*"))
                .build()
                .apiInfo(ApiInfoBuilder().version("1.0")
                        .title("Cargill Shrimp Farm API")
                        .description("API for Cargill Shrimp Farm Business")
                        .license("© 2020 Cargill")
                        .build())
    }
}