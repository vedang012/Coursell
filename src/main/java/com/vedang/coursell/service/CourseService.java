package com.vedang.coursell.service;

import com.vedang.coursell.dto.CourseResponse;
import com.vedang.coursell.dto.CreateCourseRequest;
import com.vedang.coursell.model.Course;
import com.vedang.coursell.model.Role;
import com.vedang.coursell.model.User;
import com.vedang.coursell.repository.CourseRepo;
import com.vedang.coursell.repository.CreatorProfileRepo;
import com.vedang.coursell.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor

public class CourseService {

    private final UserRepo userRepo;
    private final CourseRepo courseRepo;
    private final CreatorProfileRepo creatorProfileRepo;

    public void newCourse(CreateCourseRequest courseRequest, User user) {

        if (user.getRole() != Role.CREATOR) {
            throw new RuntimeException("No creator account found.");
        }

        Course course = Course.builder()
                .price(courseRequest.price())
                .courseName(courseRequest.courseName())
                .description(courseRequest.description())
                .build();

        course.setCreator(user.getCreatorProfile());

        courseRepo.save(course);

    }

    public Page<CourseResponse> getAllCourses(String search, Pageable pageable) {

        Page<Course> page;

        if(search == null || search.isBlank()) {
            page = courseRepo.findAll(pageable);
        } else {
            page = courseRepo.searchCourses(search, pageable);
        }

        return page.map(course -> new CourseResponse(
                        course.getId(),
                        course.getCourseName(),
                        course.getCreator().getUser().getName(),
                        course.getPrice(),
                        course.getDescription()
                ));

    }

    public void deleteCourse(Long id, User user) {
        Course course = courseRepo.findById(id).orElseThrow();
        if (!course.getCreator().getUser().getId().equals(user.getId()) && user.getRole() != Role.ADMIN) {
            throw new RuntimeException("Not allowed to delete this course");
        }
        courseRepo.delete(course);
    }
}
