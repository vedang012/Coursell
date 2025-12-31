package com.vedang.coursell.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String courseName;

    @ManyToOne
    private CreatorProfile creator;

    @OneToMany(mappedBy = "course")
    private List<Lesson> lessons;
}
