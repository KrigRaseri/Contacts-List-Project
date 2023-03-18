package contacts_list;

import java.io.*;
import java.util.ArrayList;

/**
 * Allows for de/serialization of contact database.
 * */
public class ContactSerialize implements Serializable {

    //Serializes contact object.
    static void contactSerialize(ArrayList<Contact> contactList) {
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("src/Contact.db"));
             ObjectOutputStream oos = new ObjectOutputStream(bos)) {

            oos.writeObject(contactList);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Deserialize contact object.
    static ArrayList<Contact> contactDeserialize() {
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream("src/Contact.db"))) {

            if (bis.available() == 0) {
                return new ArrayList<Contact>();
            }

            try (ObjectInputStream ois = new ObjectInputStream(bis)) {
                return new ArrayList<Contact>((ArrayList<Contact>) ois.readObject());

            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
