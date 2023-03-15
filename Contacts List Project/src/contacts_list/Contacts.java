package contacts_list;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    @Override
    public String toString() {
        return getfName() + ", " + getlName() + ", " + getPhoneNum();
    }

    public static boolean checkPhoneNumber(String phoneNumber) {
        Pattern p = Pattern.compile("^(\\+\\d{1,3}( )+)?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{3,4}[- .]?[0-9a-zA-Z]{3,4}$");
        Matcher m = p.matcher(phoneNumber);

        return m.matches();
    }
}
