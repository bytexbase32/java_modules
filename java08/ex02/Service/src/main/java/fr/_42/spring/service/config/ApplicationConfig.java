
package fr._42.spring.service.config;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

@Configuration
@ComponentScan(basePackages = "fr._42.spring.service")
@PropertySource("classpath:db.properties")
public class ApplicationConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean(name = "driverManagerDataSource")
    public DataSource driverManagerDataSource(
            @Value("${db.url}") String url,
            @Value("${db.user}") String username,
            @Value("${db.password}") String password,
            @Value("${db.driver.name}") String driver
    ) {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        ds.setDriverClassName(driver);
        return ds;
    }

    @Bean(name = "hikariDataSource", destroyMethod = "close")
    public HikariDataSource hikariDataSource(
            @Value("${db.url}") String url,
            @Value("${db.user}") String username,
            @Value("${db.password}") String password,
            @Value("${db.driver.name}") String driver
    ) {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        ds.setDriverClassName(driver);
        return ds;
    }

    @Bean
    public DataSourceInitializer schemaInitializer(
            @org.springframework.beans.factory.annotation.Qualifier(
                    "driverManagerDataSource") DataSource dataSource
    ) {
        ResourceDatabasePopulator populator =
                new ResourceDatabasePopulator(
                        new ClassPathResource("schema.sql")
                );

        DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);
        initializer.setDatabasePopulator(populator);
        return initializer;
    }
}
