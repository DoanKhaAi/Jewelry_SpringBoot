package javaweb.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javaweb.Entity.ProductType;
import javaweb.Service.ProductTypeService;


@RestController
@RequestMapping("/admin/productType")
public class ProductTypeController {
	@Autowired
	private ProductTypeService service;
	
	@GetMapping("/list")
	public List<ProductType> list() {
		return service.listAllTrue();
		
	}
	
	@GetMapping("/listHidden")
	public List<ProductType> listHidden() {
		return service.listAllFalse();	
	}
	
	@PostMapping("/new")
    public ResponseEntity<ProductType> create(@RequestBody ProductType productType) {
        ProductType createdProductType = service.save(productType);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProductType);
    }

	@PutMapping("/update")
    public ResponseEntity<ProductType> update(@RequestBody ProductType productType) {
        ProductType updatedProductType = service.update(productType);
        if (updatedProductType != null) {
            return ResponseEntity.ok(updatedProductType);
        } else {
            return ResponseEntity.notFound().build();
        }

    }
	
	@PutMapping("/hidden/{id}")
    public ResponseEntity<Void> hidden(@PathVariable Long id) {
        if (service.getById(id) != null) {
            service.hidden(id);
            return ResponseEntity.noContent().build(); 
        } else {
            return ResponseEntity.notFound().build(); 
        }
    }
	
	@PutMapping("/restore/{id}")
    public ResponseEntity<Void> restore(@PathVariable Long id) {
        if (service.getById(id) != null) {
            service.restore(id);
            return ResponseEntity.noContent().build(); 
        } else {
            return ResponseEntity.notFound().build(); 
        }
    }
	
	@DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.getById(id) != null) {
            service.delete(id);
            return ResponseEntity.noContent().build(); 
        } else {
            return ResponseEntity.notFound().build(); 
        }
    }
}
