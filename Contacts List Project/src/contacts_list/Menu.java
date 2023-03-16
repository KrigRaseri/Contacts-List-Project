package contacts_list;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public interface Menu {

    static void menu() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            ContactList cList = new ContactList(new ArrayList<>());
            ContactFactory cf = new ContactFactory();
            while (true) {
                System.out.print("Enter action (add, remove, edit, count, info, exit): ");
                String input = reader.readLine();
                switch(input) {
                    case "add":
                        System.out.print("Enter the type (person, organization): ");
                        Contact contact = cf.getContact(reader.readLine());
                        contact.createContact(reader);
                        cList.addContact(cList, contact);
                        System.out.println("The record added.\n");
                        continue;

                    case "remove":
                        if (cList.getContactsListSize() == 0) {
                            System.out.println("No records to remove!\n");
                            continue;
                        }
                        cList.removeEntry(reader, cList);
                        continue;

                    case "edit":
                        if (cList.getContactsListSize() == 0) {
                            System.out.println("No records to edit!\n");
                            continue;
                        }
                        cList.editEntry(reader, cList);
                        System.out.println();
                        continue;

                    case "count":
                        System.out.printf("The Phone Book has %d records.\n", cList.getContactsListSize());
                        continue;

                    case "info":
                        if (cList.getContactsListSize() == 0) {
                            System.out.println("No records to show!\n");
                            continue;
                        }

                        cList.printInfo(reader, cList);
                        System.out.println();
                        continue;

                    case "exit":
                        break;

                    default:
                        continue;
                }
                break;
            }

        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
