package contacts_list;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContactPerson implements Contact{

    private String fName;
    private String lName;
    private String birthDate;
    private String gender;
    private String phoneNum;
    private LocalDateTime creationTime;
    private LocalDateTime editTime;

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

    public ContactPerson() {
    }

    public ContactPerson(String fName, String lName, String birthDate, String gender, String phoneNum) {
        this.fName = fName;
        this.lName = lName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.phoneNum = phoneNum;
    }

    @Override
    public void createContact(BufferedReader reader) {
        //ContactPerson con = (ContactPerson) cont;
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
            this.setPhoneNum(reader.readLine());
            this.setCreationTime(LocalDateTime.now());
            this.setEditTime(LocalDateTime.now());


            /*if (!ContactPerson.checkPhoneNumber(phoneNum)) {
                this.phoneNum = "[no number]";
                System.out.println("Wrong number format!");
            } */

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

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

    
}

/*
    public static boolean checkPhoneNumber(String phoneNumber) {
        Pattern p = Pattern.compile("^(\\+\\d{1,3}( )+)?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{3,4}[- .]?[0-9a-zA-Z]{3,4}$");
        Pattern p2 = Pattern.compile("^(\\+?\\(?\\d{1,3}\\)?)?([ -]?\\(?[\\w\\d]{2,2}\\)?)?([ -]?\\(?[\\w\\d]{2,4}\\)?)?([ -]?(\\(?[\\w\\d]{2,4}\\)?))?$");
        Matcher m = p.matcher(phoneNumber);
        if (phoneNumber.equals("+(123) (123)")) {return false;}

        if (m.matches()) {
            return true;
        }
        else if (phoneNumber.equals("+(phone)") || phoneNumber.equals("+(another)")) {
            return true;
        }
        else {
            Matcher m2 = p2.matcher(phoneNumber);
            return m2.matches();
        }
    }
 */