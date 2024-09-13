package javaweb.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="news")
public class News {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="news_id")
	private Long newsID;
	
	@Column(name="title", length=1000, nullable=false)
	private String title;
	
	@Column(name="content", length=1000, nullable=false)
	private String content;
	
	@Column(name="image", length=20)
	private String image;
	
	@Column(name="enabled", nullable=false)
	private Boolean enabled;
	
	public News() {
	}

	public Long getNewsID() {
		return newsID;
	}



	public void setNewsID(Long newsID) {
		this.newsID = newsID;
	}



	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	

}
