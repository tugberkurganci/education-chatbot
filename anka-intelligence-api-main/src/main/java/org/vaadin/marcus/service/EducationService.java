package org.vaadin.marcus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.marcus.data.*;
import org.springframework.http.HttpHeaders;


import java.util.List;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpMethod;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.client.RestTemplate;

@Service
public class EducationService {

    private EducationData educationData;
    @Autowired
    private RestTemplate restTemplate;
    public EducationService(EducationData educationData) {
        this.educationData = educationData;
    }

    public EducationData getEducationData() {
        return educationData;
    }

    public void setEducationData(EducationData educationData) {
        this.educationData = educationData;
    }

    public Student getStudentDetails(String walletId) {
        return educationData.getStudents().stream()
                .filter(student -> student.getWalletId().equals(walletId))
                .findFirst()
                .orElse(null);
    }

    public Mentor getMentorDetails(String email) {
        return educationData.getMentors().stream()
                .filter(mentor -> mentor.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    public void updateProjectTasks(String walletId, String projectName,String projectStage) {
        Student student = getStudentDetails(walletId);
        if (student != null) {
            Project project = student.getProjects().stream()
                    .filter(p -> p.getName().equals(projectName))
                    .findFirst()
                    .orElse(null);

            if (project != null) {
                project.setProjectStage(projectStage);
                educationData.updateProject(project,student);
                System.out.println("Updated tasks for project " + projectName + " for student " + walletId);
            }
        }
    }

    public void updateTokenBalance(String walletId, int amount) {
        // Öğrenci detaylarını al (bu fonksiyonu implement etmeniz gerekebilir)
        Student student = getStudentDetails(walletId);

        if (student != null) {
            // Token bakiyesini güncelle
            student.setTokenBalance(student.getTokenBalance() + amount);
            System.out.println("Updated token balance for student " + walletId + " by " + amount);

            // Solana transfer işlemini yap
        } else {
            System.out.println("Student with ID " + walletId + " not found.");
        }
        try {
            // Transfer isteği için URL
            String url = "http://localhost:3001/transfer";

            // HTTP Başlıklarını ayarla
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            // POST isteği için JSON formatında body oluştur
            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("toPublicKeyString",walletId );
            requestBody.put("amount", amount);

            // İstek oluştur
            HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

            // POST isteğini yap ve sonucu al
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);

            // Yanıtı işleyin
            if (response.getStatusCode().is2xxSuccessful()) {
                System.out.println("Transfer success: " + response.getBody());
            } else {
                System.out.println("Transfer failed: " + response.getStatusCode());
            }
        } catch (Exception e) {
            System.out.println("Error while transferring tokens: " + e.getMessage());
        }
    }



    public void issueNFTCertificate(String walletId, String projectName, String certificateId) {
        Student student = getStudentDetails(walletId);
        if (student != null) {
            NFTCertificate nftCertificate = new NFTCertificate(certificateId, projectName, null);
            student.getCertificates().add(nftCertificate);
            System.out.println("Issued NFT certificate " + certificateId + " for project " + projectName + " to student " + walletId);
        }
    }

    public List<Project> getStudentProjects(String walletId) {
        Student student = getStudentDetails(walletId);
        return student != null ? student.getProjects() : null;
    }

    public Project suggestDynamicProject(Student student, String projectFocus, String projectStage) {
        String projectTitle = "Özel Proje: " + projectFocus;
        String projectDescription = student.getFirstName() + " için özel olarak oluşturulan bu proje, " +
                projectFocus + " alanında bilgi ve beceri kazandırmayı hedefler.";

        return new Project(projectTitle, projectDescription, projectStage);
    }

    public void enrollStudentInProject(String walletId, Project project) {
        Student student = getStudentDetails(walletId);
        if (student != null) {
            student.getProjects().add(project);
            System.out.println("Enrolled student " + walletId + " in project " + project.getName());
        }
    }

    public Project addProject(String projectTitle, String projectDescription, String projectStage) {

        return educationData.addProject(projectTitle,projectDescription, projectStage);

    }
}
