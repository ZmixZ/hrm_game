package com.example.hrm_game.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "qualification_skill_types")
@RequiredArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class QualificationSkillTypesEntity {
    @Id
    private Long id;
    private String name;
    @JsonProperty("qualification_skills")
    @ElementCollection
    private List<String> skills;
    @OneToOne(mappedBy = "userSkillsType", orphanRemoval = true)
    private UserEntity user;
}
