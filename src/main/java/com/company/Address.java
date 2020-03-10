package com.company;

public class Address {
    private String street;
    private int home;
    private int room;

    public Address(String street, int home, int room) {
        this.street = street;
        if (home > 0)
            this.home = home;
        else throw new NumberFormatException();
        if (room > 0)
            this.room = room;
        else throw new NumberFormatException();
    }

    public int getHome() {
        return home;
    }
    public int getRoom() {
        return room;
    }
    public String getStreet() {
        return street;
    }

    @Override
    public String toString() {
        return " st. " + street + " h." + home + " r." + room;
    }

    @Override
    public int hashCode() {
        int result = street.hashCode();
        result = 31 * result + home;
        result = 31 * result + room;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Address address = (Address) obj;
        return street.equals(address.street) &&
                home == address.home &&
                room == address.room;
    }
}