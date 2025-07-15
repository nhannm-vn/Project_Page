/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nhannm.registration;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author ADMIN
 */
public class RegistrationBLO{

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("MVC2SE182080PU");

    public void persist(Object object) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
    public Registration checkLogin(String username, String password){
        Registration result = null;
        EntityManager em = emf.createEntityManager();
        
        String jpql = "Select r "
                    + "From Registration r "
                    + "Where r.username = :username "
                    + "And r.password = :password";
        try {
            Query query = em.createQuery(jpql);
            
            query.setParameter("username", username);
            query.setParameter("password", password);

            // Neu ra nhieu dong thi phai get resultList
            result = (Registration) query.getSingleResult();
            
        } finally {
            em.close();
        }
        
        return result;
    }
    
    public List<Registration> searchLastname(String searchValue){
        List<Registration> result = null;
        
        EntityManager em = emf.createEntityManager();
        
        String jpql = "Select r "
                    + "From Registration r "
                    + "Where r.lastname Like :lastname";
        try {
            Query query = em.createQuery(jpql);
            
            query.setParameter("lastname", "%" + searchValue + "%");
            result = query.getResultList();
        } finally {
            em.close();
        }
        
        return result;
    }
    //*Tat ca cau lenh CUD khong viet sql
    public boolean deleteAccount(String username){
        boolean result = false;
        EntityManager em = emf.createEntityManager();
        try {
            Registration reg = em.find(Registration.class, username);
            if(reg != null){
                em.getTransaction().begin();
                em.remove(reg);
                em.getTransaction().commit();
            }
            result = true;
        } finally {
            em.close();
        }
        
        return result;
    }
    
}
