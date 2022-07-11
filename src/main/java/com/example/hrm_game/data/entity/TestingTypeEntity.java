package com.example.hrm_game.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "testing_types")
@RequiredArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TestingTypeEntity {
    @Id
    private Long id;
    private String description;
    private String name;
    @OneToOne(mappedBy = "testingType", orphanRemoval = true)
    private EmployeeProjectsEntity employeeProjects;
}
