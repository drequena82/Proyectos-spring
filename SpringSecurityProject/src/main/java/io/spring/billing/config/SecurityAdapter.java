package io.spring.billing.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityAdapter extends WebSecurityConfigurerAdapter {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	/* Para la autenticación */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.inMemoryAuthentication().passwordEncoder(passwordEncoder)// CODIFICACION DEL PASS
				.withUser("USER1").password(passwordEncoder.encode("1234")).roles("USER", "ADMIN").and()
				.withUser("USER2").password(passwordEncoder.encode("4321")).roles("USER");
	}

	/* Para autorizacion */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/", "/js/**", "/images/**", "/css/**", "/login").permitAll().anyRequest()
				.authenticated().antMatchers("/bill/**").hasRole("ADMIN").and().formLogin().loginPage("/login").defaultSuccessUrl("/client/list").permitAll().and()
				.logout().permitAll();
	}

	/* configuracion de seguridad para api rest con csrf o cors */
	@Override
	public void configure(WebSecurity web) throws Exception {
		// TODO Auto-generated method stub
		super.configure(web);
	}
}
