package br.edu.iff.projetoConsulta.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class MySecurity extends WebSecurityConfigurerAdapter{
    @Autowired
    private AssistenteDetailsService service;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().ignoringAntMatchers("/apirest/**")
                .and()
                .authorizeRequests()
                .antMatchers("/apirest/**").hasRole("ADMIN")
                .antMatchers("/files/**").hasAnyRole("ADMIN", "ASSIS")
                .antMatchers("/assistentes/meusdados/**").hasAnyRole("ADMIN", "ASSIS")
                .antMatchers("/assistentes").hasRole("ADMIN")
                .antMatchers("/assistente/**").hasRole("ADMIN")
                .antMatchers("/**").hasAnyRole("ADMIN", "ASSIS")
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .formLogin()
                .usernameParameter("email");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(service).passwordEncoder(new BCryptPasswordEncoder());
    }
}
