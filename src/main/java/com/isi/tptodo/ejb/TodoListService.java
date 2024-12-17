package com.isi.tptodo.ejb;
import com.isi.tptodo.entities.Todolist;
import com.isi.tptodo.entities.User;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;

import java.util.List;

@Stateless
public class TodoListService {
    @PersistenceContext
    EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("default");
    EntityManager em = managerFactory.createEntityManager();
    EntityTransaction transaction = em.getTransaction();

    public List<Todolist> getTodoListsByUser(User user) {
        return em.createQuery("SELECT t FROM Todolist t WHERE t.user = :user", Todolist.class)
                .setParameter("user", user)
                .getResultList();
    }

    public void createTodoList(User user, String title) {
        transaction.begin();
        Todolist todoList = new Todolist();
        todoList.setTitle(title);
        todoList.setUserByUserId(user);
        em.persist(todoList);
        transaction.commit();
    }
}
