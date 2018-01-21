package com.nbuproject.Sportshistoryandrulescataloguerestapi.repositories;

import com.nbuproject.Sportshistoryandrulescataloguerestapi.entities.Rating;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.ArrayList;

/**
 * Created by user on 1/21/2018.
 */
public interface RatingRepository extends PagingAndSortingRepository<Rating, String> {
    
    ArrayList<Rating> findAllByMatchId(String matchId);
}
