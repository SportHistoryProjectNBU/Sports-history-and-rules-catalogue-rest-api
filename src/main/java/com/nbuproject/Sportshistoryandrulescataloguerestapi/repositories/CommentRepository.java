package com.nbuproject.Sportshistoryandrulescataloguerestapi.repositories;

import com.nbuproject.Sportshistoryandrulescataloguerestapi.entities.Comment;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Created by user on 1/21/2018.
 */
public interface CommentRepository extends PagingAndSortingRepository<Comment, String> {

   ArrayList<Comment> findAllByMatchIdOrderByDate(String matchId);
   Comment findCommentByUserNameAndDate(String userName, Timestamp date);
}
