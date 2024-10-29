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
	
	public void save(ProductType productType) {
		if (productType.getProductTypeID() != null) {
		    ProductType productType_update = repo.findById(productType.getProductTypeID())
		            .orElseThrow(() -> new RuntimeException("Không tìm thấy loại sản phẩm"));
		    productType_update.setProductTypeName(productType.getProductTypeName());
		    repo.save(productType_update);    
		} else {
		    productType.setEnabled(true);
		    repo.save(productType);
		}

    }
	
	public ProductType getById(Long id) {
	    return repo.findById(id).orElse(null);
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
    

}
