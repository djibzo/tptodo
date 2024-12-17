package com.isi.tptodo.ejb;

import com.isi.tptodo.entities.Task;
import com.isi.tptodo.entities.Todolist;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;

import java.util.List;

@Stateless
public class TaskService {
    @PersistenceContext
    EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("default");
    EntityManager em = managerFactory.createEntityManager();
    EntityTransaction transaction = em.getTransaction();

    public List<Task> getTasksByTodoList(Todolist todoList) {
        return em.createQuery("SELECT t FROM Task t WHERE t.id = :todoList", Task.class)
                .setParameter("todoList", todoList)
                .getResultList();
    }

    public void addTask(Todolist todoList, String description) {
        transaction.begin();
        Task task = new Task();
        task.setDescription(description);
        task.setCompleted(0);
        task.setTodolistByTodoListId(todoList);
        em.persist(task);
        transaction.commit();
    }

    public void markTaskAsCompleted(Long taskId) {
        Task task = em.find(Task.class, taskId);
        if (task != null) {
            task.setCompleted(1);
        }
    }
}

