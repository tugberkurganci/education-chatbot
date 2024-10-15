package org.vaadin.marcus.data;

import java.util.List;

public class StudentDetails {

    private String email;

    private String name;

    private List<String> projects;

    private int tokenBalance;

    private List<String> certificates;

    // Constructor
    public StudentDetails(String email, String name, List<String> projects, int tokenBalance, List<String> certificates) {
        this.email = email;
        this.name = name;
        this.projects = projects;
        this.tokenBalance = tokenBalance;
        this.certificates = certificates;
    }

    // Getters and setters
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<String> getProjects() { return projects; }
    public void setProjects(List<String> projects) { this.projects = projects; }
    public int getTokenBalance() { return tokenBalance; }
    public void setTokenBalance(int tokenBalance) { this.tokenBalance = tokenBalance; }
    public List<String> getCertificates() { return certificates; }
    public void setCertificates(List<String> certificates) { this.certificates = certificates; }
}


