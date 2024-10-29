package javaweb.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javaweb.Entity.Cart;
import javaweb.Entity.CartItem;
import javaweb.Entity.MyUser;
import javaweb.Entity.Product;
import javaweb.Repository.CartItemRepository;
import javaweb.Repository.CartRepository;
import javaweb.Repository.MyUserRepository;
import javaweb.Repository.ProductRepository;

@Service
public class CartService {
	@Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private MyUserRepository myuserRepository;
    
    
    public Cart getCartByUsername(String username) {
    	MyUser user=myuserRepository.findByUsername(username).get();
        return cartRepository.findByUser(user);
    }

    public void addProductToCart(String username, Long productId) {
    	MyUser user=myuserRepository.findByUsername(username).get();
        Cart cart = cartRepository.findByUser(user);
        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
            cartRepository.save(cart); 
        }

        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new RuntimeException("Sản phẩm không tồn tại"));

    	CartItem item = cartItemRepository.findByUsernameAndProductId(username, productId);  
    	if (item!=null) {	
        	if (item.getQuantity() + 1 > product.getProductQuantity()) {
                throw new RuntimeException("Số lượng sản phẩm trong giỏ hàng đã vượt quá số lượng tồn kho.");
            }
            item.setQuantity(item.getQuantity() + 1); 
            cartItemRepository.save(item);
            return;
        }
       
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setQuantity(1); 

        cart.getItems().add(cartItem);
        cartItemRepository.save(cartItem);
        cartRepository.save(cart); 
    }
    
    public void removeProductFromCart(String username, Long productId) {
        MyUser user = myuserRepository.findByUsername(username).get();
        Cart cart = cartRepository.findByUser(user);
    	CartItem item = cartItemRepository.findByUsernameAndProductId(username, productId);    
        if (item.getQuantity() == 1) {
            cart.getItems().remove(item);
            cartItemRepository.delete(item);
            return;
        }
        item.setQuantity(item.getQuantity() - 1);
        cartItemRepository.save(item);
        return;
    }
    
    
    public void deleteProductFromCart(String username, Long productId) {
        MyUser user = myuserRepository.findByUsername(username).get();
        Cart cart = cartRepository.findByUser(user);
    	CartItem item = cartItemRepository.findByUsernameAndProductId(username, productId);
    	cart.getItems().remove(item);
        cartItemRepository.delete(item);
        return;

    }

}
