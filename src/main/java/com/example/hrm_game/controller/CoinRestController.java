package com.example.hrm_game.controller;

import com.example.hrm_game.data.entity.UserEntity;
import com.example.hrm_game.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CoinRestController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping(
            value = "/add/coin/{id}",
            consumes = "application/json",
            produces = "application/json"
    )
    public void addCoin(
            @RequestParam("id") Long userId,
            @RequestBody Integer coins
    ){
        UserEntity user = userRepository.findUserEntityById(userId);
        user.setCoins(coins);
        userRepository.save(user);
    }

    @PostMapping(
            value = "/writeoff/coins/{id}",
            consumes = "application/json",
            produces = "application/json"
    )
    public void writeOffCoins(
            @RequestParam("id") Long userId,
            @RequestBody Integer coins
    ){
        UserEntity user = userRepository.findUserEntityById(userId);
        user.setCoins(user.getCoins() - coins);
        //TODO: Сделать логику траты этих коинов на покупку
        userRepository.save(user);
    }
}
