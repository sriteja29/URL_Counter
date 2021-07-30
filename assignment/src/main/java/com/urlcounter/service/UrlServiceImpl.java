package com.urlcounter.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.urlcounter.exception.UrlException;
import com.urlcounter.model.Urls;

@Service
public class UrlServiceImpl implements UrlService{
	
	public Urls storeUrl(String url) throws UrlException{

			for(Urls u : Urls.urlList) {
				if(u.getUrl().equals(url))
					throw new UrlException("SERVICE.URLEXIST");
			}
			Urls urlNew = new Urls(url);
			return urlNew;
	}
	
	public Integer get(String url) throws UrlException {
		Integer result = 0;
		for(Urls u : Urls.urlList) {
			if(u.getUrl().equals(url)) {
				u.setCount(u.getCount()+1);
				u.setLastAccessDate(LocalDateTime.now());
				result = u.getUrlShortKey();
			}
		}
		System.out.println(result);
		if(result==0)
			throw new UrlException("SERVICE.URLDOESNOTEXISTS");
		
		return result;
	}
	
	public Integer count(String url) throws UrlException {
		Integer count = -1;

		for(Urls u : Urls.urlList) {
			if(u.getUrl().equals(url))
			count = u.getCount();
		}
		if(count == -1)
			throw new UrlException("SERVICE.URLDOESNOTEXISTS");
		return count;
	}
	
	public List<Urls> list(int page, int size) throws UrlException {
		int total = Urls.urlList.size();
		List<Urls> result = new ArrayList<Urls>();
		
		if(page<1 || size<1) {
			throw new UrlException("SERVICE.PAGEANDSIZE");
		}
		else if(total!=0 && total > (page-1)*size) {
			for(int i=0;i<size;i++) {
				if(total<= (page-1)*size+ i) {
					break;
				}
				result.add(Urls.urlList.get((page-1)*size + i));
			}
		}
		else {
			throw new UrlException("SERVICE.PAGEANDSIZE");
		}
		return result;
	}
	
	public LocalDateTime accessDate(String url) throws UrlException {
		
		LocalDateTime accessdate = null;

		for(Urls u : Urls.urlList) {
			if(u.getUrl().equals(url)) {
				if(u.getLastAccessDate()==null)
					throw new UrlException("SERVICE.NEVERACCESSED");
				accessdate = u.getLastAccessDate();
			}
		}
		if(accessdate == null)
			throw new UrlException("SERVICE.URLDOESNOTEXISTS");
		return accessdate;
	}
}
