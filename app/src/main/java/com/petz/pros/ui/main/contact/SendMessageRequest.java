package com.petz.pros.ui.main.contact;

import com.google.gson.annotations.SerializedName;

public class SendMessageRequest {

    @SerializedName("Name")
    private String name;
    @SerializedName("Email")
    private String email;
    @SerializedName("Phone")
    private String phone;
    @SerializedName("Subject")
    private String subject;
    @SerializedName("Message")
    private String message;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
