package com.accion.security.jwtoauthserver.repository.ks;

import com.accion.security.jwtoauthserver.model.ks.KSTenant;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Venkatesh on 26-Feb-18.
 */
public interface KSTenentRepositoy extends CrudRepository<KSTenant,String>{
    KSTenant findByUserName(String userName);
}
