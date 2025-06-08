package gamekins.project.controller;

import gamekins.project.domain.Course;
import gamekins.project.domain.dto.CourseDTO;
import gamekins.project.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public List<CourseDTO> getAllCourses() {
        return courseService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable Long id) {
        return courseService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public CourseDTO createCourse(@RequestBody CourseDTO courseDTO) {
        return courseService.save(courseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course courseDetails) {
        return courseService.findById(id)
                .map(course -> {
                    course.setName(courseDetails.getName());
                    course.setCode(courseDetails.getCode());
                    Course updatedCourse = courseService.save(course);
                    return ResponseEntity.ok(updatedCourse);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCourse(@PathVariable Long id) {
        return courseService.findById(id)
                .map(course -> {
                    courseService.deleteById(id);
                    return ResponseEntity.noContent().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
