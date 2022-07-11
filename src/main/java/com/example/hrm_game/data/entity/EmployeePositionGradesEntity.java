package com.example.hrm_game.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "employee_position_grades")
@RequiredArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeePositionGradesEntity {
    @Id
    private Long grade;
    private String position;
    @JsonProperty("change_date")
    @Column(name = "change_date")
    private LocalDate changeDate;
    private String memo;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_position_grades", referencedColumnName = "id")
    private UserEntity user;
}
