package in.learn.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import in.learn.dao.CategoryDao;
import in.learn.entity.Categories;
import in.learn.entity.Courses;

@Service
public class CategoryService {

    @Autowired
    private CategoryDao dao;

    public void save(Categories category) {
        dao.save(category);
    }

    public List<Categories> getAll() {
        return dao.findAll();
    }

    public Categories getById(long id) {
        return dao.findById(id);
    }

    public void update(long id, Categories newData) {
        Categories existing = dao.findById(id);
        existing.setCname(newData.getCname());
        existing.setDescription(newData.getDescription());
        dao.update(existing);
    }

    public void delete(long id) {
        dao.delete(id);
    }

    public List<Courses> getCoursesByCategory(long categoryId) {
        return dao.findCoursesByCategory(categoryId);
    }
}
