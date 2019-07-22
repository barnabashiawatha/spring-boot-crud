package crud.service;

import crud.model.Customer;
import crud.payload.CustomerUI;
import crud.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    public void bulkcreate(){
        customerRepository.save(new Customer("Rajesh", 20));

        customerRepository.saveAll(Arrays.asList(new Customer("Salim", 24)
                , new Customer("Rajesh", 21)
                , new Customer("Rahul", 24)
                , new Customer("Dharmendra", 22)));
    }

    public void saveCustomer(CustomerUI customer) {
        customerRepository.save(new Customer(customer.getName(), customer.getAge()));
    }

    public List<CustomerUI> findAll(){
        return customerRepository.findAll().stream().map(
                customer -> new CustomerUI(customer.getId(), customer.getName(), customer.getAge())
        ).collect(Collectors.toList());
    }

    public CustomerUI findCustomerById(String id){
        var customer = customerRepository.findById(Long.parseLong(id)).get();
        return new CustomerUI(customer.getName(), customer.getAge());
    }

    public List<CustomerUI> findCustomerName(String name){
        return customerRepository.findByName(name).stream().map(
                customerUi -> new CustomerUI(customerUi.getId(), customerUi.getName(), customerUi.getAge())
        ).collect(Collectors.toList());
    }

    public void deleteCustomerById(String id) {
        customerRepository.deleteById(Long.parseLong(id));
    }

    @Transactional
    public void deleteCustomerByName(String name) {
        customerRepository.deleteByName(name);
    }

    public void clearRepository() {
        customerRepository.deleteAll();
    }

    public void updateCustomer(@RequestBody CustomerUI customerUI) {
        var customer = customerRepository.findById(customerUI.getId()).get();

        customer.setName(customerUI.getName());
        customer.setAge(customerUI.getAge());
        customerRepository.save(customer);
    }
}
