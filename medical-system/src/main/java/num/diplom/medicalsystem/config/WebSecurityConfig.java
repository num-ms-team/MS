package num.diplom.medicalsystem.config;

import num.diplom.medicalsystem.security.TokenHelper;
import num.diplom.medicalsystem.security.auth.RestAuthenticationEntryPoint;
import num.diplom.medicalsystem.security.auth.TokenAuthenticationFilter;
import num.diplom.medicalsystem.service.impl.MSUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class WeSecurityConfig extends WebSecurityConfigurerAdapter {

    private final MSUserDetailsService userDetailsService;
    private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    private final TokenHelper tokenHelper;

    public WeSecurityConfig(
            MSUserDetailsService userDetailsService,
            RestAuthenticationEntryPoint restAuthenticationEntryPoint,
            TokenHelper tokenHelper
    ) {
        super(true);
        this.userDetailsService = userDetailsService;
        this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
        this.tokenHelper = tokenHelper;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint).and()
                .authorizeRequests()
                .antMatchers(
                        HttpMethod.GET,
                        "/",
                        "/webjars/**",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js"
                ).permitAll()
                .antMatchers("/aim/**").permitAll()
                .anyRequest().authenticated().and()
                .addFilterBefore(new TokenAuthenticationFilter(tokenHelper, userDetailsService), BasicAuthenticationFilter.class);

        http.csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // TokenAuthenticationFilter will ignore the below paths
        web.ignoring().antMatchers(
                HttpMethod.POST,
                "/aim/login"
        );
        web.ignoring().antMatchers(
                HttpMethod.GET,
                "/",
                "/webjars/**",
                "/*.html",
                "/favicon.ico",
                "/**/*.html",
                "/**/*.css",
                "/**/*.js"
        );
    }
}
