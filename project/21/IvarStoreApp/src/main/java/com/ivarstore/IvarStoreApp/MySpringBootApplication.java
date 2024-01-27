// package com.ivarstore.IvarStoreApp;

// import org.flywaydb.core.Flyway;
// import org.flywaydb.core.api.configuration.FluentConfiguration;
// import org.springframework.beans.factory.config.ResourceLoaderPlaceholderConfigurer;
// import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
// import org.springframework.boot.autoconfigure.flyway.FlywayMigrationInitializer;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.context.annotation.ImportResource;

// import javax.sql.DataSource;
// import java.io.IOException;
// import java.util.Properties;

// @Configuration @org.springframework.boot.autoconfigure.EnableAutoConfiguration(exclude = { FlywayAutoConfiguration.class }) public class MySpringBootApplication {
// @Bean
// public FlywayMigrationInitializer flywayInitializer() {
//     return new FlywayMigrationInitializer(
//             new Flyway(null), new ResourceLoaderPlaceholderConfigurer());
// }
// }