package com.accion.security.jwtoauthserver.authBuilder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.support.BaseLdapPathContextSource;
import org.springframework.security.authentication.encoding.LdapShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.security.ldap.SpringSecurityLdapTemplate;
import org.springframework.security.ldap.authentication.BindAuthenticator;
import org.springframework.security.ldap.authentication.LdapAuthenticationProvider;
import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;
import org.springframework.security.ldap.userdetails.LdapAuthoritiesPopulator;
import org.springframework.security.ldap.userdetails.LdapAuthority;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;

import javax.naming.ldap.LdapName;
import javax.naming.ldap.Rdn;
import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
@EnableConfigurationProperties
public class LdapAuthenticationBuilder implements AuthenticationType {

//    /**
//     * LDAP Directory Domain
//     */
//    @Value("${authentication.mode.ldap.activeDirectoryDomain}")
//    private String activeDirectoryDomain;
//
//    /**
//     * LDAP Directory URL
//     */
//    @Value("${authentication.mode.ldap.activeDirectoryUrl}")
//    private String activeDirectoryUrl;


    @Override
    public void setAuthenticationManager(AuthenticationManagerBuilder auth) throws Exception {
//               ActiveDirectoryLdapAuthenticationProvider provider = new ActiveDirectoryLdapAuthenticationProvider(
//                activeDirectoryDomain, activeDirectoryUrl, "uid=admin,ou=system");
//        provider.setConvertSubErrorCodesToExceptions(true);
//        provider.setUseAuthenticationRequestCredentials(true);
//        auth.authenticationProvider(provider);

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
                .ldapAuthoritiesPopulator(ldapAuthoritiesPopulator());

    }

        @Bean
        LdapAuthoritiesPopulator ldapAuthoritiesPopulator() throws Exception {
            /*
              Specificity here : we don't get the Role by reading the members of available groups (which is implemented by
              default in Spring security LDAP), but we retrieve the groups from the field memberOf of the user.
             */
            class CustomLdapAuthoritiesPopulator implements LdapAuthoritiesPopulator {

                public final String[] GROUP_ATTRIBUTE = {"cn"};
                public final String GROUP_MEMBER_OF = "memberof";
//                SpringSecurityLdapTemplate ldapTemplate;

//                CustomLdapAuthoritiesPopulator() throws Exception {
//                    ldapTemplate = new SpringSecurityLdapTemplate(contextSource());
//                }
                @Override
                public Collection<? extends GrantedAuthority> getGrantedAuthorities(
                        DirContextOperations userData, String username) {
                    Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
                    if ("ben".equals(username)) {
                        authorities.add(new LdapAuthority("READ","cn=READ,ou=groups,dc=example,dc=com"));
                    } else if ("louis".equals(username)) {
                        authorities.add(new GrantedAuthority() {
                            @Override
                            public String getAuthority() {
                                return "WRITE";
                            }
                        });
                    }
                    return authorities;
                    //TODO we have to do it dynamically assigning the GrantedAuthority based on groups
//                String[] groupDns = userData.getStringAttributes(GROUP_MEMBER_OF);
//                String roles = Stream.of(groupDns).map(groupDn -> {
//                    LdapName groupLdapName = (LdapName) ldapTemplate.retrieveEntry(groupDn, GROUP_ATTRIBUTE).getDn();
//                    // split DN in its different components et get only the last one (cn=my_group)
//                    // getValue() allows to only get get the value of the pair (cn=>my_group)
//                    return groupLdapName.getRdns().stream().map(Rdn::getValue).reduce((a, b) -> b).orElse(null);
//                }).map(x -> (String)x).collect(Collectors.joining(","));
//
//                return AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
                }
            }

        return new CustomLdapAuthoritiesPopulator();
    }

}
