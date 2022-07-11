package com.example.hrm_game.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@AllArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeePositionGradesDto {
    private String position;
    private Long grade;
    @JsonProperty("change_date")
    private LocalDate changeDate;
    private String memo;
}
