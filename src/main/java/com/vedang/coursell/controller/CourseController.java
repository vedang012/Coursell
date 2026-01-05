package com.vedang.coursell.controller;

import com.vedang.coursell.dto.CourseResponse;
import com.vedang.coursell.dto.CreateCourseRequest;
import com.vedang.coursell.model.User;
import com.vedang.coursell.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/course")
public class CourseController {

    private final CourseService courseService;

    @PostMapping("/new")
    public ResponseEntity<?> newCourse(@Valid @RequestBody CreateCourseRequest courseRequest, @AuthenticationPrincipal User user) {

        if (user == null) {
            return ResponseEntity.status(401).body("Unauthorized");
        }

        courseService.newCourse(courseRequest, user);
        return ResponseEntity.ok(Map.of("message", "Course Created Successfully"));
    }

    @GetMapping("/all")
    public List<CourseResponse> getAllCourses() {
        return courseService.getAllCourses();
    }



}
