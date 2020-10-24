package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CustomerService {
	
	
	@Autowired
	private CustomerRepo repo;
public List<Customer> getCustomer()
{
	return repo.findAll();
}
public void addCustomer(Customer customer)
{
	repo.save(customer);
	}
public void updateCustomer(Customer customer)
{
	repo.save(customer);
}
public void deleteCustomer(String loc_ref)
{
repo.deleteById(loc_ref);

}

public Optional<Customer> getCustomerByLoc_Ref(String loc_ref)
{
return repo.findById(loc_ref);

}


}
