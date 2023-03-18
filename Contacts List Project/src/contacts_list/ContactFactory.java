package contacts_list;

public class ContactFactory {

    /**
     * Contact object factory creates either a ContactPerson object, or a ContactOrganization object.
     * */
    public Contact getContact(String contactType) {
        if (contactType == null) {
            return null;
        }

        if (contactType.equalsIgnoreCase("Person")) {
            return new ContactPerson();
        } else if (contactType.equalsIgnoreCase("Organization")) {
            return new ContactOrganization();
        }

        return null;
    }
}
