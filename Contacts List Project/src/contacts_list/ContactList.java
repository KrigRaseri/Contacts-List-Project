package contacts_list;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ContactList {
    //Fields
    private ArrayList<Contact> contactsList;

    //Constructor
    public ContactList(ArrayList<Contact> contactsList) {
        this.contactsList = contactsList;
    }

    //Getter and sort of getter
    public ArrayList<Contact> getContactsList() {
        return new ArrayList<>(contactsList);
    }

    public int getContactsListSize() {
        return contactsList.size();
    }

    //================================== Methods for ContactList printing ==============================================
    public void printList(ArrayList<Contact> cl) {
        for (int i = 0; i < cl.size(); i++) {
            System.out.println(i+1 + ". " + cl.get(i).getName());
        }
    }

    public void printInfo(BufferedReader reader, ContactList cList) {
        try {
            printList(cList.getContactsList());
            System.out.print("Enter index to show info:  ");
            cList.getContactsList().get(Integer.parseInt(reader.readLine()) - 1).printEntry();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //=================================== Methods for ContactList editing ==============================================

    public void addContact(ContactList cl, Contact con) {
        cl.contactsList.add(con);
    }

    public void removeEntry(ContactList cList, int entryIndex) {
        cList.contactsList.remove(entryIndex);
        ContactSerialize.contactSerialize(cList.getContactsList());
        System.out.println("The record removed!");
    }

    public void editEntry(BufferedReader reader, ContactList cList, int entryIndex) {
        if (cList.getContactsList().get(entryIndex) instanceof ContactPerson) {
            editEntryPerson(reader, cList, entryIndex);
        } else {
            editEntryOrganization(reader, cList, entryIndex);
        }

        ContactSerialize.contactSerialize(cList.getContactsList());
        System.out.println("Saved");
        cList.getContactsList().get(entryIndex).printEntry();
    }

    //================================= Methods that are called by editEntry ===========================================

    /**
     * User chooses which field of the ContactPerson object to edit.
     *
     * @param listIndex is the index of the ContactList entry to be edited.
     * */
    private void editEntryPerson(BufferedReader reader, ContactList cList, int listIndex) {
        try {
            System.out.print("Select a field (name, surname, birth, gender, number): ");
            ContactPerson p = (ContactPerson) cList.getContactsList().get(listIndex);

            switch (reader.readLine().toLowerCase()) {
                case "name":
                    System.out.print("Enter name: ");
                    p.setfName(reader.readLine());
                    break;

                case "surname":
                    System.out.print("Enter surname: ");
                    p.setlName(reader.readLine());
                    break;

                case "birth":
                    System.out.print("Enter birthday: ");
                    p.setBirthDate(Util.checkBirthday(reader.readLine()));
                    break;

                case "gender":
                    System.out.print("Enter gender: ");
                    p.setGender(Util.checkGender(reader.readLine()));
                    break;

                case "number":
                    System.out.print("Enter number: ");
                    p.setPhoneNum(Util.checkPhoneNumber(reader.readLine()));
                    break;

                default:
                    System.out.println("Invalid");
                    break;
            }

            p.setEditTime(LocalDateTime.now());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * User chooses which field of the ContactOrganization object to edit.
     *
     * @param listIndex is the index of the ContactList entry to be edited.
     * */
    private void editEntryOrganization(BufferedReader reader, ContactList cList, int listIndex) {
        try {
            System.out.print("Select a field (address, number): ");
            ContactOrganization o = (ContactOrganization) cList.getContactsList().get(listIndex);

            switch (reader.readLine().toLowerCase()) {
                 case "name":
                    System.out.print("Enter name: ");
                    o.setOrgName(reader.readLine());
                    break;

                case "address":
                    System.out.print("Enter address: ");
                    o.setOrgAddress(reader.readLine());
                    break;

                case "number":
                    System.out.print("Enter number: ");
                    o.setOrgPhoneNum(reader.readLine());
                    break;

                default:
                    System.out.println("Invalid");
                    break;
            }
            o.setEditTime(LocalDateTime.now());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
