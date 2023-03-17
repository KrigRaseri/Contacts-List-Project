package contacts_list;

import java.io.BufferedReader;
import java.io.IOException;

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

    static String inputTypeCheck(BufferedReader reader) {
         try {
             String input = reader.readLine();
             while (!input.equals("person") && !input.equals("organization")) {
                 System.out.println("Try again!");
                 System.out.print("Enter the type (person, organization): ");
                 input = reader.readLine();
             }
             return input;
         } catch (IOException e) {
             throw new RuntimeException(e);
         }
    }
}
