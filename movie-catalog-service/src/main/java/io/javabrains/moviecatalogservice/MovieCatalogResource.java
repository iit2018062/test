package io.javabrains.moviecatalogservice;
import com.netflix.discovery.DiscoveryClient;
import io.javabrains.moviecatalogservice.CatalogItem;
import io.javabrains.moviecatalogservice.Rating;
import io.javabrains.moviecatalogservice.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration;
import org.springframework.boot.web.reactive.function.client.WebClientCustomizer;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
//import org.springframework.web.reactive.function.client.WebClient;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
    @Autowired
    private RestTemplate restTemplate;

  // @Autowired
    //private DiscoveryClient discoveryClient;
   // @Autowired
   // private WebClient.Builder WebClientBuilder;
    @RequestMapping("/{userId}")
    public List<CatalogItem>getCatalog(@PathVariable("userId") String userId){
        //RestTemplate restTemplate = new RestTemplate();
       /** List<Rating> ratings = Arrays.asList(
                new Rating("1234",4),
                new Rating("2347",3),
                new Rating("8989",4),
                new Rating("9897",5)
        );**/
       UserRating ratings = restTemplate.getForObject("http://user-rating-service/ratingsdata/users/"+userId,UserRating.class);
        //Movie movie = restTemplate.getForObject("http://localhost:8082/movies/"+userId,Movie.class);


       return ratings.getUserRating().stream().map(rating -> {
                Movie movie =   restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(),Movie.class);
           //webclinetbiuilder build uri is url, retrieve is go to fect
           // bodyto mono is whatever body u get create instance of body class
           //mono is reactive way of saying that u will get features may be not rightaway but u will get it

          /** Movie movie =  WebClientBuilder.build()
                   .get()
                   .uri("http://localhost:8082/movies/" + rating.getMovieId())
                   .retrieve()
                   .bodyToMono(Movie.class)
                   .block();
           **/
                   return new CatalogItem(movie.getName(), "Test",rating.getRating());
               })
                .collect(Collectors.toList());

    }

}
