package com.accion.security.jwtoauthserver.service;

import com.accion.security.jwtoauthserver.model.ks.KSAuthorities;
import com.accion.security.jwtoauthserver.model.ks.KSTenant;
import com.accion.security.jwtoauthserver.repository.ks.KSAuthoritiesRepositoy;
import com.accion.security.jwtoauthserver.repository.ks.KSTenentRepositoy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.ldap.userdetails.LdapAuthoritiesPopulator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Venkatesh on 26-Feb-18.
 */
@Service("myAuthPopulator")
@EnableAsync(proxyTargetClass = true)
@EnableCaching
public class MyAuthoritiesPopulator implements LdapAuthoritiesPopulator {


    static final Logger LOG = LoggerFactory.getLogger(MyAuthoritiesPopulator.class);

    @Autowired
    KSTenentRepositoy ksTenentRepositoy;

    @Autowired
    KSAuthoritiesRepositoy ksAuthoritiesRepositoy;

    @Transactional(readOnly = true)
    @Override
    public Collection<? extends GrantedAuthority> getGrantedAuthorities(DirContextOperations userData, String username) {
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        try {
            KSTenant ksTenant = ksTenentRepositoy.findByUserName(username);
            if (ksTenant == null)
                LOG.error("Threw exception in MyAuthoritiesPopulator::getGrantedAuthorities : User doesn't exist into ATS database");
            else {
                List<KSAuthorities> ksAuthorities = ksAuthoritiesRepositoy.findByRole(ksTenant.getKsRole());
                for (KSAuthorities authority : ksAuthorities) {
                    authorities.add(new SimpleGrantedAuthority(authority.getAuthority()));
                }
                return authorities;
            }
            return authorities;
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("Threw exception in MyAuthoritiesPopulator::getGrantedAuthorities : ");
        }
        return authorities;
    }
}