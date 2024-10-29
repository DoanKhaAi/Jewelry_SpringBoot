package javaweb.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javaweb.Entity.ProductType;
import javaweb.Service.ProductTypeService;

@Controller
@RequestMapping("/admin/productType")
public class ProductTypeController {
	@Autowired
	private ProductTypeService service;

	@GetMapping("/list")
	public String list(Model model) {
	    List<ProductType> listProductTypes = service.listAllTrue();
	    model.addAttribute("listProductTypes", listProductTypes);
	    return "/productType/listProductType";
	}

	@GetMapping("/listHidden")
	public String listHidden(Model model) {
	    List<ProductType> listProductTypes = service.listAllFalse();
	    model.addAttribute("listProductTypes", listProductTypes);
	    return "/productType/listProductTypeHidden";
	}

	@GetMapping("/new")
	public String showNewForm(Model model) {
	    model.addAttribute("productType", new ProductType());
	    model.addAttribute("pageTitle", "Thêm loại sản phẩm mới");
	    return "/productType/formProductType";
	}

	@GetMapping("/update/{id}")
	public String showUpdateForm(@PathVariable("id") Long id, Model model) {
	    ProductType productType = service.getById(id);
	    model.addAttribute("productType", productType);
	    model.addAttribute("pageTitle", "Chỉnh sửa loại sản phẩm (ID: " + id + ")");
	    return "/productType/formProductType";
	}

	@PostMapping("/save")
	public String create(ProductType productType, RedirectAttributes ra) {
	    service.save(productType);
	    ra.addFlashAttribute("message", "Lưu loại sản phẩm thành công");
	    return "redirect:/admin/productType/list";
	}

	@GetMapping("/hidden/{id}")
	public String hidden(@PathVariable("id") Long id, RedirectAttributes ra) {
	    service.hidden(id);
	    ra.addFlashAttribute("message", "Ẩn loại sản phẩm thành công");
	    return "redirect:/admin/productType/list";
	}

	@GetMapping("/restore/{id}")
	public String restore(@PathVariable("id") Long id, RedirectAttributes ra) {
	    service.restore(id);
	    ra.addFlashAttribute("message", "Phục hồi loại sản phẩm thành công");
	    return "redirect:/admin/productType/listHidden";
	}

}
