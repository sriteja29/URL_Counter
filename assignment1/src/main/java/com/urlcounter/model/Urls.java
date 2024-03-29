package com.urlcounter.model;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "URLCOUNTERTABLE")
public class Urls {
	
	@Id
	private String url;
	
	private Integer count=0;
	
	private Integer urlShortKey;
	
	private LocalDateTime lastAccessDate;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public LocalDateTime getLastAccessDate() {
		return lastAccessDate;
	}

	public void setLastAccessDate(LocalDateTime lastAccessDate) {
		this.lastAccessDate = lastAccessDate;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getUrlShortKey() {
		return urlShortKey;
	}

	public void setUrlShortKey(Integer urlShortKey) {
		this.urlShortKey = urlShortKey;
	}
	


}
