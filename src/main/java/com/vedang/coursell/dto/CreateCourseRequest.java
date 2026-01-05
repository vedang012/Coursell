package com.vedang.coursell.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreateCourseRequest(
        @NotNull
        @DecimalMin(value = "0.0", inclusive = true)
        BigDecimal price,

        @NotNull
        String courseName,

        @NotNull
        String description
) {}

