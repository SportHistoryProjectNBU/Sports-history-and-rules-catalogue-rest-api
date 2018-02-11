package com.nbuproject.Sportshistoryandrulescataloguerestapi.cronjobs;


import com.nbuproject.Sportshistoryandrulescataloguerestapi.controllers.MailSender;
import com.nbuproject.Sportshistoryandrulescataloguerestapi.entities.Game;
import com.nbuproject.Sportshistoryandrulescataloguerestapi.entities.Subscribe;
import com.nbuproject.Sportshistoryandrulescataloguerestapi.entities.User;
import com.nbuproject.Sportshistoryandrulescataloguerestapi.repositories.GameRepository;
import com.nbuproject.Sportshistoryandrulescataloguerestapi.repositories.SubscribeRepository;
import com.nbuproject.Sportshistoryandrulescataloguerestapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Component
public class MailSendScheduledTasks {

    @Autowired
    @Qualifier("javaMail")
    private MailSender mailSender;

    @Autowired
    private SubscribeRepository subscribeRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private UserRepository userRepository;

    @Scheduled(fixedRate = 600000)
    public void sendEmail() throws ParseException {

        ArrayList<Subscribe> subscribes = subscribeRepository.findAll();
        for (Subscribe subscribe : subscribes) {
            Game game = gameRepository.findGameById(subscribe.getMatchId());
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Date gameDate = f.parse(game.getDate());
            Date today = new Date();
            if (gameDate.getTime() - today.getTime() >= 0 && gameDate.getTime() - today.getTime() <= 3600000) {
                User user = userRepository.findUserById(subscribe.getUserId());
                mailSender.sendSimpleMessage(user.getEmail(), "A new game is about to start in 1 hour!",
                        "Hi " + user.getName() + "a new game between " + game.getHomeTeamName() + " and " + game.getAwayTeamName() +
                                " will start in one hour! You can come to our website and comment and leave a forecast who will win" +
                                " http://localhost:4200/games/" + game.getId());
            }
        }
        
    }
}
