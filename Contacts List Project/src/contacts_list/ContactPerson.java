package contacts_list;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;

public class ContactPerson implements Contact, Serializable {

    //Fields
    private String fName;
    private String lName;
    private String birthDate;
    private String gender;
    private String phoneNum;
    private LocalDateTime creationTime;
    private LocalDateTime editTime;

    //Default constructor
    public ContactPerson() {}

    //Getters and setters.
    @Override
    public String getName() {
        return getfName() + " " + getlName();
    }

    public LocalDateTime getCreationTime() {return creationTime;}

    public void setCreationTime(LocalDateTime creationTime) {this.creationTime = creationTime;}

    public LocalDateTime getEditTime() {return editTime;}

    public void setEditTime(LocalDateTime editTime) {this.editTime = editTime;}

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    //Creates person contact with all relevant fields.
    @Override
    public void createContact(BufferedReader reader) {
        try {
            System.out.print("Enter the name of the person: ");
            this.setfName(reader.readLine());
            System.out.print("Enter the surname of the person:");
            this.setlName(reader.readLine());
            System.out.print("Enter the birth date: ");
            this.setBirthDate(Util.checkBirthday(reader.readLine()));
            System.out.print("Enter the gender (M, F): ");
            this.setGender(Util.checkGender(reader.readLine()));
            System.out.print("Enter the number: ");
            this.setPhoneNum(Util.checkPhoneNumber(reader.readLine()));
            this.setCreationTime(LocalDateTime.now());
            this.setEditTime(LocalDateTime.now());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Prints all the ContactPerson object fields.
    @Override
    public void printEntry() {
        System.out.printf("Name: %s\n" +
                "Surname: %s\n" +
                "Birth date: %s\n" +
                "Gender: %s\n" +
                "Number: %s\n",
                getfName(), getlName(), getBirthDate(), getGender(), getPhoneNum());

        System.out.println("Time created: " + getCreationTime() + "\nTime last edit: " + getEditTime());
        System.out.println();
    }

    //Grabs all the ContactPerson object fields and returns them as one entire string for the purpose of searching.
    @Override
    public String makeSearchable() {
        return getName() + getBirthDate() + getGender() + getPhoneNum();
    }
}
