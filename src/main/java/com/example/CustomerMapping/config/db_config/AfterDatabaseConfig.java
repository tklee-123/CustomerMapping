package com.example.CustomerMapping.config.db_config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
        entityManagerFactoryRef = "afterEntityManagerFactory",
        transactionManagerRef = "afterTransactionManager",
        basePackages = {"com.example.CustomerMapping.repo.ldsdb"} // Specify your repository package
)
public class AfterDatabaseConfig {

    @Primary
    @Bean(name = "afterDataSource")
    @ConfigurationProperties(prefix = "spring.after.datasource")
    public DataSource afterDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "afterEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean afterEntityManagerFactory(@Qualifier("afterDataSource") DataSource afterDataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(afterDataSource);
        em.setPackagesToScan(new String[]{"com.example.CustomerMapping.entity.ldsdb"}); // Specify your entity package

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaPropertyMap(additionalProperties());

        return em;
    }

    @Primary
    @Bean(name = "afterTransactionManager")
    public PlatformTransactionManager afterTransactionManager(
            @Qualifier("afterEntityManagerFactory") LocalContainerEntityManagerFactoryBean afterEntityManagerFactory) {
        return new JpaTransactionManager(afterEntityManagerFactory.getObject());
    }

    private Map<String, Object> additionalProperties() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "none");
        properties.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");

        return properties;
    }
}
