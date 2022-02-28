package com.marcel.baumgardt.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import springfox.documentation.builders.PathSelectors
import springfox.documentation.service.ApiKey
import springfox.documentation.service.AuthorizationScope
import springfox.documentation.service.SecurityReference
import springfox.documentation.service.SecurityScheme
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spi.service.contexts.SecurityContext
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2


@Configuration
@EnableSwagger2
class SwaggerConfiguration {

    @Bean
    fun swaggerApi(): Docket? {
        return Docket(DocumentationType.SWAGGER_2)
            .ignoredParameterTypes(UsernamePasswordAuthenticationToken::class.java)
            .select()
            .paths(PathSelectors.regex("^(?!/(error).*$).*$"))
            .build()
            .securitySchemes(listOf(createSchema()))
            .securityContexts(listOf(createContext()))
    }

    fun createContext(): SecurityContext {
        return SecurityContext.builder()
            .securityReferences(createRef())
            .forPaths(PathSelectors.any())
            .build()
    }

    fun createRef(): List<SecurityReference?>? {
        val authorizationScope = AuthorizationScope(
            "global", "accessEverything"
        )
        val authorizationScopes: Array<AuthorizationScope?> = arrayOfNulls<AuthorizationScope>(1)
        authorizationScopes[0] = authorizationScope
        return listOf(SecurityReference("apiKey", authorizationScopes))
    }

    fun createSchema(): SecurityScheme {
        return ApiKey("apiKey", "Authorization", "header")
    }
}