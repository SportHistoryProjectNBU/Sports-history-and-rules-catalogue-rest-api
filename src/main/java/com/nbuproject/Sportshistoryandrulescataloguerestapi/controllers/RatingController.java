package com.nbuproject.Sportshistoryandrulescataloguerestapi.controllers;

import com.nbuproject.Sportshistoryandrulescataloguerestapi.entities.Rating;
import com.nbuproject.Sportshistoryandrulescataloguerestapi.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * Created by user on 1/21/2018.
 */
@RestController
@CrossOrigin("*")
@RequestMapping(path = "/rating")
public class RatingController {

    @Autowired
    private RatingRepository ratingRepository;

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<ArrayList<Rating>> getUserRating(@RequestBody Rating rating) {
        return ResponseEntity.ok(ratingRepository.findAllByMatchId(rating.getMatchId()));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Rating> addRating(@RequestBody Rating rating) {
        ratingRepository.save(rating);
        return  ResponseEntity.ok(rating);
    }
}
