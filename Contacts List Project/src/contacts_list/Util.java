package contacts_list;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface Util {
    //Checks birthday format.
    static String checkBirthday(String inp) {
        if (inp.equalsIgnoreCase("")) {
            System.out.println("Bad birth date!");
            return "[no data]";
        }
        return inp;
    }

    //Checks gender input.
    static String checkGender(String inp) {
        if (inp.equals("") || inp.equals(" ")) {
            System.out.println("No input");
            return "[no data]";
        }

        return inp;
    }

    //Checks the input when choosing contact type.
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

    //Fills the searchable arraylist where each element is a string of all the contact objects fields.
    static ArrayList<String> fillSearchable (ContactList cList) {
        ArrayList<String> searchable = new ArrayList<>();
        for (int i = 0; i < cList.getContactsListSize(); i++) {
            searchable.add(cList.getContactsList().get(i).makeSearchable());
        }
        return searchable;
    }

    /**
     * Fills the foundList in the menu interface with all the found contacts that were searched.
     *
     * @param searchable An ArrayList where each element is a string of all the contact objects fields.
     * */
    static ArrayList<Contact> fillFoundList (BufferedReader reader, ContactList cList, ArrayList<String> searchable) {
        try {
            ArrayList<Contact> listCopy = new ArrayList<>();
            System.out.print("Enter search query: ");
            String searchTerm = reader.readLine();
            for (int i = 0; i < cList.getContactsList().size(); i++) {
                if (searchable.get(i).toLowerCase().contains(searchTerm)) {
                    listCopy.add(cList.getContactsList().get(i));
                }
            }
            return listCopy;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static String checkPhoneNumber(String phoneNumber) {
        Pattern p = Pattern.compile("^(\\+\\d{1,3}( )+)?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{3,4}[- .]?[0-9a-zA-Z]{3,4}$");
        Matcher m = p.matcher(phoneNumber);

        if (m.matches()) {
            return phoneNumber;
        } else {
            System.out.println("Invalid number");
            return "[No data]";
        }
    }

    static boolean deleteConfirmation(BufferedReader reader) {
        try {
            System.out.print("Are you sure you want to delete this entry? y/n: ");
            return reader.readLine().matches("^(?i)[yes]+");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
