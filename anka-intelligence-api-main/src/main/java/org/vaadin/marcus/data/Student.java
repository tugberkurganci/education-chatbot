package org.vaadin.marcus.data;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Student {
    private String walletId;
    private String firstName;
    private String lastName;
    private String email;
    private List<Project> projects;
    private int tokenBalance;
    private List<NFTCertificate> certificates;
    private String learningStyle; // Görsel, işitsel, uygulamalı vb.

    public Student(String firstName, String lastName, String email, String learningStyle, String walletId) {
        this.walletId = walletId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.projects = new ArrayList<>();
        this.tokenBalance = 2;
        this.certificates = new ArrayList<>();
        this.learningStyle = learningStyle;
    }

    public String getWalletId() {
        return walletId;
    }

    public void setWalletId(String walletId) {
        this.walletId = walletId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public int getTokenBalance() {
        return tokenBalance;
    }

    public void setTokenBalance(int tokenBalance) {
        this.tokenBalance = tokenBalance;
    }

    public List<NFTCertificate> getCertificates() {
        return certificates;
    }

    public void setCertificates(List<NFTCertificate> certificates) {
        this.certificates = certificates;
    }

    public String getLearningStyle() {
        return learningStyle;
    }

    public void setLearningStyle(String learningStyle) {
        this.learningStyle = learningStyle;
    }
}






