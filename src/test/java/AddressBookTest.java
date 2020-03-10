import com.company.*;
import org.junit.jupiter.api.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class AddressBookTest {
     enum EqualsAddress{
         FULL, STREET, HOME;
    }
      boolean equalsAddress(Address actual, Address extend, EqualsAddress f) {
         if (f == EqualsAddress.FULL)  return actual.getStreet().equals(extend.getStreet())
                  && Objects.equals(actual.getHome(), extend.getHome())
                  && actual.getRoom() == extend.getRoom();
          if (f == EqualsAddress.STREET)
              return actual.getStreet().equals(extend.getStreet());
          if (f == EqualsAddress.HOME)
              return actual.getStreet().equals(extend.getStreet())
                      && Objects.equals(actual.getHome(), extend.getHome());
          return false;
      }

     boolean compareMap(Map<String, Address> actual, Map<String, Address> extended, EqualsAddress f) {
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
        book.addAddressPerson("Смирнов", "Кантемировская", 8, 512 );
        book.addAddressPerson("Иванов", "Малая Конюшенная", 5, 65 );
        book.addAddressPerson("Шмакова", "Кантемировская", 17, 12 );
        book.addAddressPerson("Агафонов", "Большая Пушкарская", 45, 10 );
        book.addAddressPerson("Туранова", "Садовая", 8, 5);
        book.addAddressPerson("Поляков", "Хлопина", 16, 53 );
        book.addAddressPerson("Швалов", "Садовая", 8, 86 );
    }

    @Test
    void addAddressPerson() {
        assertTrue(book.addAddressPerson("Смирнова", "Кантемировская", 8, 512 ));
        assertTrue(book.addAddressPerson("Инов", "Малая Морская", 5, 89 ));
        assertTrue(book.addAddressPerson("Фетисов", "Харченко", 16, 418));
        assertTrue(book.addAddressPerson("Логвинов", "Большая Морская", 49, 50 ));
        assertFalse(book.addAddressPerson("Туранова", "Садовая", 8, 25 ));
        assertFalse(book.addAddressPerson("Поляков", "Хлопина", 16, 35 ));
    }

    @Test
    void deletePerson() {
        assertTrue(book.deletePerson("Иванов"));
        assertTrue(book.deletePerson("Туранова"));
        assertFalse(book.deletePerson("Шагова"));
    }

    @Test
    void changeAddressPerson() {
        assertTrue(book.changeAddressPerson("Смирнов", "Большая Пушкарская", 78, 52 ));
        assertTrue(book.changeAddressPerson("Иванов", "Кантемировская", 54, 5));
        assertFalse(book.changeAddressPerson("Шагова", "Кантемировская", 54, 5));
    }

    @Test
    void getAddress() {
        assertTrue(equalsAddress(new Address("Садовая", 8, 5), book.getAddress("Туранова"), EqualsAddress.FULL));
        assertTrue(equalsAddress(new Address("Большая Пушкарская", 45, 10), book.getAddress("Агафонов"), EqualsAddress.FULL));
        assertFalse(equalsAddress(new Address("Большая Пушкарская", 45, 10), book.getAddress("Иванов"), EqualsAddress.FULL));
        assertNull(book.getAddress("Шагова"));
    }

    @Test
    void getPersonToStreet() {
        Map<String, Address> extend = new HashMap<>();
        Address address1 = new Address("Кантемировская", 8, 512);
        Address address2 = new Address("Кантемировская", 17, 12);
        extend.put("Смирнов", address1);
        extend.put("Шмакова", address2);
        assertTrue(compareMap(extend, book.getPersonToStreet("Кантемировская"), EqualsAddress.STREET));
        try {
            extend.clear();
            assertTrue(compareMap(extend, book.getPersonToStreet("Гжатская"), EqualsAddress.STREET));
        }
        catch (IllegalArgumentException e){
            System.out.println("Данная улица не содержится в телефонной книге");
        }
    }

    @Test
    void getPersonToHome() {
        Map<String, Address> extend = new HashMap<>();
        Address address1 = new Address("Садовая", 8, 25);
        Address address2 = new Address("Садовая", 8, 86);
        extend.put("Туранова", address1);
        extend.put("Швалов", address2);
        assertTrue(compareMap(extend, book.getPersonToHome("Садовая", 8), EqualsAddress.HOME));
        extend.clear();
        try {
            assertTrue(compareMap(extend, book.getPersonToHome("Садовая", 14), EqualsAddress.HOME));
        }
        catch (IllegalArgumentException e){
            System.out.println("Данного дома нет в телефонной книге");
        }
        try {
            assertTrue(compareMap(extend, book.getPersonToHome("Хлопина", 14), EqualsAddress.HOME));
        }
        catch (IllegalArgumentException e){
            System.out.println("Данного дома и улицы нет в телефонной книге");
        }
    }

    @Test
    void equals(){
        AddressBook first = new AddressBook();
        first.addAddressPerson("Смирнов", "Кантемировская", 8, 512 );
        first.addAddressPerson("Иванов", "Малая Конюшенная", 5, 65 );
        first.addAddressPerson("Шмакова", "Кантемировская", 17, 12 );
        first.addAddressPerson("Агафонов", "Большая Пушкарская", 45, 10 );
        first.addAddressPerson("Туранова", "Садовая", 8, 5);
        first.addAddressPerson("Поляков", "Хлопина", 16, 53 );
        first.addAddressPerson("Швалов", "Садовая", 8, 86 );
        assertTrue(book.equals(first));
        assertEquals(book.hashCode(), first.hashCode());
        first.deletePerson("Смирнов");
        assertFalse(book.equals(first));
        first.changeAddressPerson("Иванов","Кантемировская", 17, 12 );
        assertTrue(first.getAddress("Иванов").equals(first.getAddress("Шмакова")));
        assertFalse(first.getAddress("Поляков").equals(first.getAddress("Шмакова")));
        assertEquals(first.getAddress("Иванов").hashCode(), first.getAddress("Шмакова").hashCode());
        System.out.println(first.toString());
        System.out.println(book.getAddress("Туранова").toString());
    }
}