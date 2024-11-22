package com.microservice;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.servicess.CustomerService;



@RestController
@RequestMapping("api/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<HashMap<String, Object>> registerCustomer(@RequestBody CustomerDTO customerDTO) {
        CustomerDTO customer = customerService.findCustomerByEmail(customerDTO.email);
        long idCustomer = customerService.getCustomerByDocument(customerDTO.document);
        HashMap<String, Object> response = new HashMap<>();
        if( idCustomer == -1 && customer == null) {
            customerService.registerCustomer(customerDTO);
            response.put("customer", customerDTO);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("error","Customer not found");
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/{email}")
    public ResponseEntity<HashMap<String, Object>> findCustomerByEmail(@PathVariable("email") String email) {
        CustomerDTO customerDTO = customerService.findCustomerByEmail(email);
        System.out.println(customerDTO);
        HashMap<String, Object> response = new HashMap<>();
        if(customerDTO != null) {
            CustomerDTO customerDT = customerService.findCustomerByEmail(email);
            response.put("customer", customerDT);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("error","Customer not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<HashMap<String, Object>> getAllCustomers() {
        HashMap<String, Object> response = new HashMap<>();
        response.put("customers", customerService.getAllCustomers());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @PostMapping("/update/{document}")
    public ResponseEntity<HashMap<String, Object>> updateCustomer(@PathVariable("document") long document, @RequestBody CustomerDTO customerDTO) {
        HashMap<String, Object> response = new HashMap<>();
        long id = customerService.getCustomerByDocument(document);
        if(id == -1) {
            response.put("error", "Customer not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
            customerService.updateCustomer(customerDTO);

        response.put("customer", customerDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/delete/{document}")
    public ResponseEntity<HashMap<String, Object>> deleteCustomer(@PathVariable("document") long document) {
        HashMap<String, Object> response = new HashMap<>();
        long id = customerService.getCustomerByDocument(document);
        if(id == -1) {
            response.put("error", "Customer not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        customerService.deleteCustomer(id);
        response.put("customer", "Customer deleted");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("id/{document}")
    public ResponseEntity<HashMap<String, Object>> getCustomerByDocument(@PathVariable("document") long document) {
        HashMap<String, Object> response = new HashMap<>();
        long id = customerService.getCustomerByDocument(document);
        if(id == -1) {
            response.put("error", "Customer not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        response.put("id", id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/changePassword/{email}")
    public ResponseEntity<HashMap<String, Object>> changePassword(@PathVariable("email") String email, @RequestBody String password) {
        
        HashMap<String, Object> response = new HashMap<>();
        CustomerDTO customer = customerService.findCustomerByEmail(email);
        if(customer == null) {
            response.put("error", "Customer not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } else {
            if(customer.password.equals(password)) {
                response.put("error", "Password is the same");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            } else {
                customerService.changePassword(email, password);
                response.put("customer", "Password changed");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }
    
    }
}
