package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@Configuration
public class ThymeleafConfig extends WebMvcConfigurerAdapter {

        @Bean
        public ClassLoaderTemplateResolver templateResolver() {
            ClassLoaderTemplateResolver classLoaderTemplateResolver = new ClassLoaderTemplateResolver();
            classLoaderTemplateResolver.setCacheable(false);
            classLoaderTemplateResolver.setPrefix("templates/");
            classLoaderTemplateResolver.setSuffix(".html");
            classLoaderTemplateResolver.setTemplateMode("HTML5");
            classLoaderTemplateResolver.setCacheable(false);
            return classLoaderTemplateResolver;
        }


        @Bean
        public SpringTemplateEngine templateEngine() {
            SpringTemplateEngine templateEngine = new SpringTemplateEngine();
            templateEngine.setTemplateResolver(templateResolver());
//            templateEngine.addDialect(new ThymeleafDialect());
            return templateEngine;
        }

        @Bean
        public ThymeleafViewResolver viewResolver() {
            ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
            viewResolver.setTemplateEngine(templateEngine());

            return viewResolver;
        }

        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/resources/**").addResourceLocations("/WEB-INF/resources/");
        }
}