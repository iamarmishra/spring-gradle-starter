package dex.compsense.dao;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;

@Component
public class CustomerDAO implements InitializingBean {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerDAO.class);
	
	private SimpleJdbcCall jdbcCall; 
	
	private JdbcTemplate jdbcTemplate;
	
	public void getHomeDetails() {
		Map inputs = new HashMap();
		inputs.put("V_USER", 1);
		jdbcCall.execute(inputs);
	}

	@Autowired
	public CustomerDAO(JdbcTemplate jdbcTemplate) {
		LOGGER.debug("I am on index {}", 2);
		this.jdbcTemplate = jdbcTemplate;
	}


	@Override
	public void afterPropertiesSet() throws Exception {
		jdbcCall = new SimpleJdbcCall(jdbcTemplate)
				.withCatalogName("catalog")
				.withProcedureName("procedurename")
				.returningResultSet("", new CustomerMapper());
		
	}


}