package micro.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import micro.service.model.Rating;
import micro.service.model.UserRating;

@RestController
@RequestMapping("/ratingsdata")
public class RatingdataResource {
	@RequestMapping("/{movieId}")
	public Rating getRating(@PathVariable("movieId") String movieId)
	{
		return new Rating(movieId,4);
	}
	
	@RequestMapping("users/{userId}")
	public UserRating getUserRating(@PathVariable("userId") String userId)
	{
		List<Rating> ratings =Arrays.asList(
				new Rating("1234",4),
				new Rating("1235",3)	
		 		);
		UserRating userRating =new UserRating();
		userRating.setRatings(ratings);
		return userRating;
	}


}
