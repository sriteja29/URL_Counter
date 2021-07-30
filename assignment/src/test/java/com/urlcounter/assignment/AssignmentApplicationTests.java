package com.urlcounter.assignment;


import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.urlcounter.exception.UrlException;
import com.urlcounter.model.Urls;
import com.urlcounter.service.UrlService;

@SpringBootTest
class AssignmentApplicationTests {
	
	@Autowired
	UrlService urlService;
	
	@BeforeEach
	void listClear() {
		Urls.urlList = new ArrayList<>();
	}

	@Test
	void storeUrlValid() throws UrlException{
		String url = "google.com";
		Urls urls =  urlService.storeUrl(url);
		Assertions.assertEquals(url, urls.getUrl());
	}
	
	@Test
	void storeUrlInValid() throws UrlException {
		String url = "google.com";
		urlService.storeUrl(url);
		String url1 = "google.com";
		Exception exception = Assertions.assertThrows(UrlException.class, () -> {
			urlService.storeUrl(url1);
	    });
		
		String expectedMessage = "SERVICE.URLEXIST";
		String actualMessage = exception.getMessage();
		Assertions.assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	void getUrlKeyValid() throws UrlException {
		String url = "google.com";
		urlService.storeUrl(url);
		Integer key = urlService.get(url);
		Assertions.assertTrue(key>=100000);
	}
	
	@Test
	void getUrlKeyInValid() {
		String url1 = "google.com";
		Exception exception = Assertions.assertThrows(UrlException.class, () -> {
			urlService.get(url1);
	    });
		
		String expectedMessage = "SERVICE.URLDOESNOTEXISTS";
		String actualMessage = exception.getMessage();
		Assertions.assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	void countUrlValid() throws UrlException {
		String url = "google.com";
		urlService.storeUrl(url);
		urlService.get(url);
		urlService.get(url);
		urlService.get(url);
		urlService.get(url);
		Assertions.assertEquals(4, urlService.count(url));
	}
	
	@Test
	void countUrlInValid() throws UrlException {

		String url1 = "google.com";
		Exception exception = Assertions.assertThrows(UrlException.class, () -> {
			urlService.count(url1);
	    });
		
		String expectedMessage = "SERVICE.URLDOESNOTEXISTS";
		String actualMessage = exception.getMessage();
		Assertions.assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	void listCallValid() throws UrlException {
		List<Urls> urlList = new ArrayList<>();
		for(int i = 0;i<5;i++) {
			String url = "google"+ i +".com";
			urlService.storeUrl(url);
		}
		urlList = urlService.list(2,2);
		Assertions.assertTrue(Urls.urlList.containsAll(urlList));
	}
	
	@Test
	void listCallInValid() {

		Exception exception = Assertions.assertThrows(UrlException.class, () -> {
			urlService.list(2,3);
	    });
		
		String expectedMessage = "SERVICE.PAGEANDSIZE";
		String actualMessage = exception.getMessage();
		Assertions.assertEquals(expectedMessage, actualMessage);
	}
}
