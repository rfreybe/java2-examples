package io.jonashackt.lectures.exercises;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddressBookTest {

    AddressBook addressBook;
    Person maxMustermann;
    Person emmaWeber;
    Person manfredHerold;

    @BeforeEach
    void prepareAddressBook() {
        addressBook = new AddressBook();
        maxMustermann = new Person("Max", "Mustermann", "max.mustermann@email.de");
        emmaWeber = new Person("Emma", "Weber", "emma.mueller@post.de");
        manfredHerold = new Person("Manfred", "Herold", "manfred@herold.de");

        addressBook.addContact(maxMustermann);
        addressBook.addContact(emmaWeber);
        addressBook.addContact(manfredHerold);
    }

    @Test
    void addressbook_should_store_persons_with_name_surename_email() throws PersonNotFoundException {

        assertEquals(maxMustermann, addressBook.getContactByLastName("Mustermann"));
        assertEquals(emmaWeber, addressBook.getContactByLastName("Weber"));
        assertEquals(manfredHerold, addressBook.getContactByLastName("Herold"));
    }

    @Test
    void should_throw_person_not_found_exception_with_unknown_person() {

        assertThrows(PersonNotFoundException.class, () -> {
            addressBook.getContactByLastName("Gates");
        });
    }

    @Test
    void persons_should_be_sorted_ascending_according_to_lastname() {

        assertEquals(3, addressBook.getSize(), "After adding 3 persons, the address book should contain 3 persons.");

        assertEquals(manfredHerold, addressBook.getPersonByIndex(0));

    }

    @Test
    void addressBook_should_have_overview_on_how_often_lastnames_occur_in_it() {
        assertEquals(1, addressBook.getFrequencyOfLastName("Mustermann"));

        addressBook.addContact(new Person("Moritz", "Mustermann", "foo@bar.com"));
        addressBook.addContact(new Person("Tobias", "Mustermann", "foo@bar.com"));
        addressBook.addContact(new Person("Matze", "Mustermann", "foo@bar.com"));

        assertEquals(4, addressBook.getFrequencyOfLastName("Mustermann"));
    }

    @Test
    void person_should_have_a_human_readable_toString_output() {
        Person felixMeyer = new Person("Felix", "Meyer", "felix@meyer.io");

        assertEquals("Felix Meyer (felix@meyer.io)", felixMeyer.toString());
    }
}
