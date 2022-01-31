package io.javabrains.ratingdataservice;

import io.javabrains.ratingdataservice.Rating.Rating;
import java.util.*;
public class UserRating {
    private List<Rating>userRating;
    public List<Rating>getUserRating(){
        return userRating;
    }
    public void setUserRating(List<Rating>userRating){
        this.userRating = userRating;
    }
}
