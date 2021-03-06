package com.jglitter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.boot.autoconfigure.web.MultipartAutoConfiguration;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Main Spring configuration entry point, depends on the web Configuration.
 */
@Configuration
@Import(com.jglitter.web.Config.class)
@EnableAutoConfiguration(
        exclude = {
                SecurityAutoConfiguration.class,
                ThymeleafAutoConfiguration.class,
                MultipartAutoConfiguration.class,
                LiquibaseAutoConfiguration.class
        })
public class Config {

    @Value("${server.port}")
    private Integer serverPort;

    @Bean
    public EmbeddedServletContainerFactory servletContainer() {
        return new TomcatEmbeddedServletContainerFactory(serverPort);
    }
}
