package com.urlcounter.service;

import java.time.LocalDateTime;
import java.util.List;

import com.urlcounter.exception.UrlException;
import com.urlcounter.model.Urls;

public interface UrlService {
	public Urls storeUrl(String url) throws UrlException;
	public Integer get(String url) throws UrlException;
	public Integer count(String url) throws UrlException;
	public List<Urls> list(int page, int size) throws UrlException;
	public LocalDateTime accessDate(String url) throws UrlException;
}
