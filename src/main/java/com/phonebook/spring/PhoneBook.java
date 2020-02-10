package com.phonebook.spring;

import com.phonebook.main.InMemoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

/**
 * PhoneBook service implementation
 */
@Service
public class PhoneBook {

     @Autowired
    private InMemoryRepository repository;

    public PhoneBook() {
        // be careful this.repository will not be initialised if injection on setter is chosen
    }

    /**
     * injection is supported on constructor level.
     *
     * @param repository
     */
    // @Autowired
    public PhoneBook(InMemoryRepository repository) {
        this.repository = repository;
    }

    /**
     * injection is supported on setter level
     *
     * @param repository
     */
    public void setRepository(InMemoryRepository repository) {
        this.repository = repository;
    }

    /**
     * @return all pairs of type {name: [phone1, phone2]}
     */
    public Map<String, Set<String>> findAll() {
        return repository.findAll();
    }

    /**
     * add phone number for a name or create new record
     *
     * @param name
     * @param phone
     */
    public void addPhone(String name, String phone) {
        repository.addPhone(name, phone);
    }

    /**
     * removes a phone number from set; if set becomes empty after deletion remove record "{name:[phone]}" completely
     *
     * @param phone
     * @throws IllegalArgumentException if there is no such phone in repo
     */
    public void removePhone(String phone) throws IllegalArgumentException {
        repository.removePhone(phone);
    }
}
