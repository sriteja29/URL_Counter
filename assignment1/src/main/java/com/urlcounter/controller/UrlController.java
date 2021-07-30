package com.urlcounter.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.urlcounter.exception.UrlException;
import com.urlcounter.model.Urls;
import com.urlcounter.service.UrlService;


@RestController
@RequestMapping(value = "/")
public class UrlController {
	
	@Autowired
    private UrlService urlService;
	
	@Autowired
	private Environment environment;
	
	@PostMapping(value = "/storeurl")
    public ResponseEntity<String> storeUrl(@RequestParam String url) {
		
		//Date FUNCTIONALITY
		try {
			Urls urls = urlService.storeUrl(url);
			String successMessage = environment.getProperty("API.INSERT_SUCCESS") + urls.getUrl();
			return new ResponseEntity<>(successMessage, HttpStatus.CREATED);   
		}
		catch(UrlException exception) {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, environment.getProperty(exception.getMessage()), exception);
		}
    }
	
	
    @GetMapping(value = "/get")
    public ResponseEntity<Integer> get(@RequestParam  String url){
    	try {
	        Integer urlKey = urlService.get(url);
	        return new ResponseEntity<>(urlKey, HttpStatus.OK);
    	}
    	catch(UrlException exception) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, environment.getProperty(exception.getMessage()), exception);
		}
    }
    
    
    @GetMapping(value = "/count")
    public ResponseEntity<Integer> count(@RequestParam  String url){
    	try {
	        Integer count = urlService.count(url);
	        return new ResponseEntity<>(count, HttpStatus.OK);
    	}
    	catch(UrlException exception) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, environment.getProperty(exception.getMessage()), exception);
		}
    }
    
    @GetMapping(value = "/list")
    public ResponseEntity<List<Urls>> list(@RequestParam  Integer page,@RequestParam Integer size){
    	List<Urls> listOfUrls = new ArrayList<>();
    	try {
	        listOfUrls = urlService.list(page,size);
	        return new ResponseEntity<>(listOfUrls, HttpStatus.OK);
    	}
    	catch(UrlException exception) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, environment.getProperty(exception.getMessage()), exception);
		}
    }
    
    @GetMapping(value = "/lastAccessDate")
    public ResponseEntity<LocalDateTime> accessDates(@RequestParam  String url){
    	LocalDateTime lastAccessDate;
    	try {
    		lastAccessDate = urlService.accessDate(url);
	        return new ResponseEntity<>(lastAccessDate, HttpStatus.OK);
    	}
    	catch(UrlException exception) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, environment.getProperty(exception.getMessage()), exception);
		}
    }

}
