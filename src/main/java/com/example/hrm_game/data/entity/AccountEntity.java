package com.example.hrm_game.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "accounts")
@RequiredArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountEntity {
    @Id
    private Long id;
    private String name;
    @Column(name = "active_admin")
    @ElementCollection
    private List<String> activeAdmin;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "project", referencedColumnName = "id")
    private ProjectEntity projectEntity;
}
