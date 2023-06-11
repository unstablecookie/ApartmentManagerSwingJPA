package tst.example.AptMgr;

import java.util.List;
import java.util.ArrayList;

import java.util.concurrent.ExecutionException;

import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.ParameterExpression;
import jakarta.persistence.criteria.Expression;


public class UserManager {
	private static EntityManagerFactory factory;
    private static final Logger logger = LoggerFactory.getLogger(UserManager.class);
    
    public UserManager() {
    	try {
    		factory = Persistence.createEntityManagerFactory("persistence");
    	}catch (Throwable ex) {
    		logger.error("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
    	}
    }
    public void addUser(String fName, String lName, String uName) {
    	EntityManager entityManager = factory.createEntityManager();
    	try {
    		User user = new User(fName,lName,uName);
    		entityManager.getTransaction().begin();
    		entityManager.persist(user);
    		entityManager.getTransaction().commit();
    	} catch(Exception e) {
    		logger.error("Failed to add an employee" + e);
            entityManager.getTransaction().rollback();
    	} finally {
    		entityManager.close();
    	}
    }
    
    public List<User> listUsers(){
    	EntityManager entityManager = factory.createEntityManager();
    	List<User> list = new ArrayList<>();
    	try {
    		entityManager.getTransaction().begin();
            list = entityManager.createQuery("from User").getResultList();
            entityManager.getTransaction().commit();
    	}catch(Exception e){
            logger.error("Failed to make a transaction" + e);
        }finally {
            entityManager.close();
        }
    	return list;
    }
    
    public User getUser(Long id) {
    	EntityManager entityManager = factory.createEntityManager();
    	User user = new User();
    	try {
    		entityManager.getTransaction().begin();
    		user = entityManager.find(tst.example.AptMgr.User.class, id);
    		entityManager.getTransaction().commit();
    	}catch(Exception e) {
    		logger.error("search failed :" + e);
    	}finally {
    		entityManager.close();
    	}
    	return user;
    }

}





