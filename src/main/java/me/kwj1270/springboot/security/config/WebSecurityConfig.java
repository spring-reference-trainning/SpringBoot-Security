package me.kwj1270.springboot.security.config;


import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableConfigurationProperties
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /*
     * configure (HttpSecurity) 메서드는 보안해야하는 URL 경로와 보안하지 않아야하는 경로를 정의합니다.
     * */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/", "/home").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .formLogin()        // 사용자가 성공적으로 로그인하면 인증이 필요했던 이전 요청 페이지로 리디렉션됩니다.
                    .loginPage("/login")
                    .permitAll()
                    .and()
                .logout()
                    .permitAll();


    }

    /*
     * userDetailsService () 메서드는 단일 사용자로 메모리 내 사용자 저장소를 설정합니다.
     * 해당 사용자에게는 user의 사용자 이름, 암호의 암호 및 USER의 역할이 제공됩니다.
     * 즉, 해당 사용자 정보가 이미 DB에 저장되어 있다 와 같은 역할을 해준다.
     * */
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()   // Deprecated 되었지만, 데모용으로 사용
                        .username("kwj1270")
                        .password("password")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }


}
