package com.amalvadkar.sia;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AppConfigTest {

    @Test
    void should_create_priority_list_bean_in_the_context() {
        try (var context = new AnnotationConfigApplicationContext(AppConfig.class)) {
            List<String> priorityList = (List<String>) context.getBean("priorityList");
            assertThat(priorityList).isNotNull();
            assertThat(priorityList).containsExactly("P0", "P1", "P2");
        }
    }

    @Test
    void should_create_priority_list_dropdown_bean_in_the_context() {
        try (var context = new AnnotationConfigApplicationContext(AppConfig.class)) {
            Map<String, String> priorityListDropdown = (Map<String, String>) context.getBean("priorityListDropdown");
            assertThat(priorityListDropdown).isNotNull();
            assertThat(priorityListDropdown).containsExactlyInAnyOrderEntriesOf(
                    Map.of(
                            "P0", "P0",
                            "P1", "P1",
                            "P2", "P2"
                    )
            );
        }
    }

    @Test
    void should_create_app_config_without_proxy() {
        try (var context = new AnnotationConfigApplicationContext(AppConfig.class)) {
            AppConfig appConfig = context.getBean(AppConfig.class);
            assertThat(appConfig.getClass().toString()).doesNotContain("$$SpringCGLIB");
        }
    }

    @Test
    void should_able_to_read_prop_from_config_yml_file_from_environment_abstraction() {
        try (var context = new AnnotationConfigApplicationContext(AppConfig.class)) {
            Environment env = context.getBean(Environment.class);
            assertThat(env.getProperty("message")).isEqualTo("Hello World");
        }
    }

    @Test
    void should_able_to_read_prop_from_config_yml_file_using_value_annotation() {
        try (var context = new AnnotationConfigApplicationContext(AppConfig.class)) {
            Greet greet = context.getBean(Greet.class);
            assertThat(greet.getMessage()).isEqualTo("Welcome Back");
        }
    }

}
