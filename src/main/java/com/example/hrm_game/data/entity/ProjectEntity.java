package com.example.hrm_game.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "project")
@RequiredArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProjectEntity {
    @Id
    private Long id;
    private String name;
    @OneToOne(mappedBy = "projectEntity", orphanRemoval = true)
    private AccountEntity account;
    @OneToOne(mappedBy = "project", orphanRemoval = true)
    private EmployeeProjectsEntity employeeProjects;
}
