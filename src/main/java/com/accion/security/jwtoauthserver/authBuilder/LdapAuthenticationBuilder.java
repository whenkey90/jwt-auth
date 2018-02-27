package com.accion.security.jwtoauthserver.authBuilder;

import com.accion.security.jwtoauthserver.service.MyAuthoritiesPopulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.LdapShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

@Configuration
@EnableConfigurationProperties
public class LdapAuthenticationBuilder implements AuthenticationType {

    @Autowired
    MyAuthoritiesPopulator myAuthoritiesPopulator;

    @Override
    public void setAuthenticationManager(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .ldapAuthentication()
                .userDnPatterns("uid={0},ou=people")
                .groupSearchBase("ou=groups")
                .contextSource()
                .url("ldap://localhost:10389/dc=example,dc=com")
                .and()
                .passwordCompare()
                .passwordEncoder(new LdapShaPasswordEncoder())
                .passwordAttribute("userPassword")
                .and()
                .ldapAuthoritiesPopulator(myAuthoritiesPopulator);
    }
}
