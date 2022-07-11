package com.example.hrm_game.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "employee_position")
@RequiredArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeePositionEntity {
    @Id
    private Long id;
    @JsonProperty("not_in_summary")
    @Column(name = "not_in_summary")
    private Boolean noIsSummary;
    private String memo;
    private String notIsSummary;
    private String rate;
    private Long grade;
    @JsonProperty("start_date")
    @Column(name = "start_date")
    private LocalDate startDate;
    @JsonProperty("end_date")
    @Column(name = "end_date")
    private LocalDate endDate;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_position", referencedColumnName = "id")
    private UserEntity user;
}
