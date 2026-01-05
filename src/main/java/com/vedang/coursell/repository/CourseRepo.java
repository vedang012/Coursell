package com.vedang.coursell.repository;

import com.vedang.coursell.model.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepo extends JpaRepository<Course, Long> {

    @Query("""
        select c from Course c
        where lower(c.courseName) like lower(concat('%', :search, '%'))
           or lower(c.description) like lower(concat('%', :search, '%'))
           or lower(c.creator.user.name) like  lower(concat('%', :search, '%'))
    """)
    Page<Course> searchCourses(@Param("search") String search, Pageable pageable);

}
