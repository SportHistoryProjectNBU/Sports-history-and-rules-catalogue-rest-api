package com.nbuproject.Sportshistoryandrulescataloguerestapi.repositories;

import com.nbuproject.Sportshistoryandrulescataloguerestapi.entities.Subscribe;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.ArrayList;

public interface SubscribeRepository extends PagingAndSortingRepository<Subscribe, String> {
    ArrayList<Subscribe> findAll();
    Subscribe findByMatchIdAndUserId(String matchId, String userId);
    ArrayList<Subscribe> findAllByUserId(String userId);
}
