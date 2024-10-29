package javaweb.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javaweb.Entity.News;

@Repository
public interface NewsRepository extends JpaRepository<News, Long>{
	
	List<News> findByEnabledTrueOrderByNewsIDDesc();
	
	List<News> findByEnabledFalse();
}
