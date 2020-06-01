package com.freedom.music.datasource.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.ConnectionReleaseMode;
import org.hibernate.cfg.ImprovedNamingStrategy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef="musicEntityManagerFactory",
        transactionManagerRef="musicTransactionManager",
        basePackages= {"com.freedom.music.datasource.repository"})
public class MusicDBConfig {
    
    @Primary
    @Bean(name = "musicDatasourceHikariConfig")
    @ConfigurationProperties(prefix = "music.datasource")
    public HikariConfig hikariConfig() {
        HikariConfig hikariConfig = new HikariConfig();
        return hikariConfig;
    }

    @Primary
    @Bean(name = "musicDatasource")
    public DataSource dataSource(@Qualifier("musicDatasourceHikariConfig") HikariConfig hikariConfig) {
        return new HikariDataSource(hikariConfig);
    }

    protected Map<String, Object> jpaProperties() {
        Map<String, Object> props = new HashMap<>();
        props.put("hibernate.physical_naming_strategy", SpringPhysicalNamingStrategy.class.getName());
        props.put("hibernate.implicit_naming_strategy", SpringImplicitNamingStrategy.class.getName());
        props.put("hibernate.naming-strategy", ImprovedNamingStrategy.class.getName());
        props.put("hibernate.connection.release_mode", ConnectionReleaseMode.AFTER_TRANSACTION);
        props.put("hibernate.connection.handling_mode", "DELAYED_ACQUISITION_AND_RELEASE_AFTER_TRANSACTION");
        return props;
    }

    @Primary
    @Bean(name = "musicEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("musicDatasource") DataSource dataSource) {
        return builder.dataSource(dataSource)
                .packages("com.freedom.music.datasource.model")
                .properties(jpaProperties())
                .build();
    }

    @Bean(name = "musicTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("musicEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}