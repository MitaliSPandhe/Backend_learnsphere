package in.learn.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.learn.entity.Categories;
import in.learn.entity.Courses;

@Repository
public class CategoryDao {

    @Autowired
    private SessionFactory factory;

    public void save(Categories category) {
        Session session = factory.openSession();
        session.beginTransaction();
        session.persist(category);
        session.getTransaction().commit();
        session.close();
    }

    public List<Categories> findAll() {
        Session session = factory.openSession();
        List<Categories> list =
                session.createQuery("from Categories", Categories.class).list();
        session.close();
        return list;
    }

    public Categories findById(long id) {
        Session session = factory.openSession();
        @SuppressWarnings("removal")
		Categories category = session.get(Categories.class, id);
        session.close();
        return category;
    }

    public void update(Categories category) {
        Session session = factory.openSession();
        session.beginTransaction();
        session.merge(category);
        session.getTransaction().commit();
        session.close();
    }

    public void delete(long id) {
        Session session = factory.openSession();
        session.beginTransaction();
        @SuppressWarnings("removal")
		Categories category = session.get(Categories.class, id);
        session.remove(category);
        session.getTransaction().commit();
        session.close();
    }

    public List<Courses> findCoursesByCategory(long categoryId) {
        Session session = factory.openSession();
        List<Courses> list = session.createQuery(
                "from Courses where category_id = :cid", Courses.class)
                .setParameter("cid", categoryId)
                .list();
        session.close();
        return list;
    }
}

