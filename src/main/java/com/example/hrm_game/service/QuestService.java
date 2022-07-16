package com.example.hrm_game.service;

import com.example.hrm_game.data.entity.QuestEntity;
import com.example.hrm_game.data.entity.UserEntity;
import com.example.hrm_game.repository.QuestRepository;
import com.example.hrm_game.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class QuestService {
    @Autowired
    private QuestRepository questRepository;
    @Autowired
    private UserRepository userRepository;

    public void getQuestByUser(final Long questId, final Long userId){
        QuestEntity quest = questRepository.findQuestEntityById(questId);
        UserEntity user = userRepository.findUserEntityById(userId);
        quest.setUsers(Collections.singletonList(user));
        questRepository.save(quest);
    }

    public void getQuestProgress(final Long questId, final Long userId){
//        QuestEntity quest = questRepository.findQuestEntityById(questId);
//        UserEntity user = userRepository.findUserEntityById(userId);
//        quest.setUsers(Collections.singletonList(user));
//        questRepository.save(quest);
    }
}
