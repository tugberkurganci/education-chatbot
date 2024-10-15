package org.vaadin.marcus.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vaadin.marcus.data.EducationData;
import org.vaadin.marcus.data.Mentor;
import org.vaadin.marcus.data.Project;
import org.vaadin.marcus.data.Student;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EducationController {

    private final EducationData educationData;

    public EducationController(EducationData educationData) {
        this.educationData = educationData;
    }

    @GetMapping("/all-data")
    public AllDataResponse getAllData() {
        AllDataResponse response = new AllDataResponse();
        response.setStudents(educationData.getStudents());
        response.setMentors(educationData.getMentors());
        response.setProjects(educationData.getProjects());
        return response;
    }

    public static class AllDataResponse {
        private List<Student> students;
        private List<Mentor> mentors;
        private List<Project> projects;

        // Getters and setters
        public List<Student> getStudents() { return students; }
        public void setStudents(List<Student> students) { this.students = students; }

        public List<Mentor> getMentors() { return mentors; }
        public void setMentors(List<Mentor> mentors) { this.mentors = mentors; }

        public List<Project> getProjects() { return projects; }
        public void setProjects(List<Project> projects) { this.projects = projects; }
    }
}