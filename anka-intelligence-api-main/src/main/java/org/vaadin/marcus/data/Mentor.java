package org.vaadin.marcus.data;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Mentor {
    private String firstName;
    private String lastName;
    private String email;
    private List<Student> mentees;
    private List<String> areasOfExpertise; // Örneğin, girişimcilik, finans, teknoloji

    public Mentor(String firstName, String lastName, String email, List<String> areasOfExpertise) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mentees = new ArrayList<>();
        this.areasOfExpertise = areasOfExpertise;
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

    public List<Student> getMentees() {
        return mentees;
    }

    public void setMentees(List<Student> mentees) {
        this.mentees = mentees;
    }

    public List<String> getAreasOfExpertise() {
        return areasOfExpertise;
    }

    public void setAreasOfExpertise(List<String> areasOfExpertise) {
        this.areasOfExpertise = areasOfExpertise;
    }
}
