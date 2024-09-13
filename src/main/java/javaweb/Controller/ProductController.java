package javaweb.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javaweb.Entity.Product;
import javaweb.Service.ProductService;
import javaweb.Service.ProductTypeService;

@Controller
@RequestMapping("/admin/product")
public class ProductController {
	@Autowired
    private ProductService service;
	
	@Autowired
    private ProductTypeService serviceProductType;

    @GetMapping("/list")
    public String list(Model model) {
    	List<Product> listProducts=service.listAllTrue();
    	model.addAttribute("listProducts",listProducts);
		return "/product/admin/listProduct";
    }

    @GetMapping("/listHidden")
    public String listHidden(Model model) {
    	List<Product> listProducts=service.listAllFalse();
    	model.addAttribute("listProducts",listProducts);
		return "/product/admin/listProductHidden";
    }
    
    @GetMapping("/new")
	public String showNewForm(Model model) {
		model.addAttribute("product", new Product());
		model.addAttribute("listProductTypes", serviceProductType.listAllTrue());
		model.addAttribute("pageTitle", "Thêm sản phẩm mới");
		return "/product/admin/formProduct";
	}
    
    
    @GetMapping("/update/{id}")
	public String showUpdateForm(@PathVariable("id") Long id, Model model) {
			Product product=service.getById(id);
			model.addAttribute("product", product);
			model.addAttribute("listProductTypes", serviceProductType.listAllTrue());
			model.addAttribute("pageTitle", "Chỉnh sửa sản phẩm (ID: "+id+")");
			return "/product/admin/formProduct";
		
	} 
    
    @PostMapping("/save")
    public String create(Product product, @RequestParam("imageFile")MultipartFile imageFile, RedirectAttributes ra, Model model) 
    				throws IOException {
        String result=service.save(product, imageFile);
        if (result.equals("OK")) {
        	ra.addFlashAttribute("message", "Lưu sản phẩm thành công");
        	return "redirect:/admin/product/list";
        }
        else {
        	model.addAttribute("pageTitle", "Thêm mới sản phẩm");
        	model.addAttribute("listProductTypes", serviceProductType.listAllTrue());
        	model.addAttribute("error", "Không được phép để trống hình ảnh sản phẩm");
        	return "/product/admin/formProduct";
        }
		
    }
    

    @GetMapping("/hidden/{id}")
    public String hidden(@PathVariable("id") Long id, RedirectAttributes ra) {      
        service.hidden(id);
        ra.addFlashAttribute("message", "Ẩn sản phẩm thành công");
        return "redirect:/admin/product/list";
    }

    @GetMapping("/restore/{id}")
    public String restore(@PathVariable("id") Long id, RedirectAttributes ra) {
        service.restore(id);
        ra.addFlashAttribute("message", "Phục hồi sản phẩm thành công");
        return "redirect:/admin/product/listHidden";
    }
//
//    @PostMapping("/delete/{id}")
//    public String delete(@PathVariable Long id, RedirectAttributes ra) {
//       service.delete(id);
//       ra.addFlashAttribute("message", "Xóa vĩnh viễn sản phẩm thành công");
//       return "redirect:/admin/product/listHidden";    
//    }
}
