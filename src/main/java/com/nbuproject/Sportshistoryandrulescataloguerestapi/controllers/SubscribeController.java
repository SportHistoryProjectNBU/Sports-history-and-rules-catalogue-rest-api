package com.nbuproject.Sportshistoryandrulescataloguerestapi.controllers;


import com.nbuproject.Sportshistoryandrulescataloguerestapi.entities.Subscribe;
import com.nbuproject.Sportshistoryandrulescataloguerestapi.repositories.SubscribeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "/subscribe")
public class SubscribeController {

    @Autowired
    private SubscribeRepository subscribeRepository;

    @Autowired
    @Qualifier("javaMail")
    private  MailSender mailSender;


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Map<String,String>> subscribe(@RequestBody Subscribe subscribe) {
        Map<String,String> response = new HashMap<>();
        Subscribe checkSubscribe = subscribeRepository.findByMatchIdAndUserId(subscribe.getMatchId(), subscribe.getUserId());
        if(checkSubscribe != null) {
            subscribeRepository.delete(checkSubscribe);
            response.put("subscribe", "delete");
        }else {
            subscribeRepository.save(subscribe);
            response.put("subscribe", "insert");
        }


        return ResponseEntity.ok(response);
    }

    @RequestMapping(path = "/userSubscribe", method = RequestMethod.PUT)
    public  ResponseEntity<ArrayList<Subscribe>> getAllSubscribeForUser(@RequestBody Subscribe subscribe){
        ArrayList<Subscribe> subscribes = new ArrayList<>();
        subscribes = subscribeRepository.findAllByUserId(subscribe.getUserId());
        return  ResponseEntity.ok(subscribes);

    }
}
