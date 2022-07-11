package com.example.hrm_game.service;

import com.example.hrm_game.data.entity.LevelEntity;
import com.example.hrm_game.data.entity.UserEntity;
import com.example.hrm_game.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class LevelUpService {
    @Autowired
    private UserRepository userRepository;

    public void upUserLevel(UserEntity user, LevelEntity level, int excess) {
        //TODO: отрефакторить о возможности этого
        if (excess > 0) {
            do {
                level.setLevel(level.getLevel() + 1);
                Integer levelCurrent = level.getLevel();
                Double exp = level.getMaxExperience() * calculateCoefficientLevels(levelCurrent);
                level.setCurrentExperience(excess);
                level.setMaxExperience(level.getMaxExperience() + exp.intValue());
                excess = excess - level.getMaxExperience();
                if (excess > 0) {
                    Integer currentLevel = level.getLevel();
                    Double insideNewExpLevel = level.getMaxExperience() * calculateCoefficientLevels(currentLevel);
                    level.setLevel(level.getLevel() + 1);
                    level.setCurrentExperience(excess);
                    level.setMaxExperience(level.getMaxExperience() + insideNewExpLevel.intValue());
                }
            } while (excess > level.getMaxExperience());
        } else {
            Integer currentLevel = level.getLevel();
            Double newExpLevel = level.getMaxExperience() * calculateCoefficientLevels(currentLevel);
            level.setLevel(level.getLevel() + 1);
            level.setCurrentExperience(0);
            level.setMaxExperience(level.getMaxExperience() + newExpLevel.intValue());
        }
        userRepository.save(user);
    }

    private Double calculateCoefficientLevels(int currentLevel) {
        return new BigDecimal(5).multiply(new BigDecimal(currentLevel)).divide(new BigDecimal(100)).doubleValue();
    }
}
