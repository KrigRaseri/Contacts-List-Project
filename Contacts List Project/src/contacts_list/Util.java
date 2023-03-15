package contacts_list;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public interface Util {
    static void menu() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            ContactList cList = new ContactList(new ArrayList<>());
            while (true) {
                System.out.print("Enter action (add, remove, edit, count, list, exit): ");
                String input = reader.readLine();
                switch(input) {
                    case "add":
                        cList.createContact(reader, cList);
                        System.out.println("The record added.");
                        continue;

                    case "remove":
                        if (cList.getContactsListSize() == 0) {
                            System.out.println("No records to remove!");
                            continue;
                        }
                        cList.removeEntry(reader, cList);
                        continue;

                    case "edit":
                        if (cList.getContactsListSize() == 0) {
                            System.out.println("No records to edit!");
                            continue;
                        }
                        cList.editEntry(reader, cList);
                        continue;

                    case "count":
                        System.out.printf("The Phone Book has %d records.\n", cList.getContactsListSize());
                        continue;

                    case "list":
                        cList.printList(cList);
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
