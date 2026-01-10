package com.vedang.coursell.controller;

import com.razorpay.RazorpayException;
import com.vedang.coursell.dto.CourseResponse;
import com.vedang.coursell.dto.CreateCourseRequest;
import com.vedang.coursell.dto.LessonResponse;
import com.vedang.coursell.dto.NewLessonRequest;
import com.vedang.coursell.model.User;
import com.vedang.coursell.service.CourseService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/course")
public class CourseController {

    private final CourseService courseService;

    @RolesAllowed({"CREATOR", "ADMIN"})
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
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('CREATOR','ADMIN')")
    @PostMapping("/{id}/lesson/new")
    public ResponseEntity<?> newLessonRequest(@RequestBody @Valid NewLessonRequest newLessonRequest, @AuthenticationPrincipal User user, @PathVariable Long id) {
        courseService.addNewLesson(newLessonRequest, id, user);
        return ResponseEntity.ok("new lesson created");
    }

    @PreAuthorize("hasAnyRole('CREATOR','ADMIN')")
    @DeleteMapping("/{courseId}/lesson/{lessonId}")
    public ResponseEntity<?> deleteLesson(@PathVariable Long lessonId, @PathVariable long courseId, @AuthenticationPrincipal User user) {
        courseService.deleteLesson(lessonId, courseId, user);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/lessons/")
    public Page<LessonResponse> getAllLessonsFromACourse(@PathVariable(name = "id") Long courseId,
                                                       @RequestParam(required = false) String search,
                                                       @PageableDefault(size = 10, sort = "id") Pageable pageable) {
        return courseService.getAllLessons(courseId, search, pageable);
    }


    @PreAuthorize("hasAnyRole('STUDENT', 'ADMIN')")
    @PostMapping("/{id}/pay")
    public ResponseEntity<?> pay(@PathVariable(name = "id") Long courseId,
                                    @AuthenticationPrincipal User user) throws RazorpayException {

        return courseService.order(courseId, user);
    }



}
