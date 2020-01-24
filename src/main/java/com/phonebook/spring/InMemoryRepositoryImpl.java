package com.phonebook.spring;

import com.phonebook.main.InMemoryRepository;
import org.springframework.stereotype.Repository;

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
        throw new UnsupportedOperationException("Implement it!");
    }

    @Override
    public String findNameByPhone(String phone) {
        throw new UnsupportedOperationException("Implement it!");
    }

    @Override
    public void addPhone(String name, String phone) {
        throw new UnsupportedOperationException("Implement it!");
    }

    @Override
    public void removePhone(String phone) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Implement it!");
    }
}
