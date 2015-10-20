package com.rw.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.rw.service.DBUserDetailsService;

@Configuration
@EnableWebMvcSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

	@Autowired
	private DBUserDetailsService userDetailsService;
	@Autowired
	DataSource dataSource;
	//@Autowired
	//CustomAuthenticationProvider customAuthenticationProvider;
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.exceptionHandling().accessDeniedPage("/login?error");
		http.authorizeRequests()
		// .antMatchers("/api/**").permitAll() //接口无需授权
			
				.antMatchers("/hello").hasRole("USER")
				.anyRequest().authenticated();
		http.formLogin()
				.failureUrl("/login?error")
				.defaultSuccessUrl("/")
				.successHandler(savedRequestAwareAuthenticationSuccessHandler())
				.loginPage("/login").permitAll()
				.loginProcessingUrl("/login");
		http.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/login").permitAll();
		http.rememberMe().tokenRepository(persistentTokenRepository())
		.tokenValiditySeconds(1209600);
		// super.configure(http);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {

		web.ignoring().antMatchers("/api/**", "/static/**");
		// super.configure(web);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//auth.authenticationProvider(customAuthenticationProvider);
		auth.userDetailsService(userDetailsService);
		
		// super.configure(auth);
	}
	
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
		db.setDataSource(dataSource);
		return db;
	}
	
	@Bean
	public SavedRequestAwareAuthenticationSuccessHandler savedRequestAwareAuthenticationSuccessHandler() {
		SavedRequestAwareAuthenticationSuccessHandler auth = new SavedRequestAwareAuthenticationSuccessHandler();
		auth.setTargetUrlParameter("targetUrl");
		return auth;
	}	
}
