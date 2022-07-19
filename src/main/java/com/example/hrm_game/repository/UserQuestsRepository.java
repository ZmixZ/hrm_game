package com.example.hrm_game.repository;

import com.example.hrm_game.data.entity.UsersQuestsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserQuestsRepository extends JpaRepository<UsersQuestsEntity, Long> {
    List<UsersQuestsEntity> findUsersQuestEntityByUserId(Long userId);
}
