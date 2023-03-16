package contacts_list;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ContactList {

    private ArrayList<Contact> contactsList;

    public ArrayList<Contact> getContactsList() {
        return new ArrayList<>(contactsList);
    }

    public ContactList(ArrayList<Contact> contactsList) {
        this.contactsList = contactsList;
    }

    public int getContactsListSize() {
        return contactsList.size();
    }

    public void printList(ContactList cl) {
        for (int i = 0; i < cl.getContactsListSize(); i++) {
            System.out.println(i+1 + ". " + cl.getContactsList().get(i).getName());
        }
    }

    public void printInfo(BufferedReader reader, ContactList cList) {
        try {
            printList(cList);
            System.out.print("Enter index to show info:  ");
            cList.getContactsList().get(Integer.parseInt(reader.readLine()) - 1).printEntry();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void addContact(ContactList cl, Contact con) {
        cl.contactsList.add(con);
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

            if (cList.getContactsList().get(record) instanceof ContactPerson) {
                editEntryPerson(reader, cList, record);
                System.out.println("The record updated!");

            } else {
                editEntryOrganization(reader, cList, record);
                System.out.println("The record updated!");
            }

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void editEntryPerson(BufferedReader reader, ContactList cList, int listIndex) {
        try {
            System.out.println("Select a field (name, surname, birth, gender, number): ");
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
                    p.setPhoneNum(reader.readLine());
                    break;

                default:
                    System.out.println("Invalid");
                    break;
            }

            p.setEditTime(LocalDateTime.now());
        } catch (IOException e) {
            System.out.println(e);
        }
    }


    public void editEntryOrganization(BufferedReader reader, ContactList cList, int listIndex) {
        try {
            System.out.println("Select a field (address, number): ");
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
            System.out.println(e);
        }
    }
}
