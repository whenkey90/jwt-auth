package com.accion.security.jwtoauthserver.model.ks;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by Venkatesh on 26-Feb-18.
 */
@Entity
@Table(name = "authorities")
public class KSAuthorities {
    @Id
    @GeneratedValue
    String id;
    String role;
    String authority;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
