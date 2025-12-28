package in.learn.dao;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import in.learn.entity.Users;

@Repository
public class UserDao {

	@Autowired
	SessionFactory factory;

	public boolean register(Users user) {
		Session session = factory.openSession();
		session.persist(user);
		session.beginTransaction().commit();
		return true;
	}
	
	 public Users findByEmail(String email) {

	        Session session = factory.openSession();

	        Users user = session.createQuery(
	                "from Users where email = :email",
	                Users.class)
	                .setParameter("email", email)
	                .uniqueResult();

	        session.close();
	        return user;
	    }
	 public void updateUser(Users user) {
	        Session session = factory.openSession();
	        session.beginTransaction();
	        session.merge(user);
	        session.getTransaction().commit();
	        session.close();
	    }
	 
	 @SuppressWarnings("removal")
	public Users findById(long id) {

		    Session session = factory.openSession();
		    Users user = session.get(Users.class, id);
		    session.close();
		    return user;
		}
	 
	 public List<Users> findAll() {

		    Session session = factory.openSession();
		    List<Users> list = session.createQuery("from Users", Users.class).list();
		    session.close();
		    return list;
		}

}