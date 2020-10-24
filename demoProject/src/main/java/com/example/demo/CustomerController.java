package com.example.demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
@CrossOrigin
public class CustomerController {
	
	
	
	private static List<Customer> customerList = new ArrayList<>();
	/*
	 * private static int idCounter = 0;
	 * 
	 * static { todos.add(new Todo(++idCounter, "in28minutes", "Learn to Dance 2",
	 * new Date(), false)); todos.add(new Todo(++idCounter, "in28minutes",
	 * "Learn about Microservices 2", new Date(), false)); todos.add(new
	 * Todo(++idCounter, "in28minutes", "Learn about Angular", new Date(), false));
	 * }
	 */
	@Autowired
	private CustomerService service;

	@GetMapping("/test")
	public String showtest() {
		return "hello from test";
	}

	@GetMapping("/Customer")
	public List<Customer> getCustomer() {
		System.out.println(service.getCustomer());
		System.out.println(service);
		
		return customerList;
	}

	@PostMapping("Customer/addNew")
	public void addCustomer( @RequestBody Customer customer, BindingResult result) {
		
		customerList.add(customer);
		
		
			service.addCustomer(customer);
			
		
		
		
		
	}

	@PutMapping("Customer/edit/{loc_ref}")
	public void updateCustomer( @PathVariable("loc_ref") String loc_ref, @RequestBody Customer customer) {
		
		
		Date oldDate = null;
//System.out.println("current customer"+customer);
		Optional<Customer> dbCustomer = service.getCustomerByLoc_Ref(customer.getLoc_ref());
		if (dbCustomer.isPresent()) {
			Customer existingCustomer = dbCustomer.get();
			oldDate = existingCustomer.getCurrentDate();
			// operate on existingCustomer
		} else {
			// there is no Customer in the repo with 'id'
			System.out.println("no customer found with this id");
		}

		customer.setPreviousDate(oldDate);
		service.updateCustomer(customer);
		
		////
		System.out.println("before updatigncustomerlist"+customerList);
		for(Customer cust:customerList) {
			System.out.println("before for loop");
			if(cust.getLoc_ref().equalsIgnoreCase(loc_ref)) {
				System.out.println("in side if loop");
				System.out.println("customer exsist");
				System.out.println("matching cust llist"+cust);
				
				System.out.println("previous obhect current data"+cust.getCurrentDate());
				cust.setPreviousDate(cust.getCurrentDate());
				cust.setCurrentDate(customer.getCurrentDate());
				
				
			}
			
		}
		System.out.println("after updatting customerList"+customerList);
		//return "Record updated sucessfully";
	}
 @DeleteMapping("Customer/delete/{loc_ref}") public String 
	 deleteCustomer(@PathVariable ("loc_ref") String loc_ref) { 
	 service.deleteCustomer(loc_ref); return "record successfully deleted"; } 
 
 
 
 
	/*
	 * @InitBinder public void initBinder(WebDataBinder binder) {
	 * 
	 * 
	 * System.out.println("inside init binder");
	 * 
	 * SimpleDateFormat dateFormat= new SimpleDateFormat("dd-MM-yyyy");
	 * CustomDateEditor editor= new CustomDateEditor(dateFormat, false);
	 * binder.registerCustomEditor(Date.class, "currentDate",editor);
	 * 
	 * 
	 * 
	 * //binder.setDisallowedFields("targetDate");
	 * 
	 * }
	 */

}