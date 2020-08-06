package com.codeup.springblog.repositories;

import com.codeup.springblog.models.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdRepository extends JpaRepository<Ad, Long> {
	
	// derived queries using the JPA query builder
	Ad findByTitle(String title);
	Ad findFirstByOrderByTitleAsc();
	
	// custom examples using the query annotation
	// examples using JPQL / HQL
	@Query("FROM Ad a WHERE a.id LIKE ?1")
	Ad getAdById(long id);
	
	@Query("SELECT title FROM Ad WHERE length(title) < 10 ")
	List<String> getAdsOfCertainTitleLength();
	
	// using a native query
	@Query(nativeQuery = true, value = "SELECT title FROM ads WHERE length(title) < 10")
	List<String> getAdsOfCertainTitleLengthNative();
}
