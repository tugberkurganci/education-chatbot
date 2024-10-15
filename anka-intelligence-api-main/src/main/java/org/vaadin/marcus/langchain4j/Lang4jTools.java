package org.vaadin.marcus.langchain4j;

import org.vaadin.marcus.data.*;
import org.vaadin.marcus.service.EducationService;
import dev.langchain4j.agent.tool.Tool;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Lang4jTools {

    private final EducationService service;

    public Lang4jTools(EducationService service) {
        this.service = service;
    }

    @Tool("Retrieves information about a specific student, including their name, email, projects, token balance, and certificates.")
    public Student getStudentDetails(String walletID) {
        return service.getStudentDetails(walletID);
    }

    @Tool("Allows to add new tasks to a student's project or modify existing tasks. Provides functionalities to update task descriptions, due dates, and completion status.")
    public void updateProjectTasks(String walletID, String projectName, List<Task> newTasks,String projectStage) {
        service.updateProjectTasks(walletID, projectName,projectStage);
    }

    @Tool("Retrieves information about a specific mentor, including their name, email, areas of expertise, and mentees.")
    public Mentor getMentorDetails(String email) {
        return service.getMentorDetails(email);
    }

    @Tool("Updates a student's token balance after completing certain tasks or achieving milestones.")
    public void updateTokenBalance(String walletID, int amount) {
        service.updateTokenBalance(walletID, amount);
    }

    @Tool("Issues an NFT certificate to a student upon completion of a project or achievement of a milestone.")
    public void issueNFTCertificate(String walletID, String projectName, String certificateId) {
        service.issueNFTCertificate(walletID, projectName, certificateId);
    }

    @Tool("Öğrenci projeyi kabul ederse, projeyi ekler ve başlatır. Eğer ChatGPT otomatik proje üretirse, proje başlığı, açıklaması ve aşamasını içerir.")
    public String enrollAndStartDynamicProject(String walletId, int amountString, String projectTitle, String projectDescription, String projectStage) {


            // Dinamik projeyi öğrenciye ekle ve başlat
            Project project = createDynamicProject(projectTitle, projectDescription, projectStage);
            service.enrollStudentInProject(walletId, project);


            return "Proje başarıyla eklendi ve başlatıldı: " + project.getName() + "\n" +
                    "Ödülünüz: " + project.getProjectEward() + " Solana token";

    }

    @Tool("Eğer ChatGPT otomatik proje üretirse, proje başlığı, açıklaması ve aşamasını içerir.")
    private Project createDynamicProject(String projectTitle, String projectDescription, String projectStage) {
        return service.addProject( projectTitle,  projectDescription,  projectStage);
    }
}
