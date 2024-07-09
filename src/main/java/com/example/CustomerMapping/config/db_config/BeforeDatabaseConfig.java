package com.example.CustomerMapping.config.db_config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "beforeEntityManagerFactory",
        transactionManagerRef = "beforeTransactionManager",
        basePackages = {"com.example.CustomerMapping.repo.viviet"} // Specify your repository package
)
public class BeforeDatabaseConfig {

    @Bean(name = "beforeDataSource")
    @ConfigurationProperties(prefix = "spring.before.datasource")
    public DataSource beforeDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "beforeEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean beforeEntityManagerFactory(@Qualifier("beforeDataSource") DataSource beforeDataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(beforeDataSource);
        em.setPackagesToScan(new String[]{"com.example.CustomerMapping.entity.viviet"}); // Specify your entity package

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaPropertyMap(additionalProperties());

        return em;
    }

    @Bean(name = "beforeTransactionManager")
    public PlatformTransactionManager beforeTransactionManager(
            @Qualifier("beforeEntityManagerFactory") LocalContainerEntityManagerFactoryBean beforeEntityManagerFactory) {
        return new JpaTransactionManager(beforeEntityManagerFactory.getObject());
    }

    private Map<String, Object> additionalProperties() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "none");
        properties.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");

        return properties;
    }
}
