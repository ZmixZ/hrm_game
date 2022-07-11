package com.example.hrm_game.repository;

import com.example.hrm_game.data.entity.AchievementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AchievementRepository extends JpaRepository<AchievementEntity, Long> {
}
