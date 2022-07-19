package com.example.hrm_game.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@RequiredArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserEntity {
    @Id
    private Long id;
    private String name;
    private String guild;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_level", referencedColumnName = "id")
    private LevelEntity level;
    private Integer coins;
    private Integer defeat;
    private Integer victories;

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "user_achives",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "user_achive_id"
//            )
//    )
//    private List<AchievementEntity> achievementEntity;
    @OneToMany(mappedBy = "user")
    private List<UsersAchievementEntity> usersAchievements;

    private String description;
    private String photo;
    @JsonProperty("current_positions")
    @Column(name = "current_positions")
    @ElementCollection
    private List<String> currentPositions;
    @JsonProperty("current_project")
    @Column(name = "current_project")
    private String currentProject;
    @JsonProperty("current_city")
    @Column(name = "current_city")
    private String currentCity;

    //Мб по выпил
    @JsonProperty("employee_positions")
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmployeePositionEntity> employeePosition;

    @JsonProperty("employee_position_grades")
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmployeePositionGradesEntity> guildPosition;
    ///////

    @JsonProperty("employee_projects")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_projects", referencedColumnName = "id")
    private EmployeeProjectsEntity guildProjects;

    //Мб по выпил
    @JsonProperty("qualification_skill_types")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "qualification_skill_types", referencedColumnName = "id")
    private QualificationSkillTypesEntity userSkillsType;

    @JsonProperty("key_skill_types")
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<KeySkillTypesEntity> userSkills;
    ///////

    private String gender;
    private String birth;
    @JsonProperty("joining_date")
    @Column(name = "joining_date")
    private LocalDate joiningDate;
    private String city;
    private String phone;
    private String email;
    private String courses;
    private String citizenship;
    @JsonProperty("organization_names")
    @Column(name = "organization_names")
    @ElementCollection
    private List<String> organizationNames;
    @JsonProperty("is_active")
    @Column(name = "is_active")
    private Boolean isActive;
//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "user_quests",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "user_quests_id")
//    )
//    private List<QuestEntity> quests = new ArrayList<>();
    @OneToMany(mappedBy = "user")
    private List<UsersQuestsEntity> questEntities;
}
