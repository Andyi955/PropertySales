package application;

import java.time.LocalDate;

/**
 * Agent Class
 * Has agent constructor,getters and setters
 *
 * @name Andrew Ivory
 * @Version V2.0
 * @StudentNumber 20068082
 *
 *
 *
 */

public class Agent{
    private int agentId;
    private String firstname;
    private String surname;
    private String email;
    private String password;
    private LocalDate dob;
    private String phoneNo;
    private String address;
    private String companyName;

    public Agent(int agentId, String firstname, String surname, String email, String password, String phoneNo, String companyName) {
        this.firstname = firstname;
        this.agentId = agentId;

        this.surname = surname;
        this.email = email;
        this.password = password;
        this.phoneNo = phoneNo;
    //    this.address = address;
        this.companyName = companyName;
    }

    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        agentId = agentId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String toString() {
        return "Agent " +
                " AgentId: " + agentId + '\n'+
                " firstname: " + firstname + '\n' +
                " surname: " + surname + '\n' +
                "  email: " + email + '\n' +
                "  password: " + password + '\n' +
                "  phoneNo: " + phoneNo + '\n' +
                "  companyName: " + companyName;
    }
}
