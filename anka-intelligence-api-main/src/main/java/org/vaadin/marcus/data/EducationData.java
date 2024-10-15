package org.vaadin.marcus.data;

import lombok.Data;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Data
public class EducationData {

    private final List<Student> students;
    private final List<Mentor> mentors;
    private final List<Project> projects;

    public EducationData() {
        this.students = new ArrayList<>();
        this.mentors = new ArrayList<>();
        this.projects = new ArrayList<>();

        initDemoData();
    }

    private void initDemoData() {
        // Create sample students
        students.add(new Student("John", "Doe", "john.doe@example.com", "Visual", "AwyJoHiE6iwip2RkgRzKLMEeLL5HWiZb3cpWEM5ujf9S"));
        students.add(new Student("Jane", "Smith", "jane.smith@example.com", "Auditory", "EiKyqEdevVhtnSVZdMApvNwVSMypjJvyi86zWdDxexuH"));
        students.add(new Student("Michael", "Johnson", "michael.johnson@example.com", "Kinesthetic", "2"));

        // Create sample mentors
        mentors.add(new Mentor("Alice", "Brown", "alice.brown@example.com", Arrays.asList("Entrepreneurship", "Finance")));
        mentors.add(new Mentor("Bob", "Williams", "bob.williams@example.com", Arrays.asList("Technology", "Product Development")));

        // Create sample projects and tasks
        Project project1 = new Project("Startup Launch", "Develop and launch a new startup", "Planning");
        Project project2 = new Project("AI Integration", "Integrate AI into existing systems", "Development");
        project1.setProjectEward(1);
        project1.setProjectEward(1);

        Task task1 = new Task("Market Research", "Conduct market research for the startup", LocalDate.now().plusDays(7));
        Task task2 = new Task("Develop AI Model", "Develop and train AI model for integration", LocalDate.now().plusDays(14));

        project1.getTasks().add(task1);
        project2.getTasks().add(task2);

        projects.add(project1);
        projects.add(project2);

        // Assign students to projects
        students.get(0).getProjects().add(project1);
        students.get(1).getProjects().add(project2);

        // Assign mentors to students
        mentors.get(0).getMentees().add(students.get(0));
        mentors.get(1).getMentees().add(students.get(1));

        // Add sample NFT certificates
        students.get(0).getCertificates().add(new NFTCertificate("CERT001", "Startup Launch Course", LocalDate.now().toString()));
        students.get(1).getCertificates().add(new NFTCertificate("CERT002", "AI Integration Course", LocalDate.now().toString()));

        System.out.println("Demo data initialized");
    }


    public List<Student> getStudents() {
        return students;
    }

    public List<Mentor> getMentors() {
        return mentors;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public Project addProject(String projectTitle, String projectDescription, String projectStage) {
        Project project=new Project(projectTitle,  projectDescription,  projectStage);
        projects.add(project);
        return  project;

    }

    public void updateProject(Project updatedProject, Student updatedStudent) {
        // Öncelikle öğrenciyi mevcut listeden bulalım
        for (Student student : students) {
            if (student.getEmail().equals(updatedStudent.getEmail())) {  // Öğrenciyi eposta ile buluyoruz
                // Şimdi öğrenciye atanmış projelerde bu projeyi bulalım
                for (Project project : student.getProjects()) {
                    if (project.getDescription().equals(updatedProject.getDescription())) {  // Proje başlığı ile karşılaştı

                                project.setProjectStage("Completed");
students.forEach(student1 -> System.out.println(student1));
                        System.out.println("Project updated for student: " + student.getFirstName() + " " + student.getLastName());
                        return;  // Güncelleme sonrası metodu sonlandır
                    }
                }
            }
        }
        System.out.println("Student or project not found");
    }
}