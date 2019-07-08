package crud.controller;

import crud.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import crud.model.CustomerUI;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @GetMapping("/bulkcreate")
    public void bulkcreate(){
        service.bulkcreate();
    }

    @PostMapping("/save")
    public void saveCustomer(@RequestBody CustomerUI customer) {
        service.saveCustomer(customer);
    }

    @GetMapping("/findall")
    public List<CustomerUI> findAll(){
        return service.findAll();
    }

    @PostMapping("/findbyid")
    public CustomerUI findCustomerById(@RequestBody String id) {
        return service.findCustomerById(id);
    }

    @PostMapping("/findbyname")
    public List<CustomerUI> findCustomerByName(@RequestBody String name) {
        return service.findCustomerName(name);
    }

    @PostMapping("/deletebyid")
    public void deleteCustomerById(@RequestBody String id) {
        service.deleteCustomerById(id);
    }

    @PostMapping("/deletebyname")
    public void deleteCustomerByName(@RequestBody String name) {
        service.deleteCustomerByName(name);
    }

    @PostMapping("/deleteall")
    public void clearRepository() {
        service.clearRepository();
    }

    @PostMapping("/update")
    public void updateCustomer(@RequestBody CustomerUI customer) {
        service.updateCustomer(customer);
    }
}