package crud.controller;

import crud.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import crud.payload.CustomerUI;
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

    @GetMapping("/findbyid")
    public CustomerUI findCustomerById(@RequestParam("id") String id) {
        return service.findCustomerById(id);
    }

    @GetMapping("/findbyname")
    public List<CustomerUI> findCustomerByName(@RequestParam("name") String name) {
        return service.findCustomerName(name);
    }

    @GetMapping("/deletebyid")
    public void deleteCustomerById(@RequestParam("id") String id) {
        service.deleteCustomerById(id);
    }

    @GetMapping("/deletebyname")
    public void deleteCustomerByName(@RequestParam("name") String name) {
        service.deleteCustomerByName(name);
    }

    @GetMapping("/deleteall")
    public void clearRepository() {
        service.clearRepository();
    }

    @PostMapping("/update")
    public void updateCustomer(@RequestBody CustomerUI customer) {
        service.updateCustomer(customer);
    }
}