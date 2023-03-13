package contacts_list;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public interface Util {
    static void menu() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            ContactList cList = new ContactList(new ArrayList<Contacts>());

            cList.createContact(reader, cList);
            System.out.println("A Phone Book with a single record created!");
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
