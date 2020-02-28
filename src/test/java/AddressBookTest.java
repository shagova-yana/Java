import com.company.*;
import org.junit.jupiter.api.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.*;

class AddressBookTest {

    boolean equalsAddress(Address actual, Address extend, int f) {
        if (f == 0)
            return actual.getStreet().equals(extend.getStreet())
                && Objects.equals(actual.getHome(), extend.getHome())
                && actual.getRoom().equals(extend.getRoom());
        if (f == 1)
            return actual.getStreet().equals(extend.getStreet());
        if (f == 2)
            return actual.getStreet().equals(extend.getStreet())
                    && Objects.equals(actual.getHome(), extend.getHome());
        return false;
    }

    boolean compareMap(Map<String, Address> actual, Map<String, Address> extended, int f) {
        if (actual.size() != extended.size()) return false;
        int count = 0;
        for (String key : actual.keySet()){
            if (extended.containsKey(key) && equalsAddress(actual.get(key), extended.get(key), f))
                count++;
            }
        return count == actual.size();
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
        book.addAddressPerson("Швалов", "Садовая", "8", "86" );
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
        assertTrue(equalsAddress(new Address("Садовая", "8", "25"), book.getAddress("Туранова"), 0));
        assertTrue(equalsAddress(new Address("Большая Пушкарская", "45", "10"), book.getAddress("Агафонов"), 0));
        assertFalse(equalsAddress(new Address("Большая Пушкарская", "45", "10"), book.getAddress("Иванов"), 0));
        assertNull(book.getAddress("Шагова"));
    }

    @Test
    void getPersonToStreet() {
        Map<String, Address> extend = new HashMap<>();
        Address address1 = new Address("Кантемировская", "8", "512");
        Address address2 = new Address("Кантемировская", "17", "12");
        extend.put("Смирнов", address1);
        extend.put("Шмакова", address2);
        assertTrue(compareMap(extend, book.getPersonToStreet("Кантемировская"), 1));

    }

    @Test
    void getPersonToHome() {
        Map<String, Address> extend = new HashMap<>();
        Address address1 = new Address("Садовая", "8", "25");
        Address address2 = new Address("Садовая", "8", "86");
        extend.put("Туранова", address1);
        extend.put("Швалов", address2);
        assertTrue(compareMap(extend, book.getPersonToHome("Садовая", "8"), 2));
    }
}
