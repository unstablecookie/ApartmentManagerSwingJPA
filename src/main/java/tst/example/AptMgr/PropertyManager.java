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


public class PropertyManager {
	private static EntityManagerFactory factory;
    private static final Logger logger = LoggerFactory.getLogger(PropertyManager.class);
    
    public PropertyManager() {
    	try {
    		factory = Persistence.createEntityManagerFactory("persistence");
    	}catch(Throwable ex) {
    		logger.error("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
    	}
    	
    }
    
    public void addProperty(AptType type, Integer area, Integer build,User usr,byte[] photoArr) {
    	EntityManager entityManager = factory.createEntityManager();
    	try {
    		Property property = new Property(type,area,build,usr,photoArr);
    		entityManager.getTransaction().begin();
    		entityManager.persist(property);
    		entityManager.getTransaction().commit();
    	}catch(Exception e) {
    		logger.error("Failed to add property" + e);
            entityManager.getTransaction().rollback();
    	}finally {
    		entityManager.close();
    	}
    }
    
    public List<Property> listProperty(){
    	EntityManager entityManager = factory.createEntityManager();
    	List<Property> list = new ArrayList<>();
    	try {
    		entityManager.getTransaction().begin();
    		list = entityManager.createQuery("from Property").getResultList();
    		entityManager.getTransaction().commit();
    	}catch(Exception e) {
    		logger.error("Failed to make a transaction" + e);
    	}finally {
            entityManager.close();
        }
    	return list;
    }
    
    public List<Property> listPropertyByUser(User user){
    	EntityManager entityManager = factory.createEntityManager();
    	List<Property> list = new ArrayList<>();
    	try {
    		entityManager.getTransaction().begin();
    		list = entityManager.createQuery("SELECT p FROM Property p WHERE p.user.id=?1")//FOREIGH KEY REFERENCE
    				.setParameter(1, user.getId())
    				.getResultList();
    		entityManager.getTransaction().commit();
    	}catch(Exception e) {
    		logger.error("Failed to make a transaction" + e);
    	}finally {
            entityManager.close();
        }
    	return list;
    	
    }
    
    public Property getPropertyById(Long id) {
    	Property property;
    	EntityManager entityManager = factory.createEntityManager();
    	try {
    		entityManager.getTransaction().begin();
    		property = entityManager.find(tst.example.AptMgr.Property.class, id);
    		entityManager.getTransaction().commit();
    	}catch(Exception e) {
    		logger.error("Failed to make a transaction" + e);
    		property = null;
    	}finally {
            entityManager.close();
        }
    	return property;
    }
    

}
