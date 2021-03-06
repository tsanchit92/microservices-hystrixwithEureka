package micro.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import micro.services.model.CatalogItem;
import micro.services.model.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

	
	@Autowired
	MovieInfo movieInfo;

	@Autowired
	UserRatingInfo userRatingInfo;
	
	/*
	 * @Autowired private WebClient.Builder webclientbuilder;
	 */

	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
		UserRating ratings = userRatingInfo.getUserRating(userId);
		return ratings.getRatings().stream().map(rating -> movieInfo.getCatalogItem(rating)).collect(Collectors.toList());

	}

	

	

}
/*
 * Movie movie=webclientbuilder.build() .get()
 * .uri("http://localhost:9000/movies/" + rating.getMovieId()) .retrieve()
 * .bodyToMono(Movie.class) .block();
 */
