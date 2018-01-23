package com.accion.security.jwtoauthserver.authBuilder;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

@Configuration
public interface AuthenticationType { 
	
	/**
	 * For setting the authentication type 
	 * @throws Exception
	 */
	public void setAuthenticationManager(AuthenticationManagerBuilder auth)  throws Exception;

}
