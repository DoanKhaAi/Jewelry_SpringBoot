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

import javaweb.Entity.News;
import javaweb.Service.NewsService;

@Controller
@RequestMapping("/admin/news")
public class NewsController {
	@Autowired
    private NewsService service;
	
	@GetMapping("/list")
	public String list(Model model) {
	    List<News> listNews = service.listAllTrue();
	    model.addAttribute("listNews", listNews);
	    return "/news/listNews";
	}

	@GetMapping("/listHidden")
	public String listHidden(Model model) {
	    List<News> listNews = service.listAllFalse();
	    model.addAttribute("listNews", listNews);
	    return "/news/listNewsHidden";
	}

	@GetMapping("/new")
	public String showNewForm(Model model) {
	    model.addAttribute("news", new News());
	    model.addAttribute("pageTitle", "Thêm tin tức mới");
	    return "/news/formNews";
	}

	@GetMapping("/update/{id}")
	public String showUpdateForm(@PathVariable("id") Long id, Model model) {
	    News news = service.getById(id);
	    model.addAttribute("news", news);
	    model.addAttribute("pageTitle", "Chỉnh sửa tin tức (ID: " + id + ")");
	    return "/news/formNews";
	}

	@PostMapping("/save")
	public String create(News news, @RequestParam("imageFile") MultipartFile imageFile, RedirectAttributes ra, Model model)
	        				throws IOException {
	    	service.save(news, imageFile);
	        ra.addFlashAttribute("message", "Lưu tin tức thành công");
	        return "redirect:/admin/news/list";
	}

	@GetMapping("/hidden/{id}")
	public String hidden(@PathVariable("id") Long id, RedirectAttributes ra) {
	    service.hidden(id);
	    ra.addFlashAttribute("message", "Ẩn tin tức thành công");
	    return "redirect:/admin/news/list";
	}

	@GetMapping("/restore/{id}")
	public String restore(@PathVariable("id") Long id, RedirectAttributes ra) {
	    service.restore(id);
	    ra.addFlashAttribute("message", "Phục hồi tin tức thành công");
	    return "redirect:/admin/news/listHidden";
	}

}
