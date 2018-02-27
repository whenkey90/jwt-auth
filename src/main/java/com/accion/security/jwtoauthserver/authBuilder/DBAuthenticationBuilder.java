package com.accion.security.jwtoauthserver.authBuilder;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

@Configuration
public class DBAuthenticationBuilder implements AuthenticationType{

	
	@Autowired
	@Qualifier("dataSource")
	DataSource dataSource;
	
	@Override
	public void setAuthenticationManager(AuthenticationManagerBuilder auth) throws Exception {
		auth.
				jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery("select username,password, enabled from users where username=?")
		.authoritiesByUsernameQuery("select username, role from user_roles where username=?");
		
	}

}
