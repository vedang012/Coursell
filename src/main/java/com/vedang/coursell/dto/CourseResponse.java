package com.vedang.coursell.dto;

import java.math.BigDecimal;

public record CourseResponse(
        Long id,
        String name,
        String creatorName,
        BigDecimal price
) {}
