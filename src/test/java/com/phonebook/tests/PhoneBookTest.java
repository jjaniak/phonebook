package com.phonebook.tests;

import com.phonebook.spring.ApplicationConfig;
import com.phonebook.spring.PhoneBook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.Set;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
public class PhoneBookTest {

    @Autowired
    private PhoneBook phoneBook;

    // TODO: implement a couple of tests for PhoneBook.class
    @Test
    public void getPersonPhoneNumber() {
        final Set<String> expected = new HashSet<>(asList("+79601232233"));
        assertEquals("phone numbers do not match",
                expected,
                phoneBook.findAll().get("Alex"));
    }

}
