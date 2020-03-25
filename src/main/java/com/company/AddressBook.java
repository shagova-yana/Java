package com.company;

import java.util.*;

public class AddressBook  {
    private Map<String, Address> book;
    public AddressBook() {
        book = new HashMap<>();
    }
    private Set<String> getName(){return book.keySet();}

    public boolean addAddressPerson(String person, String street, int home, int room) {
        if (book.containsKey(person)) return false;
        else {
            Address address = new Address(street, home, room);
            book.put(person, address);
            return true;
        }
    }

    public boolean deletePerson(String person) {
        if (book.containsKey(person)) {
            book.remove(person);
            return true;
        }
        else return false;
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
            return book.get(person);
    }

    public Map<String, Address> getPersonToStreet(String street) {
        Map<String, Address> map = new HashMap<>();
        for (String key : book.keySet())
            if (book.get(key).getStreet().equals(street))
                map.put(key, book.get(key));
        if (map.size() == 0) throw new IllegalArgumentException("Данная улица не содержится в телефонной книге");
        else return map;
    }

    public Map<String, Address> getPersonToHome(String street, int home) {
        Map<String, Address> map = new HashMap<>();
        for (String key : book.keySet())
            if (book.get(key).getStreet().equals(street) && book.get(key).getHome() == home)
                map.put(key, book.get(key));
        if (map.size() > 0)  return map;
        else throw new IllegalArgumentException();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        AddressBook addressBook = (AddressBook) obj;
        int count = 0;
        for (String key : book.keySet())
            for (String keys : addressBook.getName())
                if (key.equals(keys) && book.get(key).equals(addressBook.getAddress(keys)))
                    count++;
        return count == book.size() && count == addressBook.getName().size();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (String key : book.keySet()) {
            str.append(key);
            str.append(book.get(key).toString());
            System.lineSeparator();
     }
        return str.toString();
    }

    @Override
    public int hashCode() {
        int result = 0;
        for (String key : book.keySet())
            result = 31 * result + key.hashCode() + book.get(key).hashCode();
        return result;
    }
}