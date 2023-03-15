package contacts_list;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ContactList {

    private ArrayList<Contacts> contactsList;

    public ArrayList<Contacts> getContactsList() {
        return new ArrayList<>(contactsList);
    }

    public ContactList(ArrayList<Contacts> contactsList) {
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

            if (!Contacts.checkPhoneNumber(phoneNum)) {
                phoneNum = "[no number]";
                System.out.println("Wrong number format!");
            }

            cl.contactsList.add(new Contacts(fName, lName, phoneNum));
        }

        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int getContactsListSize() {
        return contactsList.size();
    }

    public void printList(ContactList cl) {
        for (int i = 0; i < cl.getContactsListSize(); i++) {
            System.out.println(i+1 + ". " + cl.getContactsList().get(i).toString());
        }
    }

    public void removeEntry(BufferedReader reader, ContactList cList) {
        try {
            printList(cList);
            System.out.print("Select a record: ");
            int input = Integer.parseInt(reader.readLine()) - 1;
            cList.contactsList.remove(input);
            System.out.println("The record removed!");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void editEntry(BufferedReader reader, ContactList cList) {
        try {
            cList.printList(cList);
            System.out.print("Select a record: ");
            int record = Integer.parseInt(reader.readLine()) - 1;
            System.out.println();
            System.out.println("Select a field (name, surname, number): ");

            switch (reader.readLine()) {
                case "name":
                    System.out.print("Enter name: ");
                    cList.contactsList.get(record).setfName(reader.readLine());
                    break;

                case "surname":
                    System.out.print("Enter surname: ");
                    cList.contactsList.get(record).setlName(reader.readLine());
                    break;

                case "number":
                    System.out.print("Enter number: ");
                    String inp = reader.readLine();

                    if (!Contacts.checkPhoneNumber(inp)) {
                        inp= "[no number]";
                        System.out.println("Wrong number format!");
                    }

                    cList.contactsList.get(record).setPhoneNum(inp);
                    break;

                default:
                    System.out.println("Invalid");
                    break;
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
