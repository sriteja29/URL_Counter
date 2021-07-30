package com.urlcounter.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.urlcounter.model.Urls;

@Repository
public interface UrlRepository extends JpaRepository<Urls, Integer>{

	Optional<Urls> findByUrl(String url);

}
