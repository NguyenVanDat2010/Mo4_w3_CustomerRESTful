package cms.controller;

import cms.model.Customer;
import cms.service.customer.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    ICustomerService customerService;

////    @RequestMapping(value = "/customers",method = RequestMethod.GET)
//    @GetMapping("/customers")
//    public List<Customer> showListCustomer(){
//        List<Customer> list = customerService.findAll();
//        System.out.println(list.size());
//        list.forEach(k-> System.out.println(k));
//        return list;
//    }

    //-------------------Retrieve All Customers--------------------------------------------------------

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Customer>> showListCustomer() {
        List<Customer> list = customerService.findAll();
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    //-------------------Retrieve Single Customer--------------------------------------------------------

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> getCustomer(@PathVariable Long id) {
        Customer customer = customerService.findById(id);
        if (customer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    //-------------------Create a Customer--------------------------------------------------------

//    @RequestMapping(method = RequestMethod.POST)
//    public ResponseEntity<Void> createCustomer(@RequestBody Customer customer, UriComponentsBuilder ucBuilder) {
//        customerService.save(customer);
//        HttpHeaders headers = new HttpHeaders();
//        headers.setLocation(ucBuilder.path("/customers/{id}").buildAndExpand(customer.getId()).toUri());
//        return new ResponseEntity<>(headers, HttpStatus.CREATED);
//    }

    @PostMapping()
    public ResponseEntity<Customer> createNewStudent(@RequestBody Customer customer){
        customerService.save(customer);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //------------------- Update a Customer --------------------------------------------------------

//    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
//    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") Long id, @RequestBody Customer customer) {
//        Customer currentCustomer = customerService.findById(id);
//        if (currentCustomer == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        currentCustomer.setFirstName(customer.getFirstName());
//        currentCustomer.setLastName(customer.getLastName());
//        currentCustomer.setId(customer.getId());
//        customerService.save(currentCustomer);
//
//        return new ResponseEntity<>(currentCustomer, HttpStatus.OK);
//    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) {
        customerService.save(customer);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    //------------------- Delete a Customer --------------------------------------------------------

    @RequestMapping(value ="{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Customer> deleteCustomer(@PathVariable("id") Long id){
        Customer customer = customerService.findById(id);
        if (customer == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        customerService.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
