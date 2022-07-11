package com.example.hrm_game.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class KeySkillTypesDto {
    private Long id;
    private String name;
    @JsonProperty("key_skills")
    private List<String> keySkills;
}
