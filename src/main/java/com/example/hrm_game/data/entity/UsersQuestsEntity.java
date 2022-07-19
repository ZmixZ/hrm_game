package com.example.hrm_game.data.entity;

import com.example.hrm_game.data.serializable.UsersAchievementEntityId;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "users_quests")
@RequiredArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@IdClass(UsersAchievementEntityId.class)
public class UsersQuestsEntity {
    @Id
    @ManyToOne
    @JoinColumn(
            name = "users_id",
            referencedColumnName = "id"
    )
    private UserEntity user;

    @Id
    @ManyToOne
    @JoinColumn(
            name = "quest_id",
            referencedColumnName = "id")
    private QuestEntity quests;

    @Column(name = "is_completed")
    private boolean isCompleted;
    private Integer total;
    private Integer progress;
}
