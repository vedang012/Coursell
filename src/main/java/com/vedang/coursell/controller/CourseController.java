package com.vedang.coursell.controller;

import com.vedang.coursell.dto.CourseResponse;
import com.vedang.coursell.dto.CreateCourseRequest;
import com.vedang.coursell.model.User;
import com.vedang.coursell.service.CourseService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

    @RolesAllowed({"CREATOR, ADMIN"})
    @PostMapping("/new")
    public ResponseEntity<?> newCourse(@Valid @RequestBody CreateCourseRequest courseRequest, @AuthenticationPrincipal User user) {

        if (user == null) {
            return ResponseEntity.status(401).body("Unauthorized");
        }

        courseService.newCourse(courseRequest, user);
        return ResponseEntity.ok(Map.of("message", "Course Created Successfully"));
    }

    @GetMapping("/all")
    public Page<CourseResponse> getAllCourses(@RequestParam(required = false) String search,
                                              @PageableDefault(size = 12, sort = "id") Pageable pageable) {
        return courseService.getAllCourses(search, pageable);
    }

    @DeleteMapping("/{id}")
    @RolesAllowed({"CREATOR", "ADMIN"})
    public ResponseEntity<?> deleteCourse(@PathVariable Long id, @AuthenticationPrincipal User user) {
        courseService.deleteCourse(id, user);
        return ResponseEntity.ok().body("deleted");
    }



}
