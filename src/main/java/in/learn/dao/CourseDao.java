package in.learn.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.learn.entity.Courses;
import in.learn.constants.CoursePopularity;
import in.learn.constants.Course_Type;
import in.learn.constants.Level;
import in.learn.constants.Price;



@Repository
public class CourseDao {

    @Autowired
    private SessionFactory factory;

    public void save(Courses course) {
        Session session = factory.openSession();
        session.beginTransaction();
        session.persist(course);
        session.getTransaction().commit();
        session.close();
    }

    public List<Courses> findAll() {
        Session session = factory.openSession();
        List<Courses> list =
                session.createQuery("from Courses", Courses.class).list();
        session.close();
        return list;
    }

    public Courses findById(long id) {
        Session session = factory.openSession();
        @SuppressWarnings("removal")
		Courses course = session.get(Courses.class, id);
        session.close();
        return course;
    }

    public void update(Courses course) {
        Session session = factory.openSession();
        session.beginTransaction();
        session.merge(course);
        session.getTransaction().commit();
        session.close();
    }

    public void delete(long id) {
        Session session = factory.openSession();
        session.beginTransaction();
        @SuppressWarnings("removal")
		Courses course = session.get(Courses.class, id);
        session.remove(course);
        session.getTransaction().commit();
        session.close();
    }
    
    public List<Courses> filterCourses(
            Long categoryId,
            Price price,
            Level level,
            Course_Type coursetype,
            CoursePopularity popularity) {

        Session session = factory.openSession();

        String hql = "from Courses c where 1=1";

        if (categoryId != null)
            hql += " and c.category.id = :categoryId";

        if (price != null)
            hql += " and c.price = :price";

        if (level != null)
            hql += " and c.level = :level";

        if (coursetype != null)
            hql += " and c.coursetype = :coursetype";

        if (popularity != null)
            hql += " and c.popularity = :popularity";

        Query<Courses> query = session.createQuery(hql, Courses.class);

        if (categoryId != null)
            query.setParameter("categoryId", categoryId);

        if (price != null)
            query.setParameter("price", price);

        if (level != null)
            query.setParameter("level", level);

        if (coursetype != null)
            query.setParameter("coursetype", coursetype);

        if (popularity != null)
            query.setParameter("popularity", popularity);


        List<Courses> list = query.list();
        session.close();
        return list;
    }

}
