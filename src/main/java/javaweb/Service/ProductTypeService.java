package javaweb.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javaweb.Entity.ProductType;
import javaweb.Repository.ProductTypeRepository;


@Service
public class ProductTypeService {
	@Autowired
	private ProductTypeRepository repo;
	
	public List<ProductType> listAllTrue(){
		return (List<ProductType>) repo.findByEnabledTrue();
	}
	
	public List<ProductType> listAllFalse(){
		return (List<ProductType>) repo.findByEnabledFalse();
	}	
	
	public ProductType save(ProductType productType) {
		productType.setEnabled(true);
        return repo.save(productType);
    }
	
	public ProductType getById(Long id) {
	    return repo.findById(id).orElse(null);
	}

	public ProductType update(ProductType productType) {   
	    ProductType existingProductType = repo.findById(productType.getProductTypeID()).get();
        existingProductType.setProductTypeName(productType.getProductTypeName());
	    return repo.save(existingProductType);
	}
	
    public void hidden(Long id) {
	    if (repo.existsById(id)) {
	    	ProductType existingProductType = repo.findById(id).get();
	    	existingProductType.setEnabled(false);
	    	repo.save(existingProductType);
	     }
	}
    
    public void restore(Long id) {
	    if (repo.existsById(id)) {
	    	ProductType existingProductType = repo.findById(id).get();
	    	existingProductType.setEnabled(true);
	    	repo.save(existingProductType);
	     }
	}
    
    public void delete(Long id) {
	    if (repo.existsById(id)) {
	    	repo.deleteById(id);
	     }
	}
    

}
