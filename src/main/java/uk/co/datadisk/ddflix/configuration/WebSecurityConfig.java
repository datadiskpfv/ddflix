package uk.co.datadisk.ddflix.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private DdflixAuthenticationProvider ddflixAuthenticationProvider;

    public WebSecurityConfig(DdflixAuthenticationProvider ddflixAuthenticationProvider) {
        this.ddflixAuthenticationProvider = ddflixAuthenticationProvider;
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authenticationProvider(ddflixAuthenticationProvider)
                .authorizeRequests()
                    .antMatchers( "/registration", "/login", "/css/**", "/js/**", "/index", "/error", "/profileImages",
                            "/forgetPassword",
                            "/user/resetPassword",
                            "/user/changePassword",
                            "/user/updatePassword",
                            "/images/**").permitAll()
                    .antMatchers("/admin/**").hasAuthority("ADMIN")
                    .antMatchers("/user/**").hasAuthority("USER")
                    .antMatchers("/dataloader/**").hasAuthority("DATALOADER")
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .failureUrl("/login?error")
                    .and()
                .logout()
                    .permitAll()
                    .invalidateHttpSession(true)
                    .logoutSuccessUrl("/login?logout")
                    .and()
                .headers()
                    .frameOptions()
                    .disable()
                    .and()
                .exceptionHandling()
                    .accessDeniedPage("/login")
                    .and()
                .csrf()
                    .disable();

    }
}