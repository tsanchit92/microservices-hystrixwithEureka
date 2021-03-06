package micro.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.HystrixThreadPoolProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import micro.services.model.CatalogItem;
import micro.services.model.Movie;
import micro.services.model.Rating;

@Service
public class MovieInfo {
	@Autowired
	private RestTemplate rt;

	@HystrixCommand(fallbackMethod = "getFallBackCatalogItem",
				commandProperties= {
						@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
						@HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value="5"),
						@HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value="50"),
						@HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value="5000")
				},
				threadPoolKey = "movieINfoPool",
				threadPoolProperties= {
						@HystrixProperty(name="coreSize",value = "20"),
						@HystrixProperty(name="maxQueueSize",value = "10"),
				}
	) 
	public CatalogItem getCatalogItem(Rating rating) {
		Movie movie = rt.getForObject("http://movieinfoservice/movies/" + rating.getMovieId(), Movie.class);
		return new CatalogItem(movie.getName(), "XXX", rating.getRating());
	}

	private CatalogItem getFallBackCatalogItem(Rating rating) {

		return new CatalogItem("movie name not found", "", rating.getRating());

	}
}
