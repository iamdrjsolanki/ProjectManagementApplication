package com.iamdrjsolanki.pma.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	DataSource dataSrc;
	
	@Autowired
	BCryptPasswordEncoder bCryptEncoder;
	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		//In Memory authentication of the user
//		auth.inMemoryAuthentication().withUser("myuser").password("pass").roles("USER")
//			.and()
//			.withUser("dhiraj").password("abcd").roles("USER")
//			.and()
//			.withUser("iamdrjsolanki").password("pass").roles("USER")
//			.and()
//			.withUser("manager").password("manager").roles("ADMIN");
//	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//JDBC authentication of the user
		//automatically creates USERS & AUTHORITIES table with the use of withDefaultSchema() method
		auth.jdbcAuthentication().dataSource(dataSrc)
			.usersByUsernameQuery("select username, password, enabled from user_accounts where username = ?")
			.authoritiesByUsernameQuery("select username, role from user_accounts where username = ?")
			.passwordEncoder(bCryptEncoder);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			//antMatchers works as priority as the are written in the method
			.antMatchers("/projects/new").hasRole("ADMIN")
			.antMatchers("/projects/save").hasRole("ADMIN")
			.antMatchers("/employees/new").hasRole("ADMIN")
			.antMatchers("/employees/save").hasRole("ADMIN")
			//all users can access the H2 db
			.antMatchers("/h2-console/**").permitAll()
			.antMatchers("/", "/**").permitAll()
			//load the page if exists
			//.and().formLogin().loginPage("/login-page")
			.and().formLogin();
		
		//to enable other than GET HTTP verbs
		http.csrf().disable();
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

}
