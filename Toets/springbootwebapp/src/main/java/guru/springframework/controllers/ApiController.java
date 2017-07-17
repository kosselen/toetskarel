package guru.springframework.controllers;

import guru.springframework.domain.Product;
import guru.springframework.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by KOSSELEN on 17-7-2017.
 */

@RestController
public class ApiController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Transactional
    @RequestMapping(value = "/api/products", method = RequestMethod.GET)
    public List<Product> productList(){
        List productlist = (List) productService.listAllProducts();
        System.out.println(productlist);
        return productlist;
    }

    @Transactional
    @RequestMapping(value = "/api/showproduct", method = RequestMethod.GET)
    public Product showProduct(@RequestParam Integer id){
        Product product = productService.getProductById(id);
        return product;
    }

    @Transactional
    @RequestMapping(value = "/api/product/edit", method = RequestMethod.POST)
    public Product edit(@RequestParam Product product){
        productService.saveProduct(product);
        return product;
    }

    @Transactional
    @RequestMapping(value = "/api/product/delete}", method = RequestMethod.GET)
    public boolean delete(@RequestParam Integer id) {
        boolean succes=false;
        if (productService.getProductById(id).getId()>0){
            productService.deleteProductById(id);
            succes=true;
        }
        return succes;
    }

    @Transactional
    @RequestMapping(value = "/api/product/new", method = RequestMethod.GET)
    public String newProduct(@RequestBody Model model){
        model.addAttribute("product", new Product());
        return "productform";
    }

    @Transactional
    @RequestMapping(value = "/api/product", method = RequestMethod.POST)
    public String saveProduct(@RequestBody Product product){

        productService.saveProduct(product);

        return "redirect:/product/" + product.getId();
    }
}
