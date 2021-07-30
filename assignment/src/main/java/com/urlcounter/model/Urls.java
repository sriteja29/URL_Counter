package com.urlcounter.model;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Urls {
	
	private String url;
	
	private Integer count;
	
	private Integer urlShortKey;
	
	private LocalDateTime lastAccessDate;
	
	static int keyGenerator = 100000;
	public static List<Urls> urlList = new ArrayList<Urls>();
	
	
	public Urls(String url){
		this.url = url;
		this.count = 0;
		urlShortKey = keyGenerator;
		keyGenerator++;
		Urls.urlList.add(this); 
	}

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
}
