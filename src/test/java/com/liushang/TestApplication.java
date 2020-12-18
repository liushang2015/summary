package com.liushang;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liushang.web.filter.CommonFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Slf4j
@EnableTransactionManagement
@ComponentScan({ "com.liushang" })
public class TestApplication {
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(TestApplication.class, args);
        log.info("{}", applicationContext);
        log.warn("初始化完成!");
    }
    @Bean
    RequestMappingHandlerMapping requestHandlerMapping() {
        RequestMappingHandlerMapping requestHandlerMapping = new RequestMappingHandlerMapping();
        requestHandlerMapping.setUseSuffixPatternMatch(true);
        return requestHandlerMapping;
    }
    @Bean
    public CommonFilter commonFilter() {
        return new CommonFilter();
    }


}
