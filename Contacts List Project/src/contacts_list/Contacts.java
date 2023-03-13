package contacts_list;

public class Contacts {

    private String fName;
    private String lName;
    private String phoneNum;

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

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Contacts() {
    }

    public Contacts(String fName, String lName, String phoneNum) {
        this.fName = fName;
        this.lName = lName;
        this.phoneNum = phoneNum;
    }
}
