package micro.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import micro.services.model.Rating;
import micro.services.model.UserRating;

@Service
public class UserRatingInfo {
	@Autowired
	private RestTemplate rt;
	
	@HystrixCommand(fallbackMethod = "getFallBackUserRating")
	public  UserRating getUserRating(@PathVariable("userId") String userId) {
		return rt.getForObject("http://ratingdataservice/ratingsdata/users/" + userId, UserRating.class);
	}

	public UserRating getFallBackUserRating(@PathVariable("userId") String userId) {
		UserRating userRating = new UserRating();
		userRating.setUserId(userId);
		userRating.setRatings(Arrays.asList(new Rating("0", 0)));
		return userRating;

	}

}
