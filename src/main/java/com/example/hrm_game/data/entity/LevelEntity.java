package com.example.hrm_game.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "levels")
@RequiredArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class LevelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer level;
    @Column(name = "current_experience")
    private Integer currentExperience;
    @Column(name = "max_experience")
    private Integer maxExperience;
    @OneToOne(mappedBy = "level", orphanRemoval = true)
    private UserEntity userEntity;
}
