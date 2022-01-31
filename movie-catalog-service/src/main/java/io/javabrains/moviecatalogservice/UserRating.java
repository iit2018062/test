package io.javabrains.moviecatalogservice;

import io.javabrains.moviecatalogservice.Rating;
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

