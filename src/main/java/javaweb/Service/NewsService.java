package javaweb.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.transaction.Transactional;
import javaweb.Entity.News;
import javaweb.Repository.NewsRepository;

@Service
public class NewsService {
	@Autowired
	private NewsRepository repo;

	public List<News> listAllTrue() {
	    return (List<News>) repo.findByEnabledTrueOrderByNewsIDDesc();
	}

	public List<News> listAllFalse() {
	    return (List<News>) repo.findByEnabledFalse();
	}

	public void save(News news, MultipartFile imageFile) throws IOException {
	    news.setEnabled(true);
	    if (news.getNewsID() != null) {
	        News news_save = repo.findById(news.getNewsID()).get();
	        if (!imageFile.isEmpty()) {
	            Path filePath = Paths.get("src/main/resources/static/img/news/", news_save.getImage());
	            if (!news_save.getImage().equals("default.jpg")) Files.delete(filePath);
	            String file = saveImage(news.getNewsID(), imageFile);
	            news.setImage(file);
	        } else {
	            news.setImage(news_save.getImage());
	        }
	    } else {
	    	news.setImage("default.jpg");
	        if (!imageFile.isEmpty()) {
		        repo.save(news);
		        String file = saveImage(news.getNewsID(), imageFile);
		        news.setImage(file);
	        }
	    }
	    repo.save(news);
	}

	public News getById(Long id) {
	    return repo.findById(id).orElse(null);
	}

	public News update(News news) {
	    if (repo.existsById(news.getNewsID())) {
	        News existingNews = repo.findById(news.getNewsID()).get();
	        existingNews.setTitle(news.getTitle());
	        existingNews.setContent(news.getContent());
	        return repo.save(existingNews);
	    } else {
	        return null;
	    }
	}

	public void hidden(Long id) {
	    if (repo.existsById(id)) {
	        News existingNews = repo.findById(id).get();
	        existingNews.setEnabled(false);
	        repo.save(existingNews);
	    }
	}

	public void restore(Long id) {
	    if (repo.existsById(id)) {
	        News existingNews = repo.findById(id).get();
	        existingNews.setEnabled(true);
	        repo.save(existingNews);
	    }
	}
	
	@Transactional
	public String saveImage(Long id, MultipartFile imageFile) throws IOException {
	    	if (!imageFile.isEmpty()) {
		    	Path uploadPath = Paths.get("src/main/resources/static/img/news/");
		        String originalFileName = imageFile.getOriginalFilename();
		        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
		        String newFileName = "_news" + id + fileExtension;
		        Path filePath = uploadPath.resolve(newFileName);
		        Files.copy(imageFile.getInputStream(), filePath);
		        return newFileName; 
	        }
	    	else return "default.jpg";
	 }

}
