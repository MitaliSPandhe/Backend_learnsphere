package in.learn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.learn.dao.EnrollmentDao;
import in.learn.dao.CourseDao;
import in.learn.dao.UserDao;
import in.learn.entity.Courses;
import in.learn.entity.Enrollments;
import in.learn.entity.Users;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentDao enrollmentDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private CourseDao courseDao;

    public void enrollUser(long userId, long courseId) {
        Users user = userDao.findById(userId);
        Courses course = courseDao.findById(courseId);

        Enrollments enrollment = new Enrollments();
        enrollment.setUser(user);
        enrollment.setCourse(course);

        enrollmentDao.save(enrollment);
    }

    public List<Enrollments> getAllEnrollments() {
        return enrollmentDao.findAll();
    }

    public Enrollments getEnrollmentById(long id) {
        return enrollmentDao.findById(id);
    }

    public List<Enrollments> getByUser(long userId) {
        return enrollmentDao.findByUser(userId);
    }

    public List<Enrollments> getByCourse(long courseId) {
        return enrollmentDao.findByCourse(courseId);
    }

    public void deleteEnrollment(long id) {
        enrollmentDao.delete(id);
    }
}
