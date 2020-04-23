package com.tv.bang.api.gateway.config;

import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@Configuration
@EnableSwagger2WebMvc
public class SwaggerConfiguration {

    @Bean
    @Primary
    public SwaggerResourcesProvider swaggerResourcesProvider(RouteLocator routeLocator) {
        return new GatewaySwaggerResourcesProvider(routeLocator);
    }
}
