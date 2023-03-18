package contacts_list;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Locale;

public class Menu implements Runnable {

    @Override
    public void run() {
        menu();
    }

    public void menu() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            ContactList cList = new ContactList(ContactSerialize.contactDeserialize());
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
                        ContactSerialize.contactSerialize(cList.getContactsList());
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
            throw new RuntimeException(e);
        }
    }

    /**
     * Search's through the list to find a contact, then gives options for the user. Calls menuEditEntry to edit a
     * contact via the search option. First block Section fills lists shows the number of results. Second block checks
     * for an int to parse and then prints the entry at that index, then calls menuEditEntry with that same index.
     */
    private static void menuSearch(BufferedReader reader, ContactList cList) {
        try {
            while (true) {
                ArrayList<String> searchable = new ArrayList<>(Util.fillSearchable(cList));
                ArrayList<Contact> foundList = new ArrayList<>(Util.fillFoundList(reader, cList, searchable));
                System.out.printf("Found %d results:\n", foundList.size());
                cList.printList(foundList);
                System.out.println();

                System.out.print("[search] Enter action ([number], back, again): ");
                String inp = reader.readLine().toLowerCase(Locale.ROOT);
                if (inp.matches("\\d+")) {
                    foundList.get(Integer.parseInt(inp) - 1).printEntry();
                    menuEditEntry(reader, cList, cList.getContactsList()
                            .indexOf(foundList.get(Integer.parseInt(inp) - 1)));

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

                        if (Util.deleteConfirmation(reader)) {
                            cList.removeEntry(cList, entryNum);
                        }
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

                if (input.matches("\\d+")) {
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
