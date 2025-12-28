package in.learn.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import in.learn.dao.CategoryDao;
import in.learn.dao.CourseDao;
import in.learn.entity.Categories;
import in.learn.entity.Courses;
import in.learn.constants.CoursePopularity;
import in.learn.constants.Course_Type;
import in.learn.constants.Level;
import in.learn.constants.Price;
import in.learn.service.CourseService;

@Service
public class CourseService {

    @Autowired
    private CourseDao courseDao;

    @Autowired
    private CategoryDao categoryDao;

    public void save(Courses course) {

        Categories category = categoryDao.findById(
                course.getCategory().getId());

        course.setCategory(category);
        courseDao.save(course);
    }

    public List<Courses> getAll() {
        return courseDao.findAll();
    }

    public Courses getById(long id) {
        return courseDao.findById(id);
    }

    public void update(long id, Courses newData) {

        Courses existing = courseDao.findById(id);

        existing.setTitle(newData.getTitle());
        existing.setDescription(newData.getDescription());
        existing.setTeacherName(newData.getTeacherName());
        existing.setPrice(newData.getPrice());
        existing.setLevel(newData.getLevel());
        existing.setCoursetype(newData.getCoursetype());
        existing.setPopularity(newData.getPopularity());
        existing.setThumbnail_url(newData.getThumbnail_url());

    
        Categories category = categoryDao.findById(
                newData.getCategory().getId());
        existing.setCategory(category);

        courseDao.update(existing);
    }

    public void delete(long id) {
        courseDao.delete(id);
    }
    
    
    public List<Courses> filterCourses(
            Long categoryId,
            Price price,
            Level level,
            Course_Type coursetype,
            CoursePopularity popularity) {

        return courseDao.filterCourses(
                categoryId, price, level, coursetype, popularity);
    }

}
