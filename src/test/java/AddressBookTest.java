import com.company.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AddressBookTest {

    public boolean compareMap(Map<String, Address> actual,
                          Map<String, Address> extended) {
        return actual.entrySet().containsAll(extended.entrySet())
                && extended.entrySet().containsAll(actual.entrySet());

    }
    AddressBook book = new AddressBook();
    @BeforeEach
    void setUp() {
        book.addAddressPerson("Смирнов", "Кантемировская", "8", "512" );
        book.addAddressPerson("Иванов", "Малая Конюшенная", "5", "65" );
        book.addAddressPerson("Шмакова", "Кантемировская", "17", "12" );
        book.addAddressPerson("Агафонов", "Большая Пушкарская", "45", "10" );
        book.addAddressPerson("Туранова", "Садовая", "8", "25" );
        book.addAddressPerson("Поляков", "Хлопина", "16", "53" );
    }



    @Test
    void addAddressPerson() {
        assertTrue(book.addAddressPerson("Смирнов", "Кантемировская", "8", "512" ));
        assertTrue(book.addAddressPerson("Иванов", "Малая Конюшенная", "5", "65" ));
        assertTrue(book.addAddressPerson("Шмакова", "Кантемировская", "17", "12" ));
        assertTrue(book.addAddressPerson("Агафонов", "Большая Пушкарская", "45", "10" ));
        assertTrue(book.addAddressPerson("Туранова", "Садовая", "8", "25" ));
        assertTrue(book.addAddressPerson("Поляков", "Хлопина", "16", "53" ));
    }

    @Test
    void deletePerson() {

        assertTrue(book.deletePerson("Иванов"));
        assertTrue(book.deletePerson("Туранова"));
        assertFalse(book.deletePerson("Шагова"));
    }

    @Test
    void changeAddressPerson() {
        assertTrue(book.changeAddressPerson("Смирнов", "Большая Пушкарская", "78", "52" ));
        assertTrue(book.changeAddressPerson("Иванов", "Кантемировская", "54", "5"));
        assertFalse(book.changeAddressPerson("Шагова", "Кантемировская", "54", "5"));
    }

    @Test
    void getAddress() {

    }

    @Test
    void getPersonToStreet() throws MalformedURLException {
        Map<String, Address> extend = new HashMap<>();
        Address address1 = new Address("Кантемировская", "8", "512");
        Address address2 = new Address("Кантемировская", "17", "12");
        extend.put("Смирнов", address1);
        extend.put("Шмакова", address2);
        assertTrue(compareMap(extend, book.getPersonToStreet("Кантемировская")));
    }

    @Test
    void getPersonToHome() {
    }
}