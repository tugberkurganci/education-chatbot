package org.vaadin.marcus.data;


import lombok.Data;

import java.time.LocalDate;

@Data
public class Task {

    private String name;

    private String description;

    private boolean isCompleted;

    private LocalDate dueDate; // Görev bitiş tarihi

    public Task(String name, String description, LocalDate dueDate) {
        this.name = name;
        this.description = description;
        this.isCompleted = false;
        this.dueDate = dueDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}