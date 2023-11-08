package org.dnsouzadev;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfiguration {

    @Bean(name = "applicationName")
    public String applicationName() {
        return "Sistema de Vendas";
    }
}
