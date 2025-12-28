package in.learn.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.learn.entity.Enrollments;

@Repository
public class EnrollmentDao {

    @Autowired
    private SessionFactory factory;

    public void save(Enrollments enrollment) {
        Session session = factory.openSession();
        session.beginTransaction();
        session.persist(enrollment);
        session.getTransaction().commit();
        session.close();
    }

    public List<Enrollments> findAll() {
        Session session = factory.openSession();
        List<Enrollments> list =
                session.createQuery("from Enrollments", Enrollments.class).list();
        session.close();
        return list;
    }

    public Enrollments findById(long id) {
        Session session = factory.openSession();
        @SuppressWarnings("removal")
		Enrollments enrollment = session.get(Enrollments.class, id);
        session.close();
        return enrollment;
    }

    public List<Enrollments> findByUser(long userId) {
        Session session = factory.openSession();
        List<Enrollments> list = session.createQuery(
                "from Enrollments e where e.user.id = :uid",
                Enrollments.class)
                .setParameter("uid", userId)
                .list();
        session.close();
        return list;
    }

    public List<Enrollments> findByCourse(long courseId) {
        Session session = factory.openSession();
        List<Enrollments> list = session.createQuery(
                "from Enrollments e where e.course.id = :cid",
                Enrollments.class)
                .setParameter("cid", courseId)
                .list();
        session.close();
        return list;
    }

    @SuppressWarnings("removal")
	public void delete(long id) {
        Session session = factory.openSession();
        session.beginTransaction();
        Enrollments enrollment = session.get(Enrollments.class, id);
        if (enrollment != null) {
            session.remove(enrollment);
        }
        session.getTransaction().commit();
        session.close();
    }
}
