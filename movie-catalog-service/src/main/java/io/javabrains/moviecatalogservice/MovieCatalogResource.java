package io.javabrains.moviecatalogservice;
import io.javabrains.moviecatalogservice.CatalogItem;
import io.javabrains.moviecatalogservice.Rating;
import io.javabrains.moviecatalogservice.Movie;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
    @RequestMapping("/{userId}")
    public List<CatalogItem>getCatalog(@PathVariable("userId") String userId){
        RestTemplate restTemplate = new RestTemplate();
        //Movie movie = restTemplate.getForObject("http://localhost:8082/movies/foo",Movie.class);
        List<Rating> ratings = Arrays.asList(
               new Rating("1234",4),
                new Rating("2347",3),
                new Rating("8989",4),
                new Rating("9897",5)
        );
       return ratings.stream().map(rating -> {
                Movie movie=   restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(),Movie.class);
                   return new CatalogItem(movie.getName(), "Test",rating.getRating());
               })
                .collect(Collectors.toList());
        //get all rated movie id
        //for each movie id call movie information service
        //put them all together
            //  return Collections.singletonList(
              //        new CatalogItem(  "Transformers",  "test",  4 )
              //);
    }

}
