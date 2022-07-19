package com.example.hrm_game.service;

import com.example.hrm_game.data.dto.AccountDto;
import com.example.hrm_game.data.dto.UserDto;
import com.example.hrm_game.data.entity.*;
import com.example.hrm_game.repository.*;
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
    @Autowired
    private QuestRepository questRepository;
    @Autowired
    private UserAchievementRepository userAchievementRepository;
    public void insertAllUser(List<UserDto> users) {
//        users.forEach(
//                user -> {
//                    try {
//                        //TODO: Задать вопрос: меняются ли в HRM данные
//                        // и стоит ли их при проливке апдейтить или только добавлять новых, как сейчас?
//                        UserEntity userById = userRepository.findUserEntityById(user.getId());
//                        if (userById == null) {
//                            List<AchievementEntity> achievementEntity = achievementRepository.findAll();
//                            //Создаем юзера, если он отсутствует в БД приложения
//                            UserEntity userEntity = new UserEntity();
//                            userEntity.setId(user.getId());
//                            userEntity.setName(user.getName());
//                            userEntity.setDefeat(0);
//                            userEntity.setVictories(0);
//                            userEntity.setDescription("");
//                            userEntity.setCoins(0);
//                            userEntity.setBirth(user.getBirth());
//                            userEntity.setCity(user.getCity());
//                            userEntity.setPhoto(user.getPhoto());
//                            userEntity.setGender(user.getGender());
//                            userEntity.setAchievementEntity(achievementEntity);
//
//                            LevelEntity level = new LevelEntity();
//                            level.setLevel(1);
//                            level.setCurrentExperience(0);
//                            level.setMaxExperience(100);
//                            userEntity.setLevel(level);
//
//                            userRepository.save(userEntity);
//                        } else {
//                            //Обновляем имя и админа, вдруг поменялись
//                            userById.setName(user.getName());
//                            userById.setBirth(user.getBirth());
//                            userById.setCity(user.getCity());
//                            userById.setGender(user.getGender());
//                        }
//                    } catch (NullPointerException ex) {
//                        log.error("Пустой айди у пользователя " + user.getId());
//                    }
//                }
//        );
        AchievementEntity achievement = new AchievementEntity();
        achievement.setId(1L);
//        achievement.setIsAdded(false);
//        achievement.setTotal(5);
//        achievement.setProgress(0);
        achievement.setDescription("Test");
        achievementRepository.save(achievement);
        AchievementEntity achievement1 = new AchievementEntity();
        achievement1.setId(2L);
//        achievement1.setIsAdded(false);
//        achievement1.setTotal(3);
//        achievement1.setProgress(0);
        achievement1.setDescription("Test1");
        achievementRepository.save(achievement1);

        QuestEntity quest = new QuestEntity();
        quest.setIsCompleted(false);
        quest.setTotal(5);
        quest.setProgress(0);
        quest.setDescription("Test");
        quest.setExperience(50);
        quest.setCoins(20);
        questRepository.save(quest);

        List<AchievementEntity> achievementEntity = achievementRepository.findAll();
        //Создаем юзера, если он отсутствует в БД приложения
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setName("Mik");
        userEntity.setDefeat(0);
        userEntity.setVictories(0);
        userEntity.setDescription("");
        userEntity.setCoins(0);
        userEntity.setBirth("12.08.4444");
        userEntity.setCity("Moscow");
        userEntity.setPhoto("asdfsdgsd.jpg");
        userEntity.setGender("man");
//        userEntity.setAchievementEntity(achievementEntity);

        LevelEntity level = new LevelEntity();
        level.setLevel(1);
        level.setCurrentExperience(0);
        level.setMaxExperience(100);
        userEntity.setLevel(level);

        userRepository.save(userEntity);

        for(AchievementEntity achieve : achievementEntity) {
            UsersAchievementEntity usersAchievement = new UsersAchievementEntity();
            usersAchievement.setUser(userEntity);
            usersAchievement.setAchievement(achieve);
            userAchievementRepository.save(usersAchievement);
        }

        UserEntity userEntity1 = new UserEntity();
        userEntity1.setId(2L);
        userEntity1.setName("Nik");
        userEntity1.setDefeat(0);
        userEntity1.setVictories(0);
        userEntity1.setDescription("");
        userEntity1.setCoins(0);
        userEntity1.setBirth("12.08.4422");
        userEntity1.setCity("Moscow");
        userEntity1.setPhoto("asdfsdgsd.jpg");
        userEntity1.setGender("man");
//        userEntity1.setAchievementEntity(achievementEntity);

        LevelEntity level1 = new LevelEntity();
        level1.setLevel(1);
        level1.setCurrentExperience(0);
        level1.setMaxExperience(100);
        userEntity1.setLevel(level);

        userRepository.save(userEntity1);

        for(AchievementEntity achieve : achievementEntity) {
            UsersAchievementEntity usersAchievement = new UsersAchievementEntity();
            usersAchievement.setUser(userEntity1);
            usersAchievement.setAchievement(achieve);
            userAchievementRepository.save(usersAchievement);
        }
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
//                userEntity.setAchievementEntity(achievementEntity);

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
