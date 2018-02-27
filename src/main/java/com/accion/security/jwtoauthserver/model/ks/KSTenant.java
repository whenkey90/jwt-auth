package com.accion.security.jwtoauthserver.model.ks;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Venkatesh on 26-Feb-18.
 */
@Entity
@Table(name = "tenant")
public class KSTenant implements Serializable{
    @Id
    @GeneratedValue
    String id;

    @Column(name = "user_name")
    String userName;

    String tenant;

    @Column(name = "sub_company")
    String subCompany;

    @Column(name = "ad_role")
    String adRole;
    @Column(name = "ks_role")
    String ksRole;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    public String getSubCompany() {
        return subCompany;
    }

    public void setSubCompany(String subCompany) {
        this.subCompany = subCompany;
    }

    public String getAdRole() {
        return adRole;
    }

    public void setAdRole(String adRole) {
        this.adRole = adRole;
    }

    public String getKsRole() {
        return ksRole;
    }

    public void setKsRole(String ksRole) {
        this.ksRole = ksRole;
    }
}
