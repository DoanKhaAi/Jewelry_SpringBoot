package javaweb.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.transaction.Transactional;
import javaweb.Entity.Product;
import javaweb.Repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository repo;
	public List<Product> listAllTrue() {
        return (List<Product>) repo.findByEnabledTrue();
    }
	
	public Page<Product> findAll(Pageable pageable) {
	        return repo.findByEnabledTrue(pageable);
	}
	
	public Page<Product> findAllByType(Long productTypeID, Pageable pageable) {
        return repo.findByProductType_ProductTypeID(productTypeID, pageable);
}

    public List<Product> listAllFalse() {
        return (List<Product>) repo.findByEnabledFalse();
    }

    public String save(Product product, MultipartFile imageFile) throws IOException {
    	product.setEnabled(true);
    	if (product.getProductID()!=null){
    		Product product_save=repo.findById(product.getProductID()).get();
    		if (!imageFile.isEmpty()) {
    			Path filePath = Paths.get("src/main/resources/static/img/product/", product_save.getProductImage());
    			Files.delete(filePath);
    			String file= saveImage(product.getProductID(), imageFile);
    			product.setProductImage(file);
    		}
    		else product.setProductImage(product_save.getProductImage());
    	}
    	else {
    		if (imageFile.isEmpty()) return "NO";
	        product.setProductImage("temp.jpg");
    		repo.save(product);
	    	String file= saveImage(product.getProductID(), imageFile);
			product.setProductImage(file);
    	}
    	repo.save(product);
    	return "OK";
    }

    public Product getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Product update(Product product) {
        if (repo.existsById(product.getProductID())) {
            Product existingProduct = repo.findById(product.getProductID()).get();
            existingProduct.setProductName(product.getProductName());
            existingProduct.setProductPrice(product.getProductPrice());
            existingProduct.setProductQuantity(product.getProductQuantity());
            existingProduct.setProductDescription(product.getProductDescription());
            existingProduct.setProductType(product.getProductType());
            return repo.save(existingProduct);
        } else {
            return null;
        }
    }

    public void hidden(Long id) {
        if (repo.existsById(id)) {
            Product existingProduct = repo.findById(id).get();
            existingProduct.setEnabled(false);
            repo.save(existingProduct);
        }
    }

    public void restore(Long id) {
        if (repo.existsById(id)) {
            Product existingProduct = repo.findById(id).get();
            existingProduct.setEnabled(true);
            repo.save(existingProduct);
        }
    }

//    public void delete(Long id) {
//        if (repo.existsById(id)) {
//            repo.deleteById(id);
//        }
//    }
    
	@Transactional
	public String saveImage(Long id, MultipartFile imageFile) throws IOException {
	    	if (!imageFile.isEmpty()) {
		    	Path uploadPath = Paths.get("src/main/resources/static/img/product/");
		        String originalFileName = imageFile.getOriginalFilename();
		        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
		        String newFileName = "_product" + id + fileExtension;
		        Path filePath = uploadPath.resolve(newFileName);
		        Files.copy(imageFile.getInputStream(), filePath);
		        return newFileName; 
	        }
	    	else return "no_name.jpg";
	 }
}
