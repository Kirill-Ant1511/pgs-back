package pal.comp.pgsbackend.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Разрешить для всех путей
                .allowedOrigins("http://localhost:3000") // Разрешить конкретный источник
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE") // Разрешить методы
                .allowedHeaders("*"); // Разрешить все заголовки
    }
}
