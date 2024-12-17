package com.isi.tptodo.ejb;

import com.isi.tptodo.entities.User;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;

@Stateless
public class UserService {
    @PersistenceContext
    EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("default");
    EntityManager em = managerFactory.createEntityManager();
    EntityTransaction transaction = em.getTransaction();

    public User findByUsername(String username) {
        return em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                .setParameter("username", username)
                .getResultStream()
                .findFirst()
                .orElse(null);
    }

    public void registerUser(String username, String password) {
        transaction.begin();
        User user = new User();
        user.setUsername(username);
        user.setPassword(password); // Ajoutez un hashage de mot de passe ici
        em.persist(user);
        transaction.commit();
    }
}
