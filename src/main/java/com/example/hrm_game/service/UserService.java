package com.example.hrm_game.service;

import com.example.hrm_game.data.dto.AccountDto;
import com.example.hrm_game.data.dto.UserDto;
import com.example.hrm_game.data.entity.AchievementEntity;
import com.example.hrm_game.data.entity.LevelEntity;
import com.example.hrm_game.data.entity.UserEntity;
import com.example.hrm_game.repository.AchievementRepository;
import com.example.hrm_game.repository.LevelRepository;
import com.example.hrm_game.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LevelRepository levelRepository;
    @Autowired
    private AchievementRepository achievementRepository;
    public void insertAllUser(List<UserDto> users) {
        users.forEach(
                user -> {
                    try {
                        //TODO: Задать вопрос: меняются ли в HRM данные
                        // и стоит ли их при проливке апдейтить или только добавлять новых, как сейчас?
                        UserEntity userById = userRepository.findUserEntityById(user.getId());
                        if (userById == null) {
                            List<AchievementEntity> achievementEntity = achievementRepository.findAll();
                            //Создаем юзера, если он отсутствует в БД приложения
                            UserEntity userEntity = new UserEntity();
                            userEntity.setId(user.getId());
                            userEntity.setName(user.getName());
                            userEntity.setDefeat(0);
                            userEntity.setVictories(0);
                            userEntity.setDescription("");
                            userEntity.setCoins(0);
                            userEntity.setBirth(user.getBirth());
                            userEntity.setCity(user.getCity());
                            userEntity.setPhoto(user.getPhoto());
                            userEntity.setGender(user.getGender());
                            userEntity.setAchievementEntity(achievementEntity);

                            LevelEntity level = new LevelEntity();
                            level.setLevel(1);
                            level.setCurrentExperience(0);
                            level.setMaxExperience(100);
                            userEntity.setLevel(level);

                            userRepository.save(userEntity);
                        } else {
                            //Обновляем имя и админа, вдруг поменялись
                            userById.setName(user.getName());
                            userById.setBirth(user.getBirth());
                            userById.setCity(user.getCity());
                            userById.setGender(user.getGender());
                        }
                    } catch (NullPointerException ex) {
                        log.error("Пустой айди у пользователя " + user.getId());
                    }
                }
        );
    }
    public void insertUser(UserDto user) {
        try {
            UserEntity userById = userRepository.findUserEntityById(user.getId());
            if (userById == null) {
                //Создаем юзера, если он отсутствует в БД приложения
                UserEntity userEntity = new UserEntity();
                List<AchievementEntity> achievementEntity = achievementRepository.findAll();
                userEntity.setId(user.getId());
                userEntity.setName(user.getName());
                userEntity.setDefeat(0);
                userEntity.setVictories(0);
                userEntity.setDescription("");
                userEntity.setCoins(0);
                userEntity.setBirth(user.getBirth());
                userEntity.setCity(user.getCity());
                userEntity.setPhoto(user.getPhoto());
                userEntity.setGender(user.getGender());
                userEntity.setAchievementEntity(achievementEntity);

                LevelEntity level = new LevelEntity();
                level.setLevel(1);
                level.setCurrentExperience(0);
                level.setMaxExperience(100);
                userEntity.setLevel(level);

                userRepository.save(userEntity);
            } else {
                //Обновляем имя и админа, вдруг поменялись
                userById.setName(user.getName());
                userById.setBirth(user.getBirth());
                userById.setCity(user.getCity());
                userById.setGender(user.getGender());
            }
        } catch (NullPointerException ex) {
            log.error("Пустой айди у пользователя " + user.getId());
        }
    }
    public UserEntity getUserFromId(Long id) {
        return userRepository.findUserEntityById(id);
    }
    public void updateUserData(UserDto user) {
        //TODO: мб как-то убрать или подумать что с ним делать
        UserEntity userEntity = userRepository.findUserEntityById(user.getId());
        userEntity.setName(user.getName());
//        userEntity.setActiveAdmin(user.getActiveAdmin());
        userEntity.setGuild(user.getGuild());
//        userEntity.setLevel(user.getLevel());
//        userEntity.setCoins(user.getCoins());
//        userEntity.setVictories(user.getVictories());
//        userEntity.setDefeat(user.getDefeat());
        userEntity.setDescription(user.getDescription());
        userRepository.save(userEntity);
    }
    public void updateUserData(UserEntity user){
        userRepository.save(user);
    }
}
