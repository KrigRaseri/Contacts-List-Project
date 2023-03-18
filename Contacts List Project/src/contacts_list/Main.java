package contacts_list;

/**
 * Contact list program that can make either a personal contact, or an organization contact. Allows for adding, editing,
 * searching, removing, etc. Then saves all contacts to the contact.db file.
 *
 * @author Krig Raser (pen name), https://github.com/KrigRaseri
 * */
public class Main {
    public static void main(String[] args) {
        Thread t = new Thread(new Menu());
        t.start();
        synchronized (t) {
            try {
                t.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

/*
TODO

Edit search menu remove option to have a confirmation before deleting.
Implement bday format check.
 */