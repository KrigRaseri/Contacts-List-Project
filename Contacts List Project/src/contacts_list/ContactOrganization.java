package contacts_list;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;

public class ContactOrganization implements Contact {
    private String orgName;
    private String orgAddress;
    private String orgPhoneNum;
    private LocalDateTime creationTime;
    private LocalDateTime editTime;

    @Override
    public String getName() {
        return orgName;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public LocalDateTime getEditTime() {
        return editTime;
    }

    public void setEditTime(LocalDateTime editTime) {
        this.editTime = editTime;
    }

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

    public ContactOrganization() {
    }

    public ContactOrganization(String orgName, String orgAddress, String orgPhoneNum) {
        this.orgName = orgName;
        this.orgAddress = orgAddress;
        this.orgPhoneNum = orgPhoneNum;
    }

    @Override
    public void createContact(BufferedReader reader) {
        try {
            System.out.print("Enter the organization name: ");
            this.setOrgName(reader.readLine());
            System.out.print("Enter the address: ");
            this.setOrgAddress(reader.readLine());
            System.out.print("Enter the number: ");
            this.setOrgPhoneNum(reader.readLine());
            this.setCreationTime(LocalDateTime.now());
            this.setEditTime(LocalDateTime.now());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void printEntry() {
        System.out.printf("Organization name: %s\n" +
                "Address: %s\n" +
                "Number: %s\n",
                getName(), getOrgAddress(), getOrgPhoneNum());

        System.out.println("Time created: " + getCreationTime() + "\nTime last edit: " + getEditTime());
    }
}

