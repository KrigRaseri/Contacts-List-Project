package contacts_list;

public interface Util {
    static String checkBirthday(String inp) {
        if (inp.equalsIgnoreCase("")) {
            System.out.println("Bad birth date!");
            return "[no data]";
        }
        return inp;
    }

    static String checkGender(String inp) {
        if (inp.equalsIgnoreCase("M") || inp.equalsIgnoreCase("F")) {
            return inp;
        }

        System.out.println("Bad gender!");
        return "[no data]";
    }
}
