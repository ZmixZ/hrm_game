package com.example.hrm_game.data.entity;

import com.example.hrm_game.data.dto.ProjectDto;
import com.example.hrm_game.data.dto.ProjectRoleDto;
import com.example.hrm_game.data.dto.TestingTypeDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "employee_projects")
@RequiredArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeProjectsEntity {
    @Id
    private Long id;
    @JsonProperty("start_date")
    @Column(name = "start_date")
    private LocalDate startDate;
    @JsonProperty("end_date")
    @Column(name = "end_date")
    private LocalDate endDate;
    @JsonProperty("show_in_resume")
    @Column(name = "show_in_resume")
    private Boolean showIsResume;
    private String responsibilities;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "project", referencedColumnName = "id")
    private ProjectEntity project;

    @JsonProperty("testing_type")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "testing_type", referencedColumnName = "id")
    private TestingTypeEntity testingType;

    @JsonProperty("is_trainee")
    @Column(name = "is_trainee")
    private Boolean isTrainee;
    private String feedback;

    @JsonProperty("project_role")
    @OneToOne(mappedBy = "employeeProjects", orphanRemoval = true)
    private ProjectRoleEntity projectRole;

    @OneToOne(mappedBy = "guildProjects", orphanRemoval = true)
    private UserEntity user;
}
