package contacts_list;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

public interface Menu {

    static void menu() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            ContactList cList = new ContactList(new ArrayList<>());
            ContactFactory cf = new ContactFactory();
            while (true) {
                System.out.print("[menu] Enter action (add, list, search, count, exit): ");
                String input = reader.readLine();

                switch(input) {
                    case "add":
                        System.out.print("Enter the type (person, organization): ");
                        Contact contact = cf.getContact(Util.inputTypeCheck(reader));
                        contact.createContact(reader);
                        cList.addContact(cList, contact);
                        System.out.println("The record added.\n");
                        continue;

                    case "count":
                        System.out.printf("The Phone Book has %d records.\n", cList.getContactsListSize());
                        System.out.println();
                        continue;

                    case "search":
                        menuSearch(reader, cList);
                        continue;

                    case "list":
                        if (cList.getContactsListSize() == 0) {
                            System.out.println("No records to show!");
                            continue;
                        }

                        cList.printList(cList.getContactsList());
                        menuEditListEntry(reader, cList);
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

    /**
     * Search's through the list to find a contact, then gives options for the user. Calls menuEditEntry to edit a
     * contact via the search option.
     *
     * @param cList ContactList object
     */
    private static void menuSearch(BufferedReader reader, ContactList cList) {
        try {
            while (true) {
                ArrayList<Contact> listCopy = new ArrayList<>();
                System.out.print("Enter search query: ");
                String searchTerm = reader.readLine();

                for (int i = 0; i < cList.getContactsList().size(); i++) {
                    if (cList.getContactsList().get(i).getName().toLowerCase().contains(searchTerm)) {
                        listCopy.add(cList.getContactsList().get(i));
                    }
                }

                System.out.printf("Found %d results:\n", listCopy.size());
                cList.printList(listCopy);
                System.out.println();

                System.out.print("[search] Enter action ([number], back, again): ");
                String inp = reader.readLine().toLowerCase(Locale.ROOT);

                if (inp.matches("[\\d]+")) {
                    listCopy.get(Integer.parseInt(inp) - 1).printEntry();
                    Contact contactToSearch = listCopy.get(Integer.parseInt(inp) - 1);
                    menuEditEntry(reader, cList, cList.getContactsList().indexOf(contactToSearch));

                } else if (inp.equals("again")) {
                    continue;

                } else if (inp.equals("back")) {
                    System.out.println();
                    break;

                } else {
                    System.out.println("Try again.");
                    continue;
                }
                break;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Used to edit contact objects, called from the search option, or the list option.
     *
     * @param entryNum The number of the contact entry to be edited.
     */
    private static void menuEditEntry(BufferedReader reader, ContactList cList, int entryNum) {
        try {
            while (true) {
                System.out.print("[record] Enter action (edit, delete, menu): ");
                String input = reader.readLine().toLowerCase(Locale.ROOT);

                switch (input) {
                    case ("edit"):
                        if (cList.getContactsList().get(entryNum) != null) {
                            cList.editEntry(reader, cList, entryNum);

                        }  else {
                            System.out.println("Incorrect entry");
                        }
                        continue;

                    case "delete":
                        if (cList.getContactsListSize() == 0) {
                            System.out.println("No records to delete!");
                            continue;
                        }
                        cList.removeEntry(cList, entryNum);
                        System.out.println();
                        break;

                    case "menu":
                        System.out.println();
                        break;

                    default:
                        System.out.println("Try again");
                        continue;
                }
                break;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Calls menuEditEntry to edit a contact via the list option.
    private static void menuEditListEntry(BufferedReader reader, ContactList cList) {
        try {
            while (true) {
                System.out.println();
                System.out.print("[list] Enter action ([number], back): ");
                String input = reader.readLine().toLowerCase(Locale.ROOT);

                if (input.matches("[\\d]+")) {
                    cList.getContactsList().get(Integer.parseInt(input) - 1).printEntry();
                    menuEditEntry(reader, cList, Integer.parseInt(input) - 1);

                } else if (input.equals("back")) {
                    break;
                } else {
                    System.out.println("Invalid input");
                    continue;
                }
                break;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
