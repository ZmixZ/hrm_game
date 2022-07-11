package com.example.hrm_game.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TestingTypeDto {
    private Long id;
    private String description;
    private String name;
}
