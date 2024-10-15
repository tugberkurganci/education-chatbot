package org.vaadin.marcus.data;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Project {
    private String name;
    private String description;
    private List<Task> tasks;
    private List<Student> participants;
    private String projectStage;// Örneğin, planlama, geliştirme, tamamlandı
    private int projectEward;

    public Project(String name, String description, String projectStage) {
        this.name = name;
        this.description = description;
        this.tasks = new ArrayList<>();
        this.participants = new ArrayList<>();
        this.projectStage = projectStage;
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

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Student> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Student> participants) {
        this.participants = participants;
    }

    public String getProjectStage() {
        return projectStage;
    }

    public void setProjectStage(String projectStage) {
        this.projectStage = projectStage;
    }

    public int getProjectEward() {
        return projectEward;
    }

    public void setProjectEward(int projectEward) {
        this.projectEward = projectEward;
    }
}

