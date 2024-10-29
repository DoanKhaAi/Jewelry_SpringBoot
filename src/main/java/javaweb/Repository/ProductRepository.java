package javaweb.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javaweb.Entity.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	
	List<Product> findByEnabledTrue();
	
	List<Product> findByEnabledFalse();
	
	Page<Product> findByEnabledTrue(Pageable pageable);
	
	Page<Product> findByProductType_ProductTypeID(Long productTypeID, Pageable pageable);
}
