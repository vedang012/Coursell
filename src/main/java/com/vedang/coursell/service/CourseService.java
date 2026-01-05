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
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public List<CourseResponse> getAllCourses() {
        List<CourseResponse> list = new ArrayList<>();
        List<Course> courses = courseRepo.findAll();

        for (Course course : courses) {
            list.add(new CourseResponse(course.getId(), course.getCourseName(), course.getCreator().getUser().getName(), course.getPrice()));
        }
        return list;
    }
}
