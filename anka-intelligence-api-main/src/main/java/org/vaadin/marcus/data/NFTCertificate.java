package org.vaadin.marcus.data;

import lombok.Data;

@Data
public class NFTCertificate {
    private String certificateId;
    private String courseName;
    private String issueDate;

    public NFTCertificate(String certificateId, String courseName, String issueDate) {
        this.certificateId = certificateId;
        this.courseName = courseName;
        this.issueDate = issueDate;
    }

}