package com.vedang.coursell.service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.vedang.coursell.dto.CourseResponse;
import com.vedang.coursell.dto.CreateCourseRequest;
import com.vedang.coursell.dto.LessonResponse;
import com.vedang.coursell.dto.NewLessonRequest;
import com.vedang.coursell.model.*;
import com.vedang.coursell.repository.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
@RequiredArgsConstructor

public class CourseService {

    private final CourseRepo courseRepo;
    private final LessonRepo lessonRepo;
    private final EnrollmentRepo enrollmentRepo;
    private final RazorpayClient razorpayClient;
    private final PaymentInfoRepo paymentInfoRepo;

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

    public void addNewLesson(NewLessonRequest lessonRequest, Long id, User user) {
        Course course = courseRepo.findById(id).orElseThrow();
        if (!course.getCreator().getUser().getId().equals(user.getId()) && user.getRole() != Role.ADMIN) {
            throw new RuntimeException("You are not allowed to add a new lesson into this course");
        }

        Lesson lesson = Lesson.builder()
                .title(lessonRequest.title())
                .type(lessonRequest.lessonType())
                .course(course)
                .fileKey(lessonRequest.fileKey())
                .build();

        lessonRepo.save(lesson);

    }

    public void deleteLesson(Long lessonId, long courseId, User user) {
        Course course = courseRepo.findById(courseId).orElseThrow();

        if (!lessonRepo.existsByIdAndCourseId(lessonId, courseId)) {
            throw new EntityNotFoundException("Lesson not found");
        }

        if (!course.getCreator().getUser().getId().equals(user.getId()) && user.getRole() != Role.ADMIN) {
            throw new AccessDeniedException("You are not allowed to delete this lesson");
        }

        lessonRepo.deleteById(lessonId);

    }

    public Page<LessonResponse> getAllLessons(Long courseId, String search, Pageable pageable) {
        Page<Lesson> page;

        if (search == null || search.isBlank()) {
            page = lessonRepo.findAllByCourseId(courseId, pageable);
        } else {
            page = lessonRepo.searchLessonsInCourse(courseId, search, pageable);
        }

        return page.map(lesson -> new LessonResponse(
                lesson.getId(),
                lesson.getTitle(),
                lesson.getFileKey()
        ));
    }

    public ResponseEntity<?> order(Long courseId, User user) throws RazorpayException {
        if (enrollmentRepo.existsByCourseIdAndStudentId(courseId, user.getId())) {
            throw new IllegalStateException("You are already enrolled in this course");
        }

        Course course = courseRepo.findById(courseId).orElseThrow();

        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount", course.getPrice().multiply(BigDecimal.valueOf(100)));
        orderRequest.put("currency", "INR");

        Order order = razorpayClient.orders.create(orderRequest);

        PaymentInfo payment = PaymentInfo.builder()
                .razorpayOrderId(order.get("id"))
                .amount(course.getPrice())
                .course(course)
                .status(PaymentStatus.PENDING)
                .build();

        paymentInfoRepo.save(payment);
        return ResponseEntity.ok(order.toString());


    }



    public void enroll(Course course, User user) {

        Enrollment enrollment = Enrollment.builder()
                .course(course)
                .student(user)
                .build();

        enrollmentRepo.save(enrollment);

    }
}
