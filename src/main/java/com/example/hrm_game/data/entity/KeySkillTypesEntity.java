package com.example.hrm_game.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "key_skill_types")
@RequiredArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class KeySkillTypesEntity {
    @Id
    private Long id;
    private String name;
    @JsonProperty("key_skills")
    @ElementCollection
    private List<String> keySkills;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "key_skill_types", referencedColumnName = "id")
    private UserEntity user;
}
