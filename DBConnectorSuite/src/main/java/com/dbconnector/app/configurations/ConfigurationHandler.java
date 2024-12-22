package com.dbconnector.app.configurations;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

@Configuration
public class ConfigurationHandler {
	
	/*
	 * Mapping the details in the app.properties to the DataSource Builder.
	 */
	
	@ConfigurationProperties(prefix= "spring.datasource.user")
	@Bean
	DataSourceProperties getDataSourceProperties() {
		return new DataSourceProperties();	
	}
	
	@Bean
	DataSource getDataSource() {
		return getDataSourceProperties().initializeDataSourceBuilder().build();
	}
	
	@Bean
	JdbcTemplate getJdbcTemplate() {
		return new JdbcTemplate(getDataSource());
	}
	
	@Bean
	SimpleJdbcCall getSimpleJdbcTemplate() {
		return new SimpleJdbcCall(getDataSource());
	}
}
