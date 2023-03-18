package contacts_list;

import java.io.BufferedReader;

public interface Contact {
   void printEntry();
   void createContact(BufferedReader reader);
   String getName();
   String makeSearchable();
}
