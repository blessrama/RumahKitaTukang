package blessrama.pkm.rumahkitatukang.model;

import java.util.List;

public class User {
    private String id;
    private String name;
    private String emailAddress;
    private int contractInProgress, contractCompleted;
    private String phoneNumber;
    private String homeAddress;
    private String description;
    private boolean workingStatus;
    private List<Job> jobList;
    private String role;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public int getContractInProgress() {
        return contractInProgress;
    }

    public void setContractInProgress(int contractInProgress) {
        this.contractInProgress = contractInProgress;
    }

    public int getContractCompleted() {
        return contractCompleted;
    }

    public void setContractCompleted(int contractCompleted) {
        this.contractCompleted = contractCompleted;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getWorkingStatus() {
        return workingStatus;
    }

    public void setWorkingStatus(boolean workingStatus) {
        this.workingStatus = workingStatus;
    }

    public List<Job> getJobList() {
        return jobList;
    }

    public void setJobList(List<Job> jobList) {
        this.jobList = jobList;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
