package com.company;

import java.util.HashMap;
import java.util.Map;
import java.util.*;



public class AddressBook  {
    private Map<String, Address> book;
    public AddressBook() {
        book = new HashMap<>();
    }

    public boolean addAddressPerson(String Person, String Street, String Home, String Room) {
        Address address = new Address(Street, Home, Room);
        if (!book.containsKey(Person))
            book.put(Person, address);

        return book.containsKey(Person);
    }

    public boolean deletePerson(String person) {
        if (book.containsKey(person))
            book.remove(person);
        return book.containsKey(person);
    }

    public boolean changeAddressPerson(String person, String street, String home, String room) {
        if (book.containsKey(person)) {
            Address address = new Address(street, home, room);
            book.replace(person, address);
            return true;
        }
        return false;
    }

    public Address getAddress(String person) {
        if (book.containsKey(person))
            return book.get(person);
        return null;
    }

    public Map<String, Address> getPersonToStreet(String street) {
        Map<String, Address> map = new HashMap<>();
        if (book.containsValue(street))
            for (String key : book.keySet())
                if (book.get(key).getStreet().equals(street))
                    map.put(key, book.get(key));
        return map;
    }

    public Map<String, Address> getPersonToHome(String street, String home)  {
        Map<String, Address> map = new HashMap<>();
        if (book.containsValue(street))
            for (String key : book.keySet())
                if (book.get(key).getStreet().equals(street) && book.get(key).getHome().equals(home))
                    map.put(key, book.get(key));
        return map;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
