package com.example.hrm_game.controller;

import com.example.hrm_game.data.entity.LevelEntity;
import com.example.hrm_game.data.entity.UserEntity;
import com.example.hrm_game.service.UserService;
import com.example.hrm_game.service.LevelUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LevelUpRestController {
    @Autowired
    private UserService accountsService;
    @Autowired
    private LevelUpService levelUpService;

    @PostMapping("/user/progress")
    public void experienceProgressForUser(
            @RequestParam("id") Long userId,
            @RequestParam("experience") Integer experience
    ) {
        //TODO: отрефакторить о возможности этого
        UserEntity user = accountsService.getUserFromId(userId);
        LevelEntity level = user.getLevel();
        Integer progressUp = level.getCurrentExperience() + experience;
        if (progressUp > level.getMaxExperience()) {
            int excess = progressUp - level.getMaxExperience();
            levelUpService.upUserLevel(user, level, excess);
        } else if (progressUp.equals(level.getMaxExperience())) {
            levelUpService.upUserLevel(user, level, 0);
        } else {
            level.setCurrentExperience(level.getCurrentExperience() + experience);
            accountsService.updateUserData(user);
        }
    }
}
