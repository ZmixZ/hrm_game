package com.example.hrm_game.data.entity;

import com.example.hrm_game.data.serializable.UsersAchievementEntityId;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "users_achievement")
@RequiredArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@IdClass(UsersAchievementEntityId.class)
public class UsersAchievementEntity {
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
            name = "achieve_id",
            referencedColumnName = "id")
    private AchievementEntity achievement;

    @Column(name = "is_added")
    private boolean isAdded;
    private Integer total;
    private Integer progress;
}
