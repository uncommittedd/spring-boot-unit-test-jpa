package com.enigmacamp.hellospring.service;

import com.enigmacamp.hellospring.model.Course;
import com.enigmacamp.hellospring.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    @Value("${course.data.length}")
    Integer dataLength;
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<Course> list() throws Exception {
        List<Course> result = courseRepository.getAll();
        if (result.isEmpty()) {
            throw new Exception("Course not found");
        }
        return result;
    }

    @Override
    public Course create(Course course) {
        try {
            if (!(courseRepository.getAll().size() < dataLength)) {
                throw new Exception("Data is full");
            }
            return courseRepository.create(course);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Course get(String id) throws Exception {
        Optional<Course> result = courseRepository.findById(id);
        if (result.isEmpty()) {
            throw new Exception("Course not found");
        }
        return result.get();
    }

    @Override
    public List<Course> getBy(String key, String value) throws Exception {
        Optional<List<Course>> result = courseRepository.findBy(key, value);
        if (result.isEmpty()) {
            throw new Exception("Course not found");
        }
        return result.get();
    }

    @Override
    public void update(Course course, String id) {
        try {
            courseRepository.update(course, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String id) {
        try {
            courseRepository.delete(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createBulk(List<Course> courses) {
        try {
            courseRepository.bulk(courses);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
