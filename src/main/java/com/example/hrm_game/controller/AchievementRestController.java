package com.example.hrm_game.controller;

import com.example.hrm_game.data.dto.AchievementDto;
import com.example.hrm_game.data.entity.AchievementEntity;
import com.example.hrm_game.data.entity.UserEntity;
import com.example.hrm_game.repository.AchievementRepository;
import com.example.hrm_game.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class AchievementRestController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AchievementRepository achievementRepository;

    @PostMapping(
            value = "/add/achievement/{id}",
            consumes = "application/json",
            produces = "application/json")
    public void addAchievement(
            @PathVariable("id") Long userId,
            @RequestBody AchievementDto achievement
    ){
        UserEntity user = userRepository.findUserEntityById(userId);
        AchievementEntity newAchievement = new AchievementEntity();
        newAchievement.setImage(achievement.getImage());
        newAchievement.setDescription(achievement.getDescription());
        newAchievement.setLocalDate(LocalDate.now());
        newAchievement.setUserAchive(user);
        achievementRepository.save(newAchievement);
    }
}
