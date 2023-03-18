package contacts_list;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;

public class ContactOrganization implements Contact, Serializable {

    //Fields
    private String orgName;
    private String orgAddress;
    private String orgPhoneNum;
    private LocalDateTime creationTime;
    private LocalDateTime editTime;

    //Default constructor
    public ContactOrganization() {}

    //Getters and setters
    @Override
    public String getName() {
        return orgName;
    }

    public LocalDateTime getCreationTime() { return creationTime; }

    public void setCreationTime(LocalDateTime creationTime) { this.creationTime = creationTime; }

    public LocalDateTime getEditTime() { return editTime; }

    public void setEditTime(LocalDateTime editTime) { this.editTime = editTime;}

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgAddress() {
        return orgAddress;
    }

    public void setOrgAddress(String orgAddress) {
        this.orgAddress = orgAddress;
    }

    public String getOrgPhoneNum() {
        return orgPhoneNum;
    }

    public void setOrgPhoneNum(String orgPhoneNum) {
        this.orgPhoneNum = orgPhoneNum;
    }

    //Creates organization contact with all relevant fields.
    @Override
    public void createContact(BufferedReader reader) {
        try {
            System.out.print("Enter the organization name: ");
            this.setOrgName(reader.readLine());
            System.out.print("Enter the address: ");
            this.setOrgAddress(reader.readLine());
            System.out.print("Enter the number: ");
            this.setOrgPhoneNum(Util.checkPhoneNumber(reader.readLine()));
            this.setCreationTime(LocalDateTime.now());
            this.setEditTime(LocalDateTime.now());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Prints all the ContactOrganization object fields.
    @Override
    public void printEntry() {
        System.out.printf("Organization name: %s\n" +
                "Address: %s\n" +
                "Number: %s\n",
                getName(), getOrgAddress(), getOrgPhoneNum());

        System.out.println("Time created: " + getCreationTime() + "\nTime last edit: " + getEditTime());
    }

    //Grabs all the ContactOrganization object fields and returns them as one entire string for the purpose of searching.
    @Override
    public String makeSearchable() {
        return getName() + getOrgPhoneNum() + getOrgAddress();
    }
}

