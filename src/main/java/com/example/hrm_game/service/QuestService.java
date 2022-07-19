package com.example.hrm_game.service;

import com.example.hrm_game.data.dto.QuestDto;
import com.example.hrm_game.data.entity.LevelEntity;
import com.example.hrm_game.data.entity.UserEntity;
import com.example.hrm_game.data.entity.UsersQuestsEntity;
import com.example.hrm_game.repository.QuestRepository;
import com.example.hrm_game.repository.UserQuestsRepository;
import com.example.hrm_game.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuestService {
    @Autowired
    private QuestRepository questRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserQuestsRepository userQuestsRepository;

    public void addQuestForUser(final Long questId, final Long userId) {
        List<UsersQuestsEntity> usersQuests =
                userQuestsRepository.findUsersQuestEntityByUserId(userId);
        Optional<UsersQuestsEntity> targetQuest =
                usersQuests.stream().filter(usr -> usr.getQuests().getId().equals(questId)).findAny();
        if (targetQuest.isEmpty()) {
            UsersQuestsEntity addNewQuest = new UsersQuestsEntity();
            addNewQuest.setQuests(questRepository.findQuestEntityById(questId));
            addNewQuest.setUser(userRepository.findUserEntityById(userId));
            userQuestsRepository.save(addNewQuest);
        }
    }

    public boolean getQuestProgress(final Long questId, final Long userId, final QuestDto questDto) {
        List<UsersQuestsEntity> usersQuests =
                userQuestsRepository.findUsersQuestEntityByUserId(userId);
        UsersQuestsEntity targetQuest =
                usersQuests.stream().filter(usr -> usr.getQuests().getId().equals(questId)).findAny().get();
        if (!targetQuest.isCompleted()) {
            targetQuest.setProgress(targetQuest.getProgress() + questDto.getProgress());
            if (targetQuest.getProgress() >= targetQuest.getTotal()) {
                targetQuest.setCompleted(true);
                userQuestsRepository.save(targetQuest);
                return true;
            }
        }
        return false;
    }

    public boolean addAwards(final Long questId, final Long userId, final QuestDto questDto) {
        List<UsersQuestsEntity> usersQuests =
                userQuestsRepository.findUsersQuestEntityByUserId(userId);
        UsersQuestsEntity targetQuest =
                usersQuests.stream().filter(usr -> usr.getQuests().getId().equals(questId)).findAny().get();
        if (targetQuest.isCompleted()) {
            UserEntity user = targetQuest.getUser();
            user.setCoins(user.getCoins() + questDto.getCoins());

            LevelEntity level = user.getLevel();
            level.setCurrentExperience(level.getCurrentExperience() + questDto.getExperience());

            //TODO: дописать получение награды
            return true;

        }
        return false;
    }
}
