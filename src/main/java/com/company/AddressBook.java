package com.company;

import java.util.*;
import java.lang.RuntimeException;

public class AddressBook  {
    private Map<String, Address> book;
    private  String person;
    public AddressBook() {
        book = new HashMap<>();
    }
    public String getName(){return this.person;}

    public boolean addAddressPerson(String person, String street, int home, int room) {
        if (book.containsKey(person)) return false;
        else {
            Address address = new Address(street, home, room);
            book.put(person, address);
        }
        return book.containsKey(person);
    }

    public boolean deletePerson(String person) {
        if (book.containsKey(person))
            book.remove(person);
        else return false;
        return !book.containsKey(person);
    }

    public boolean changeAddressPerson(String person, String street, int home, int room) {
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
        for (String key : book.keySet())
            if (book.get(key).getStreet().equals(street))
                map.put(key, book.get(key));
        if (map.size() == 0) throw new IllegalArgumentException();
        else return map;
    }

    public Map<String, Address> getPersonToHome(String street, int home) {
        Map<String, Address> map = new HashMap<>();
        for (String key : book.keySet())
            if (book.get(key).getStreet().equals(street) && book.get(key).getHome() == home)
                map.put(key, book.get(key));
            System.out.println(map);
        if (map.size() == 0) throw new IllegalArgumentException();
        else return map;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        AddressBook addressBook = (AddressBook) obj;
        return person.equals(addressBook.getName()) &&
                book.get(person).getStreet().equals(addressBook.getAddress(addressBook.getName()).getStreet()) &&
                book.get(person).getHome() == addressBook.getAddress(addressBook.getName()).getHome() &&
                book.get(person).getRoom() == addressBook.getAddress(addressBook.getName()).getRoom();
    }

    @Override
    public String toString() {
        return person + " " + book.get(person).toString();
    }

    @Override
    public int hashCode() {
        int result = person.hashCode();
        result = 31 * result + book.get(person).getStreet().hashCode();
        result = 31 * result + book.get(person).getHome();
        result = 31 * result + book.get(person).getRoom();
        return result;
    }
}