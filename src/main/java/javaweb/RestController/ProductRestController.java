package javaweb.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javaweb.Entity.Product;
import javaweb.Service.ProductService;

@RestController
@CrossOrigin(origins = "http://localhost:3000") 
public class ProductRestController {

    @Autowired
    private ProductService service;

    
    @GetMapping("/api/product")
    public Page<Product> getProducts(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "3") int size,
                                      @RequestParam Long productTypeID) {
    	
    	if (productTypeID != 0) {
            return service.findAllByType(productTypeID, PageRequest.of(page, size));
        } else {
            return service.findAll(PageRequest.of(page, size));
        }
    }
    
}
