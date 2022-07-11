package com.example.hrm_game.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    private Long id;
    private String name;
    private String surname;
    private String patronymic;
    private String photo;
    @JsonProperty("current_positions")
    private List<String> currentPositions;
    @JsonProperty("current_project")
    private String currentProject;
    @JsonProperty("current_city")
    private String currentCity;
    @JsonProperty("employee_positions")
    private List<EmployeePositionDto> employeePosition;
    private String guild;
    @JsonProperty("employee_position_grades")
    private List<EmployeePositionGradesDto> guildPosition;
    @JsonProperty("employee_projects")
    private EmployeeProjectsDto guildProjects;
    @JsonProperty("qualification_skill_types")
    private QualificationSkillTypesDto userSkillsType;
    @JsonProperty("key_skill_types")
    private List<KeySkillTypesDto> userSkills;
    private String gender;
    private String birth;
    @JsonProperty("joining_date")
    private LocalDate joiningDate;
    private String city;
    private String phone;
    private String email;
    private String courses;
    private String citizenship;
    @JsonProperty("organization_names")
    private List<String> organizationNames;
    @JsonProperty("is_active")
    private Boolean isActive;
    private Integer coins;
    private Integer defeat;
    private Integer victories;
    private AchievementDto achievement;
    private String description;

    public UserDto() {
    }

//    "employee_project_salary_rates": [],
//            "employee_civil_contracts": [],
//            "employee_cities": [],
//            "employee_languages": [],
//            "employee_previous_experience": [],
//            "employee_achievements": [],
//            "employee_vacation_requests": [],
//            "employee_actual_vacations": [],
//            "employee_sick_leaves_official": [],
//            "employee_sick_leaves_unofficial": [],
//            "employee_family": [],
//    @JsonProperty("employee_educations")
//    private  employeeEducations;
}
