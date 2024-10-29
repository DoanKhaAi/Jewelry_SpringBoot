package javaweb.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javaweb.Entity.Cart;
import javaweb.Entity.MyUser;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
	
	Cart findByUser(MyUser user);
}