package com.accion.security.jwtoauthserver.config.db;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * Created by Venkatesh on 26-Feb-18.
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.accion.security.jwtoauthserver.repository.ks",
        entityManagerFactoryRef = "ksEntityManagerFactory", transactionManagerRef = "ksTransactionManager")
public class KSDBConfig {

    @Bean
    @ConfigurationProperties(prefix = "ks.datasource")
    public DataSource ksDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "ksEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder, @Qualifier("ksDataSource") DataSource ksDataSource) {
        return builder.dataSource(ksDataSource).packages("com.accion.security.jwtoauthserver.model.ks").persistenceUnit("ks").build();
    }

    @Bean(name = "ksTransactionManager")
//    @Order(2)
    public PlatformTransactionManager transactionManager(@Qualifier("ksEntityManagerFactory") EntityManagerFactory ksEntityManagerFactory) {
        return new JpaTransactionManager(ksEntityManagerFactory);
    }

}
