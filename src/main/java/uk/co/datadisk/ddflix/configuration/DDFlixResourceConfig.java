package uk.co.datadisk.ddflix.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class DDFlixResourceConfig implements WebMvcConfigurer {

    // Need this to supply the dynamic image content
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/classification/**").addResourceLocations("file:C://java_projects/ddflix/images/classification/");
        registry.addResourceHandler("/images/film/**").addResourceLocations("file:C://java_projects/ddflix/images/film/");
    }
}