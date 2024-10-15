package org.vaadin.marcus.data;

import lombok.Data;

import java.util.List;
@Data
public class MentorDetails {
    private String email;
    private String name;
    private List<String> expertise;
    private List<String> mentees;

    // Constructor
    public MentorDetails(String email, String name, List<String> expertise, List<String> mentees) {
        this.email = email;
        this.name = name;
        this.expertise = expertise;
        this.mentees = mentees;
    }
}