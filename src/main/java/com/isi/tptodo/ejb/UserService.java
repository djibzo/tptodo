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
    public User authenticate(String username, String password) {
        try {
            // Requête pour trouver l'utilisateur avec le nom d'utilisateur et le mot de passe
            TypedQuery<User> query = em.createQuery(
                    "SELECT u FROM User u WHERE u.username = :username AND u.password = :password",
                    User.class
            );
            query.setParameter("username", username);
            query.setParameter("password", password);

            return query.getSingleResult(); // Retourne l'utilisateur si trouvé
        } catch (Exception e) {
            return null; // Aucun utilisateur trouvé ou erreur
        }
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
