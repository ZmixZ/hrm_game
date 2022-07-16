package com.example.hrm_game.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "achievement")
@RequiredArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AchievementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private LocalDate localDate;
    private String image;
    private Integer total;
    private Integer progress;
    private Boolean isAdded;
    @ManyToMany(mappedBy = "achievementEntity")
    private List<UserEntity> userAchive;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "quest_achievement", referencedColumnName = "id")
    private QuestEntity quest;
}
