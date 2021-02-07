package crud.project.dao;

import crud.project.config.AppConfig;
import crud.project.models.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Component
public class UserDAO {
    public EntityManager em;

    public UserDAO(){
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        EntityManagerFactory emf = context.getBean(EntityManagerFactory.class);
        this.em = emf.createEntityManager();
    }

    public User add(User user){
        em.getTransaction().begin();
        User userFromDB = em.merge(user);
        em.getTransaction().commit();
        return userFromDB;
    }

    public void delete(long id){
        em.getTransaction().begin();
        em.remove(get(id));
        em.getTransaction().commit();
    }

    public User get(long id){
        return em.find(User.class, id);
    }

    public void update(User user){
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
    }

    public List<User> getAll(){
        TypedQuery<User> namedQuery = this.em.createQuery("from User", User.class);
        return namedQuery.getResultList();
    }

}
