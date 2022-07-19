package com.example.hrm_game.repository;

import com.example.hrm_game.data.entity.AchievementEntity;
import com.example.hrm_game.data.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AchievementRepository extends JpaRepository<AchievementEntity, Long> {
    AchievementEntity findAchievementEntityById(Long id);
//    AchievementEntity findAchievementEntityByUser(UserEntity user);
}
