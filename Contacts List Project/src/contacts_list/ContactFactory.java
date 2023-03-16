package contacts_list;

public class ContactFactory {

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
