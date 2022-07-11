package com.example.hrm_game.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "project_role")
@RequiredArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProjectRoleEntity {
    @Id
    private Long id;
    private String name;
    @JsonProperty("is_lead")
    @Column(name = "is_lead")
    private Boolean isLead;
    private String description;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_projects", referencedColumnName = "id")
    private EmployeeProjectsEntity employeeProjects;
}
