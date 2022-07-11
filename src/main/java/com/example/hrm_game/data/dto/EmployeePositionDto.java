package com.example.hrm_game.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@AllArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeePositionDto {
    private Long id;
    @JsonProperty("not_in_summary")
    private Boolean noIsSummary;
    private String memo;
    private String notIsSummary;
    private String rate;
    private Long grade;
    @JsonProperty("start_date")
    private LocalDate startDate;
    @JsonProperty("end_date")
    private LocalDate endDate;
}
