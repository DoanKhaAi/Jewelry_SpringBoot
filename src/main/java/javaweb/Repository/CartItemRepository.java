package javaweb.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javaweb.Entity.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
	 
    @Query("SELECT ci FROM CartItem ci WHERE ci.cart.user.username = :username AND ci.product.productID = :productId")
    CartItem findByUsernameAndProductId(@Param("username") String username, @Param("productId") Long productId);
}
