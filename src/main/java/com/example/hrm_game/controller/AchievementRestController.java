package com.example.hrm_game.controller;

import com.example.hrm_game.data.dto.AchievementDto;
import com.example.hrm_game.data.entity.AchievementEntity;
import com.example.hrm_game.data.entity.UserEntity;
import com.example.hrm_game.data.entity.UsersAchievementEntity;
import com.example.hrm_game.repository.AchievementRepository;
import com.example.hrm_game.repository.UserAchievementRepository;
import com.example.hrm_game.repository.UserRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@RestController
public class AchievementRestController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AchievementRepository achievementRepository;
    @Autowired
    private UserAchievementRepository userAchievementRepository;

    @PostMapping(
            value = "/achievement/add/{id}",
            consumes = "application/json",
            produces = "application/json"
    )
    public String addAchievement(
            @PathVariable("id") Long userId,
            @RequestBody AchievementDto achievement
    ) {
        UserEntity user =
                userRepository.findUserEntityById(userId);
        AchievementEntity achievementByUser =
                achievementRepository.findAchievementEntityById(achievement.getId());
        List<UsersAchievementEntity> usersAchievementEntityByUser =
                userAchievementRepository.findUsersAchievementEntityByUserId(user.getId());
        UsersAchievementEntity usersAchievementEntity =
                usersAchievementEntityByUser.stream().filter(usr -> usr.getAchievement().getId().equals(achievement.getId())).findAny().get();
        usersAchievementEntity.setAdded(true);
        //TODO: Сделать редирект на стр с квестом
        return null;
    }

    @PostMapping(value = "/achievement/progress/{id}",
            consumes = "application/json",
            produces = "application/json"
    )
    @SneakyThrows
    public RedirectView progressAchievement(
            @PathVariable("id") Long userId,
            @RequestParam(name = "ahcive_id") Long achiveId,
            @RequestBody AchievementDto achievementDto,
            HttpServletResponse response
    ) {
        UserEntity user = userRepository.findUserEntityById(userId);
//        AchievementEntity achievementById = achievementRepository.findAchievementEntityById(achiveId);
        AchievementEntity achievementByUser = achievementRepository.findAchievementEntityById(achiveId);
        List<UsersAchievementEntity> usersAchievements = userAchievementRepository.findUsersAchievementEntityByUserId(user.getId());
        UsersAchievementEntity targetAchievement = usersAchievements.stream().filter(usr -> usr.getAchievement().getId().equals(achiveId)).findAny().get();
        if (!targetAchievement.isAdded()) {
            targetAchievement.setProgress(targetAchievement.getProgress() + achievementDto.getProgress());
            if (targetAchievement.getProgress() >= targetAchievement.getTotal()) {
                userAchievementRepository.save(targetAchievement);
                response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
                response.setHeader("Location", "/achievement/add/" + userId);
//                Редирект для @requestparam
//                redirectAttributes.addFlashAttribute("achievement", achievementByUser);
//                return new RedirectView("/achievement/add/" + userId);
            } else {
                userAchievementRepository.save(targetAchievement);
                //TODO: Сделать редирект на метод, отвечающий за прогрузку стр с квестом
                return null;
            }
        }
        //TODO: Сделать редирект на метод, отвечающий за прогрузку стр с квестом
        return null;
    }
}
