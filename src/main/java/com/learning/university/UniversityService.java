package com.learning.university;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UniversityService {

    CourseRepository courseRepository;

    UniversityService(CourseRepository courseRepository)
    {
        this.courseRepository = courseRepository;
    }

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public Course update(Course newCourse, int id) {
        return this.courseRepository.findById(id).map(employee -> {
            employee.setCname(newCourse.getCname());
            employee.setSubjects(newCourse.getSubjects());
            employee.setMaxStudentAllowed(newCourse.getMaxStudentAllowed());
            return courseRepository.save(employee);
        }).orElseGet( () ->
                {
                    newCourse.setId(id);
                    return courseRepository.save(newCourse);
                });
    }


    public Course find(int id) throws Exception {
        return this.courseRepository.findById(id).orElseThrow(() -> new Exception("Course not found"));
    }

    public void deleteById(int id) {
        this.courseRepository.deleteById(id);
    }

    public Course save(Course newCourse) {
        return this.courseRepository.save(newCourse);
    }
}
