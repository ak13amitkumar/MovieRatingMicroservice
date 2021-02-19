package com.amit.resources;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.amit.models.CatalogItem;
import com.amit.models.Movie;
import com.amit.models.Rating;
import com.amit.models.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable String userId){
		
		//get all rated movie IDs
		UserRating ratings=restTemplate.getForObject("http://ratings-data-service/ratingsdata/users/"+userId, UserRating.class);
		
		return ratings.getUserRating().stream().map(rating -> {
			
			//for each movie ID,call movie info service and get details
			Movie movie=restTemplate.getForObject("http://movie-info-service/movies/"+rating.getMovieId(), Movie.class);
			
			//put them all together
			return new CatalogItem(movie.getName(),"Sci-fi",rating.getRating());
			})
				.collect(Collectors.toList());
		
	}
	

}
