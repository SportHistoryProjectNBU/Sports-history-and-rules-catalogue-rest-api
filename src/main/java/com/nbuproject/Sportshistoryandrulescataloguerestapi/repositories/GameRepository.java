package com.nbuproject.Sportshistoryandrulescataloguerestapi.repositories;

import com.nbuproject.Sportshistoryandrulescataloguerestapi.entities.Game;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.ArrayList;

/**
 * Created by user on 1/7/2018.
 */
public interface GameRepository extends PagingAndSortingRepository<Game, String> {
    ArrayList<Game> findAll();
    Game findGameById(String id);
    ArrayList<Game> findAllByStatusOrStatus(String status1, String status2);
}
