package guru.springframework.services;

/**
 * Created by KOSSELEN on 17-7-2017.
 */

import guru.springframework.domain.Customer;

public interface CustomerService {
    Iterable<Customer> listAllCustomers();

    Customer getCustomerById(Integer id);

    Customer saveCustomer(Customer customer);

    void deleteCustomerById(Integer id);
}
