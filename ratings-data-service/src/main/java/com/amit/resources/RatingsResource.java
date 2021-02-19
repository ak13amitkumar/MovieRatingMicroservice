package com.amit.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amit.models.Rating;
import com.amit.models.UserRating;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsResource {
	
	@RequestMapping("/{movieId}")
	public Rating getMovieRating(@PathVariable String movieId) {
		
		return new Rating(movieId,5);
	}
	
	@RequestMapping("/users/{userId}")
	public UserRating getUserRating(@PathVariable String userId) {
		
		List<Rating> ratings=Arrays.asList(
				new Rating("1111",5),
				new Rating("2222",4)
				);
		
		UserRating userRating=new UserRating();
		userRating.setUserRating(ratings);
		return userRating;
	}

}
