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

    public List<Task> getTasksByTodoList(int todoList) {
//        return em.createQuery("SELECT t FROM Task t WHERE t.id = :todoList", Task.class)
//                .setParameter("todoList", todoList)
//                .getResultList();
        String sql= "SELECT * from task where todo_list_id = " + todoList+";";
        List tasks = em.createNativeQuery(sql, Task.class).getResultList();
        return tasks;
    }

    public void addTask(Todolist todoList, String description, int completed ) {
        transaction.begin();
        Task task = new Task();
        task.setDescription(description);
        task.setCompleted(completed);
        task.setTodolistByTodoListId(todoList);
        em.persist(task);
        transaction.commit();
    }

    public void markTaskAsCompleted(int taskId) {
        transaction.begin();
        Task task = em.find(Task.class, taskId);
        if (task != null) {
            task.setCompleted(1);
            em.merge(task);
        }
        transaction.commit();
    }
}

