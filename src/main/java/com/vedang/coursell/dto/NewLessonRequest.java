package com.vedang.coursell.dto;

import com.vedang.coursell.model.LessonType;
import jakarta.validation.constraints.NotNull;

public record NewLessonRequest(
        @NotNull
        String title,

        @NotNull
        LessonType lessonType,

        @NotNull
        String fileKey

) {}
