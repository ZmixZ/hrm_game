package com.example.hrm_game.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@AllArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class QuestDto {
    private Integer experience;
    private Integer coins;
    private Integer progress;
    private List<Object> awards;
}
