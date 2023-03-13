package contacts_list;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ContactList {
    private List<Contacts> contactsList;

    public ContactList(List<Contacts> contactsList) {
        this.contactsList = contactsList;
    }

    public void createContact(BufferedReader reader, ContactList cl) {
        try {
            System.out.println("Enter the name of the person:");
            String fName = reader.readLine();
            System.out.println("Enter the surname of the person:");
            String lName = reader.readLine();
            System.out.println("Enter the number:");
            String phoneNum = reader.readLine();
            cl.contactsList.add(new Contacts(fName, lName, phoneNum));
            System.out.println("A record created!");

        }

        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int getContactsListSize() {
        return contactsList.size();
    }
}
