package com.urlcounter.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.urlcounter.exception.UrlException;
import com.urlcounter.model.Urls;
import com.urlcounter.repository.UrlRepository;

@Service
@Transactional
public class UrlServiceImpl implements UrlService{
	
	@Autowired
	UrlRepository urlRepository;
	
	public Urls storeUrl(String url) throws UrlException{

		Optional<Urls> urls = urlRepository.findByUrl(url);
		if(urls.isEmpty())
			throw new UrlException("SERVICE.URLEXISTS");
		Urls urlNew = new Urls();
		urlNew.setUrl(url);
		urlRepository.save(urlNew);
		return urlNew;		
	}
	
	public Integer get(String url) throws UrlException {

		Optional<Urls> urls =  urlRepository.findByUrl(url);
		Urls u =  urls.orElseThrow(() ->new UrlException("SERVICE.URLDOESNOTEXISTS"));
		
		u.setCount(u.getCount()+1);
		u.setLastAccessDate(LocalDateTime.now());
		
		urlRepository.save(u);
		
		return u.getUrlShortKey();
	}
	
	public Integer count(String url) throws UrlException {
		
		Optional<Urls> urls =  urlRepository.findByUrl(url);
		Urls u =  urls.orElseThrow(() ->new UrlException("SERVICE.URLDOESNOTEXISTS"));
		return u.getCount();
	}
	
	public List<Urls> list(int page, int size) throws UrlException {
		
		List<Urls> result = new ArrayList<Urls>();
		
		if(page<=0 || size <=0)
			throw new UrlException("SERVICE.PAGEANDSIZE");
		
		Pageable pageable = PageRequest.of(page, size);		
		Page<Urls> pages = urlRepository.findAll(pageable);		
		result = pages.getContent();

		return result;
	}
	
	public LocalDateTime accessDate(String url) throws UrlException {
		
		Optional<Urls> urls =  urlRepository.findByUrl(url);
		Urls u =  urls.orElseThrow(() ->new UrlException("SERVICE.URLDOESNOTEXISTS"));
		if(u.getCount()==0) 
			throw new UrlException("SERVICE.NEVERACCESSED");
		return u.getLastAccessDate();
	}
}
