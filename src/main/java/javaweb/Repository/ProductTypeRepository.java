package javaweb.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javaweb.Entity.ProductType;


@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType, Long>{
	List<ProductType> findByEnabledTrue();
	List<ProductType> findByEnabledFalse();
}
