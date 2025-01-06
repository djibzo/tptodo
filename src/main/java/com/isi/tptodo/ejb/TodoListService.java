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

    public List getTodoListsByUser(int iduser) {
        transaction.begin();
        String sql= "SELECT * from todolist where user_id = " + iduser+";";
        List todoLists = em.createNativeQuery(sql, Todolist.class).getResultList();
        return todoLists;
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
