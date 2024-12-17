package com.isi.tptodo.entities;

import jakarta.persistence.*;


@Entity
public class Task {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "description")
    private String description;

    @Basic
    @Column(name = "completed")
    private int completed;

    @ManyToOne
    @JoinColumn(name = "todo_list_id", referencedColumnName = "id", nullable = false)
    private Todolist todolistByTodoListId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCompleted() {
        return completed;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
    }

    public Todolist getTodolistByTodoListId() {
        return todolistByTodoListId;
    }

    public void setTodolistByTodoListId(Todolist todolistByTodoListId) {
        this.todolistByTodoListId = todolistByTodoListId;
    }
}