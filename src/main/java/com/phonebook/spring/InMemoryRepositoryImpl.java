package com.phonebook.spring;

import com.phonebook.main.InMemoryRepository;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Keeps phoneBook data in memory in ordered in accordance to addition.
 */
@Repository
public class InMemoryRepositoryImpl implements InMemoryRepository {

    private Map<String, Set<String>> data;

    /**
     * no args constructor
     */
    public InMemoryRepositoryImpl() {
        // LinkedHashMap is chosen because usually iteration order matters
        this(new LinkedHashMap<>());
    }

    /**
     * this constructor allows to inject initial data to the repository
     *
     * @param data
     */
    public InMemoryRepositoryImpl(Map<String, Set<String>> data) {
        this.data = new LinkedHashMap<>(data);
    }

    @Override
    public Map<String, Set<String>> findAll() {
        return new LinkedHashMap<>(this.data);
    }

    @Override
    public Set<String> findAllPhonesByName(String name) {
        return this.data.get(name);
    }

    @Override
    public String findNameByPhone(String phone) {
        for(String name : data.keySet()) {
            Set<String> allPhoneNumbersPersonHas = data.get(name);
            if (allPhoneNumbersPersonHas.contains(phone)) {
                return name;
            }
        }
        return null;
    }

    @Override
    public void addPhone(String name, String phone) {
        Set<String> phoneNumbers = findAllPhonesByName(name);
        if (phoneNumbers == null) {
            phoneNumbers = new HashSet<>();
            data.put(name, phoneNumbers);   // putIfAbsent??  naaah
        }
        phoneNumbers.add(phone);
    }

    @Override
    public void removePhone(String phone) throws IllegalArgumentException {
        String contactName = this.findNameByPhone(phone);
        if (contactName == null) {
            throw new IllegalArgumentException("The phone number " + phone + " is not in the phone book");
        }
        Set<String> phoneNumbers = data.get(contactName);
        phoneNumbers.remove(phone);

        if (phoneNumbers.isEmpty()) {
            this.data.remove(contactName);
        }
    }
}
