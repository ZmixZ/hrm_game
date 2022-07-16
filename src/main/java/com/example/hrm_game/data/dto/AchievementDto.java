package com.example.hrm_game.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@AllArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AchievementDto {
    private Long id;
    private String description;
    private Integer progress;
}
