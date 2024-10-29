package org.example.springhealthcaremanagement.entities.contactlist;

import java.util.List;

class ContactListImport {
    private String fileUrl;
    private List<String> fieldOrder;

    // getters and setters

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public List<String> getFieldOrder() {
        return fieldOrder;
    }

    public void setFieldOrder(List<String> fieldOrder) {
        this.fieldOrder = fieldOrder;
    }
}