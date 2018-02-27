package com.accion.security.jwtoauthserver.repository.ks;

import com.accion.security.jwtoauthserver.model.ks.KSAuthorities;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Venkatesh on 26-Feb-18.
 */
public interface KSAuthoritiesRepositoy extends CrudRepository<KSAuthorities, String> {
    List<KSAuthorities> findByRole(String ksRole);
}
