package io.spring.billing.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
public class SecurityAdapter extends WebSecurityConfigurerAdapter {

	/* Para la autenticación */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance())// CODIFICACION DEL PASS
				.withUser("USER1").password("1234").roles("USER", "ADMIN");
	}

	/* Para autorizacion */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/", "/js/**", "/images/**", "/css/**", "/login").permitAll().anyRequest()
				.authenticated().and().formLogin().loginPage("/login").defaultSuccessUrl("/client/list").permitAll().and()
				.logout().permitAll();
	}

	/* configuracion de seguridad para api rest con csrf o cors */
	@Override
	public void configure(WebSecurity web) throws Exception {
		// TODO Auto-generated method stub
		super.configure(web);
	}
}
