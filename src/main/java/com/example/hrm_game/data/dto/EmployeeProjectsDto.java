package com.example.hrm_game.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@AllArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeProjectsDto {
    private Long id;
    @JsonProperty("start_date")
    private LocalDate startDate;
    @JsonProperty("end_date")
    private LocalDate endDate;
    @JsonProperty("show_in_resume")
    private Boolean showIsResume;
    private String responsibilities;
    private ProjectDto project;
    @JsonProperty("testing_type")
    private TestingTypeDto testingType;
    @JsonProperty("is_trainee")
    private Boolean isTrainee;
    private String feedback;
    @JsonProperty("project_role")
    private ProjectRoleDto projectRole;
}
