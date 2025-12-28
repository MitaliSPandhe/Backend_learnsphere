package in.learn.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import in.learn.constants.CoursePopularity;
import in.learn.constants.Course_Type;
import in.learn.constants.Level;
import in.learn.constants.Price;
import in.learn.entity.Courses;
import in.learn.service.CourseService;

@RestController
public class CourseController {

    @Autowired
    private CourseService service;

    @PostMapping("/addcourses")
    public String addCourse(@RequestBody Courses course) {
        service.save(course);
        return "Course added successfully";
    }

    @GetMapping("getall/courses")
    public List<Courses> getAllCourses() {
        return service.getAll();
    }

    @GetMapping("coursesby/{id}")
    public Courses getCourseById(@PathVariable long id) {
        return service.getById(id);
    }

    @PutMapping("courses/update/{id}")
    public String updateCourse(@PathVariable long id, @RequestBody Courses course) {
        service.update(id, course);
        return "Course updated successfully";
    }

    @DeleteMapping("courses/delete/{id}")
    public String deleteCourse(@PathVariable long id) {
        service.delete(id);
        return "Course deleted successfully";
    }
    
    @GetMapping("/courses/filter")
    public List<Courses> filterCourses(
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Price price,
            @RequestParam(required = false) Level level,
            @RequestParam(required = false) Course_Type coursetype,
            @RequestParam(required = false) CoursePopularity popularity
            ) {

        return service.filterCourses(
                categoryId, price, level, coursetype, popularity);
    }

}
