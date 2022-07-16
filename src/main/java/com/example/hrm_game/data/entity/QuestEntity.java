package com.example.hrm_game.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "quests")
@RequiredArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class QuestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private Integer experience;
    private Integer coins;
    private String image;
    private Integer progress;
    @OneToOne(mappedBy = "quest")
    private AchievementEntity achievement;
    @ManyToMany(mappedBy = "quests")
    private List<UserEntity> users = new ArrayList<>();
    //Добавить репутацию фракции
}
