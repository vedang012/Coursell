package com.vedang.coursell.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
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
    @NotNull
    @DecimalMin(value = "0.0", inclusive = true, message = "Price cannot be negative")
    private BigDecimal price;

    @Column(nullable = false)
    @NotNull
    private String courseName;

    @Column(nullable = false, length = 500)
    @NotNull
    private String description;

    @ManyToOne(optional = false)
    private CreatorProfile creator;

    @OneToMany(mappedBy = "course")
    private List<Lesson> lessons;
}
