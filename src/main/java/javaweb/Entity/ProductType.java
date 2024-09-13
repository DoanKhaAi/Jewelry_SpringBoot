package javaweb.Entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name="product_type")
public class ProductType {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="product_type_id")
	private Long productTypeID;
	
	@Column(name="product_type_name", length=50, nullable=false)
	private String productTypeName;
	
	@Column(name="enabled", nullable=false)
	private Boolean enabled;
	
	@OneToMany(mappedBy = "productType", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products;
	
	public ProductType() {

	}


	public ProductType(Long productTypeID, String productTypeName, Boolean enabled, List<Product> products) {
		this.productTypeID = productTypeID;
		this.productTypeName = productTypeName;
		this.enabled = enabled;
		this.products = products;
	}


	public Long getProductTypeID() {
		return productTypeID;
	}


	public void setProductTypeID(Long productTypeID) {
		this.productTypeID = productTypeID;
	}


	public String getProductTypeName() {
		return productTypeName;
	}


	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}


	public Boolean getEnabled() {
		return enabled;
	}


	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}


	public List<Product> getProducts() {
		return products;
	}


	public void setProducts(List<Product> products) {
		this.products = products;
	}



	
	
}
