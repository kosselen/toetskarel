package guru.springframework.controllers;

import guru.springframework.domain.Customer;
import guru.springframework.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by KOSSELEN on 17-7-2017.
 */

@Controller
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("customers", customerService.listAllCustomers());
        System.out.println("Returning customers:");
//        throw new OutOfMemoryError();
        return "customers";
    }

    @RequestMapping(value = "customer/{id}", method = RequestMethod.GET)
    public String showProduct(@PathVariable Integer id, Model model){
        model.addAttribute("product", customerService.getCustomerById(id));
        return "customershow";
    }

    @RequestMapping(value = "customer/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("customer", customerService.getCustomerById(id));
        return "customerform";
    }

    @RequestMapping(value = "customer/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable Integer id) {
        customerService.deleteCustomerById(id);
        return "redirect:/customers";
    }

    @RequestMapping(value = "customer/new", method = RequestMethod.GET)
    public String newCustomer(Model model){
        model.addAttribute("customer", new Customer());
        return "customerform";
    }

    @RequestMapping(value = "customer", method = RequestMethod.POST)
    public String saveProduct(Customer customer){

        customerService.saveCustomer(customer);

        return "redirect:/customer/" + customer.getId();
    }




}
