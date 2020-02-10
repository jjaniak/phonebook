package com.phonebook.tests;

import com.phonebook.spring.ApplicationConfig;
import com.phonebook.spring.PhoneBook;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes = {ApplicationConfig.class})
//@ContextConfiguration("/application-config.xml") // uncomment this if you want to switch to use use xml context configuration

public class PhoneBookTest {

    @Autowired
    private PhoneBook phoneBook;

    @Test
    public void shouldGetPersonPhoneNumber() {
        final Set<String> expected = new HashSet<>(asList("+79601232233"));

        assertEquals(expected,
                phoneBook.findAll().get("Alex"),
                "phone numbers do not match");
    }

    @Test
    public void shouldGetPersonAllPhoneNumbers() {
        final Set<String> expected = new HashSet<>(asList("+79213215566", "+79213215567", "+79213215568"));

        assertEquals(expected,
                phoneBook.findAll().get("Billy"),
                "phone numbers do not match");
    }

    @Test
    public void shouldShowAllPhoneNumbersInRepo() {
        Map<String, Set<String>> expectedMap = new LinkedHashMap<>();
        expectedMap.put("Alex", new HashSet<>(asList("+79601232233")));
        expectedMap.put("Billy", new HashSet<>(asList("+79213215566", "+79213215567", "+79213215568")));

        assertEquals(expectedMap,
                phoneBook.findAll(),
                "not showing correctly all phone numbers in phone book");
    }

    @Test
    public void shouldAddPhoneNumberForNewPerson() {
        phoneBook.addPhone("Max", "+79123456789");

        assertTrue(phoneBook.findAll().containsKey("Max"));
        assertEquals(new HashSet<>(asList("+79123456789")),
                phoneBook.findAll().get("Max"),
                "problem with adding phone number for new contact");
    }

    @Test
    public void shouldAddPhoneNumberForExistingPerson() {
        phoneBook.addPhone("Alex", "+79987654321");

        assertEquals(new HashSet<>(asList("+79987654321", "+79601232233")),
                phoneBook.findAll().get("Alex"),
                "problem with adding phone number for existing contact");
    }

    @Test
    public void shouldAddMultiplePhoneNumbers() {
        phoneBook.addPhone("Alex", "+71111111113");
        phoneBook.addPhone("Alex", "+72468094716");

        assertEquals(new HashSet<>(asList("+71111111113", "+72468094716", "+79601232233")),
                phoneBook.findAll().get("Alex"),
                "problem with adding multiple phone numbers for existing contact");
    }

    @Test
    public void shouldNotAddTwiceSameNumber() {
        phoneBook.addPhone("Mary", "+72222222222");
        phoneBook.addPhone("Mary", "+72222222222");

        assertEquals(new HashSet<>(asList("+72222222222")),
                phoneBook.findAll().get("Mary"),
                "problem with handling addition of the same phone number");
    }

    @Test
    public void shouldRemovePhoneNumber() {
        phoneBook.removePhone("+79213215567");
        final Set<String> expected = new HashSet<>(asList("+79213215566", "+79213215568"));

        assertEquals(expected,
                phoneBook.findAll().get("Billy"),
                "phone number was not removed");
    }

    @Test
    public void shouldRemoveWholeEntryWhenValuesEmpty() {
        phoneBook.removePhone("+79601232233");

        assertNull(phoneBook.findAll().get("Alex"));
    }

    @Test
    public void shouldThrowExceptionWhenRemovingInvalidNumber() {
        Exception exception1 = assertThrows(IllegalArgumentException.class, ()-> phoneBook.removePhone("+79213215000"));

        assertEquals("The phone number +79213215000 is not in the phone book",
                exception1.getMessage());
    }

    @Test
    public void shouldNotRemoveNumberTwice() {
        phoneBook.removePhone("+79213215566");
        final Set<String> expected = new HashSet<>(asList("+79213215567", "+79213215568"));
        assertEquals(expected,
                phoneBook.findAll().get("Billy"));

        assertThrows(IllegalArgumentException.class, ()-> phoneBook.removePhone("+79213215566"));
    }
}