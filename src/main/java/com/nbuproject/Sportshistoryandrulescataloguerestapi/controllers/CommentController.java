package com.nbuproject.Sportshistoryandrulescataloguerestapi.controllers;

import com.nbuproject.Sportshistoryandrulescataloguerestapi.entities.Comment;
import com.nbuproject.Sportshistoryandrulescataloguerestapi.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Created by user on 1/21/2018.
 */
@RestController
@CrossOrigin("*")
@RequestMapping(path = "/comments")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Comment> addComment(@RequestBody Comment comment){
        Date date = new Date();
        comment.setDate(new java.sql.Timestamp(date.getTime()));
        commentRepository.save(comment);
        comment = commentRepository.findCommentByUserNameAndDate(comment.getUserName(), comment.getDate());
        return ResponseEntity.ok(comment);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<ArrayList<Comment>> getAllComments(@RequestBody Comment comment){
        return ResponseEntity.ok(commentRepository.findAllByMatchIdOrderByDate(comment.getMatchId()));
    }

}
