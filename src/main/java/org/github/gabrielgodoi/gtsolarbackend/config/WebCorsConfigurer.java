package org.github.gabrielgodoi.gtsolarbackend.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebCorsConfigurer implements WebMvcConfigurer{
    @Override
    public void addCorsMappings(CorsRegistry corsRegistry){
        corsRegistry
                .addMapping("/**")
                .allowedOrigins("http://localhost:8081", "http://localhost:8082")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(false);
    }
}