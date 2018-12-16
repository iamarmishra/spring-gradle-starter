package dex.compsense.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import dex.compsense.config.constants.ConfigConstants;
import dex.compsense.dao.CustomerDAO;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "dex.compsense")
@EnableTransactionManagement
@PropertySource(value = {"classpath:application.properties"})
public class AppConfig {
	
	@Autowired
	private Environment environment;
	
	@Bean
	public CustomerDAO customerDAO() {
		return new CustomerDAO(getJdbcTemplate());
	}
	
	@Bean
	public JdbcTemplate getJdbcTemplate() {
		return new JdbcTemplate(getDataSource());
	}
	
	@Bean
	public DataSource getDataSource() {
		
		String username = environment.getProperty(ConfigConstants.DATASOURCE_USERNAME);
		String password = environment.getProperty(ConfigConstants.DATASOURCE_PASSWORD);
		
		if(ConfigConstants.PRODUCTION.equalsIgnoreCase(environment.getProperty(ConfigConstants.ENVIRONMENT))) {
			username = environment.getProperty(ConfigConstants.PROD_DATASOURCE_USERNAME);
			password = environment.getProperty(ConfigConstants.PROD_DATASOURCE_PASSWORD);
		}
		
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setUrl(environment.getProperty(ConfigConstants.DATASOURCE_URL));
		driverManagerDataSource.setUsername(username);
		driverManagerDataSource.setPassword(password);
		driverManagerDataSource.setDriverClassName(environment.getProperty(ConfigConstants.DATASOURCE_DRIVER));
		return driverManagerDataSource;
	}
}