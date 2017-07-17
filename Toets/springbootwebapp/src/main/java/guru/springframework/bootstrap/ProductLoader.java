package guru.springframework.bootstrap;

import guru.springframework.domain.Customer;
import guru.springframework.domain.Product;
import guru.springframework.repositories.CustomerRepository;
import guru.springframework.repositories.ProductRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProductLoader implements ApplicationListener<ContextRefreshedEvent> {

    private ProductRepository productRepository;
    private CustomerRepository customerRepository;

    private Logger log = Logger.getLogger(ProductLoader.class);

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        Product shirt = new Product();
        shirt.setDescription("Evil Java");
        shirt.setPrice(new BigDecimal("18.95"));
        shirt.setImageUrl("http://cdn.quotesgram.com/img/76/60/66849369-mens-round-neck-black-funny-t-shirt-java-evil-900x900.jpg");
        shirt.setProductId("235268845711068308");
        productRepository.save(shirt);

        log.info("Saved Shirt - id: " + shirt.getId());

        Product shirt2 = new Product();
        shirt2.setDescription("Let's save time");
        shirt2.setImageUrl("https://ih1.redbubble.net/image.253091624.3999/fc,550x550,charcoal_heather.u2.jpg");
        shirt2.setProductId("168639393495335947");
        shirt2.setPrice(new BigDecimal("11.95"));
        productRepository.save(shirt2);

        log.info("Saved Mug - id:" + shirt2.getId());

        Customer customer = new Customer();
        customer.setFirstName("Karel");
        customer.setLastName("van Osselen");
        customer.setAddress("Hier");
        customerRepository.save(customer);
    }
}
