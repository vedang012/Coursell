package com.vedang.coursell.repository;

import com.vedang.coursell.model.Lesson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepo extends JpaRepository<Lesson, Long> {
    boolean existsByIdAndCourseId(Long lessonId, Long courseId);

    @Query("""
    select l from Lesson l
    where l.course.id = :courseId
      and lower(l.title) like lower(concat('%', :search, '%'))
""")
    Page<Lesson> searchLessonsInCourse(
            @Param("courseId") Long courseId,
            @Param("search") String search,
            Pageable pageable
    );


    Page<Lesson> findAllByCourseId(Long courseId, Pageable pageable);
}
