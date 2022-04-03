package micro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableCircuitBreaker
@EnableHystrixDashboard
public class MovieCatalogServiceNewApplication {
	@LoadBalanced
	@Bean
	public RestTemplate getRestTemplate()
	{
		HttpComponentsClientHttpRequestFactory hchrf=new HttpComponentsClientHttpRequestFactory();
		hchrf.setConnectTimeout(3000);
		return new RestTemplate(hchrf);
	}

	public static void main(String[] args) {
		SpringApplication.run(MovieCatalogServiceNewApplication.class, args);
	}

}
