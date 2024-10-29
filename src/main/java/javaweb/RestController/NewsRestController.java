package javaweb.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javaweb.Entity.News;
import javaweb.Service.NewsService;


@RestController
@CrossOrigin(origins = "http://localhost:3000") 
public class NewsRestController {
	
	@Autowired
    private NewsService service;

    @GetMapping ("api/news")
    public List<News> getAllNews() {
        return service.listAllTrue();
    }
}
