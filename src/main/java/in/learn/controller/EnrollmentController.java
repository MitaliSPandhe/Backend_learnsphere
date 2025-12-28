package in.learn.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import in.learn.entity.Enrollments;
import in.learn.service.EnrollmentService;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentService service;

    @PostMapping("/enroll")
    public String enrollUser(
            @RequestParam long userId,
            @RequestParam long courseId) {
        service.enrollUser(userId, courseId);
        return "User enrolled successfully";
    }

    @GetMapping
    public List<Enrollments> getAll() {
        return service.getAllEnrollments();
    }

    @GetMapping("/{id}")
    public Enrollments getById(@PathVariable long id) {
        return service.getEnrollmentById(id);
    }

    @GetMapping("/user/{userId}")
    public List<Enrollments> getByUser(@PathVariable long userId) {
        return service.getByUser(userId);
    }

    @GetMapping("/course/{courseId}")
    public List<Enrollments> getByCourse(@PathVariable long courseId) {
        return service.getByCourse(courseId);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable long id) {
        service.deleteEnrollment(id);
        return "Enrollment deleted successfully";
    }
}
