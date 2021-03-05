package me.kwj1270.springboot.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override   // 기존에 존재하는 디폴트 메서드 addViewControllers()를 재정의 한다.
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");    // 기존에 정의한 home.html
        registry.addViewController("/").setViewName("home");        // 기존에 정의한 home.html
        registry.addViewController("/hello").setViewName("hello");  // 기존에 정의한 hello.html
        registry.addViewController("/login").setViewName("login");  // 전혀 다른 뷰, security 가 사용
    }
}
