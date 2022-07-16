package com.example.hrm_game.controller;

import com.example.hrm_game.data.dto.AchievementDto;
import com.example.hrm_game.data.entity.AchievementEntity;
import com.example.hrm_game.data.entity.UserEntity;
import com.example.hrm_game.repository.AchievementRepository;
import com.example.hrm_game.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@RestController
public class AchievementRestController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AchievementRepository achievementRepository;

    //TODO: Не добавлять ачивку, а сделать ее видимой при достижении прогресса ачивки
    @PostMapping(
            value = "/achievement/add/{id}",
            consumes = "application/json",
            produces = "application/json"
    )
    public String addAchievement(
            @PathVariable("id") Long userId,
            @RequestBody AchievementDto achievement
    ) {
        UserEntity user = userRepository.findUserEntityById(userId);
//        AchievementEntity achievementById = achievementRepository.findAchievementEntityByUser(user);
        AchievementEntity achievementByUser = achievementRepository.findAchievementEntityById(achievement.getId());
        achievementByUser.setIsAdded(true);
        achievementRepository.save(achievementByUser);

        //TODO: Сделать редирект на стр с квестом
        return null;
    }

    @PostMapping(value = "/achievement/progress/{id}",
            consumes = "application/json",
            produces = "application/json"
    )
    public String progressAchievement(
            @PathVariable("id") Long userId,
            @RequestParam(name = "ahciveId") Long achiveId,
            @RequestBody Integer progress,
            RedirectAttributes redirectAttributes
    ) {
        UserEntity user = userRepository.findUserEntityById(userId);
//        AchievementEntity achievementById = achievementRepository.findAchievementEntityById(achiveId);
        AchievementEntity achievementByUser = achievementRepository.findAchievementEntityById(achiveId);
        if (!achievementByUser.getIsAdded()) {
            achievementByUser.setProgress(achievementByUser.getProgress() + progress);
            if (achievementByUser.getProgress() >= achievementByUser.getTotal()) {
                achievementRepository.save(achievementByUser);
                redirectAttributes.addAttribute("achievement", achievementByUser);
                return "redirect:/achievement/add/" + userId;
            } else {
                achievementRepository.save(achievementByUser);
                //TODO: Сделать редирект на метод, отвечающий за прогрузку стр с квестом
                return null;
            }

        }
        //TODO: Сделать редирект на метод, отвечающий за прогрузку стр с квестом
        return null;
    }
}
