package io.spring.billing.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import io.spring.billing.security.UserDetailsServiceImpl;

@Configuration
public class SecurityAdapter extends WebSecurityConfigurerAdapter {


	private PasswordEncoder passwordEncoder;
	
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	public SecurityAdapter(final PasswordEncoder passwordEncoder,
			final UserDetailsServiceImpl userDetailsService
			) {
		this.passwordEncoder = passwordEncoder;
		this.userDetailsServiceImpl = userDetailsService;
	}
	
	
	/* Para la autenticación */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		/*
		auth.inMemoryAuthentication().passwordEncoder(passwordEncoder)// CODIFICACION DEL PASS
				.withUser("USER1").password(passwordEncoder.encode("1234")).roles("USER", "ADMIN","SUPERVISOR").and()
				.withUser("USER2").password(passwordEncoder.encode("4321")).roles("USER");
		*/
		
		auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder);
	}

	/* Para autorizacion guarda la seguridad en sessio en el http-only (cookies invisibles para js)*/
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/", "/js/**", "/images/**", "/css/**", "/login").permitAll().anyRequest()
				.authenticated()
				.antMatchers("/bill/**").hasAnyRole("ADMIN","SUPERVISOR")
				.antMatchers(HttpMethod.DELETE).hasAnyRole("ADMIN")
				.and().formLogin().loginPage("/login").defaultSuccessUrl("/client/list").permitAll().and()
				.logout().permitAll();
	}

	/* configuracion de seguridad para api rest con csrf o cors */
	@Override
	public void configure(WebSecurity web) throws Exception {
		// TODO Auto-generated method stub
		super.configure(web);
	}
}
