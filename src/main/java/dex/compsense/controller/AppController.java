package dex.compsense.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import dex.compsense.dao.CustomerDAO;

@RestController
public class AppController {
	
	@Autowired
	private CustomerDAO customerDAO;

	private static final String template = "Hello, %s!";

	//@RequestMapping(value="/greeting", method = RequestMethod.GET)
	@GetMapping("/greeting")
	public List getCustomers() {
		customerDAO.getHomeDetails();
		return new ArrayList<>();
	}
    
}
