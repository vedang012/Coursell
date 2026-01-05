package com.vedang.coursell.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record CreateCourseRequest(
        @NotNull
        @DecimalMin(value = "0.0", inclusive = true)
        BigDecimal price,

        @NotNull
        String courseName,

        @NotNull
        @Size(max = 500, message = "Description must be at most 500 characters")
        String description
) {}

