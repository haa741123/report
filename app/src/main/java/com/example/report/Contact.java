package com.example.report;

public class Contact {
    private int id;
    private String name;
    private String phoneNumber;
    private String email;
    private String workplaceName;
    private String jobTitle;

    // 생성자
    public Contact() {
    }

    public Contact(int id, String name, String phoneNumber, String email, String workplaceName, String jobTitle) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.workplaceName = workplaceName;
        this.jobTitle = jobTitle;
    }

    // Getter 및 Setter 메서드
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWorkplaceName() {
        return workplaceName;
    }

    public void setWorkplaceName(String workplaceName) {
        this.workplaceName = workplaceName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @Override
    public String toString() {
        return name; // 연락처의 이름을 반환하도록 수정
    }
}