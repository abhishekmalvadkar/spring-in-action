package com.amalvadkar.sia;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;
import java.util.Map;

@Configuration(proxyBeanMethods = false)
@PropertySource("classpath:config.properties")
public class AppConfig {

    @Bean
    public List<String> priorityList(){
        return List.of("P0", "P1", "P2");
    }

    @Bean
    public Map<String, String> priorityListDropdown(){
        return Map.of(
                "P0", "P0",
                "P1", "P1",
                "P2", "P2"
        );
    }

    @Bean
    public Greet greet(@Value("${greet.message}") String greetPrefix){
        return new Greet(greetPrefix);
    }

    @Bean(name = "golden")
    public Greet goldenGreet(){
        return new Greet("golden greet");
    }

    @Bean(name = "premium")
    public Greet premiumGreet(){
        return new Greet("premium greet");
    }

}
