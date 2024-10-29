package javaweb.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javaweb.Entity.Cart;
import javaweb.Service.CartService;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "http://localhost:3000") 
public class CartRestController {

    @Autowired
    private CartService cartService;
    
    @GetMapping("/{username}")
    public Cart getCartByUsername(@PathVariable String username) {
        return cartService.getCartByUsername(username);
    }

    @PostMapping("/add/{username}/{productId}")
    public void addProductToCart(@PathVariable String username, @PathVariable Long productId) {
        cartService.addProductToCart(username, productId);
    }
    
    @PostMapping("/remove/{username}/{productId}")
    public void removeProductfromCart(@PathVariable String username, @PathVariable Long productId) {
        cartService.removeProductFromCart(username, productId);
    }
    
    @DeleteMapping("/delete/{username}/{productId}")
    public void deleteProductfromCart(@PathVariable String username, @PathVariable Long productId) {
        cartService.deleteProductFromCart(username, productId);
    }
    

   
}