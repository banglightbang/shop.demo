package com.tv.bang.orders.config;

import feign.Logger.Level;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.hateoas.config.EnableHypermediaSupport;

@Configuration
@EnableFeignClients(basePackages = "com.tv.bang")
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
@Import(FeignClientsConfiguration.class)
public class FeignConfiguration {

    @Bean
    Level feignLoggerLevel() {
        return Level.BASIC;
    }
}
