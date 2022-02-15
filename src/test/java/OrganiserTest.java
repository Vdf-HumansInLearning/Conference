import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrganiserTest {

    Organiser tester= new Organiser("Emily", "Brown", "Dr.", "emily.brown@gmail.com", "0768683673", "emily.brown", "emily200172");

    @Test
    void getFirstName() {
        assertEquals("Emily", tester.getFirstName());
    }

    @Test
    void setFirstName() {
        tester.setFirstName("first name");

        assertEquals("first name", tester.getFirstName());
    }

    @Test
    void getLastName() {
        assertEquals("Brown", tester.getLastName());
    }

    @Test
    void setLastName() {
        tester.setLastName("last name");

        assertEquals("last name", tester.getLastName());
    }

    @Test
    void getTitle() {
        assertEquals("Dr.", tester.getTitle());
    }

    @Test
    void setTitle() {
        tester.setTitle("title");

        assertEquals("title", tester.getTitle());
    }

    @Test
    void getEmail() {
        assertEquals("emily.brown@gmail.com", tester.getEmail());
    }

    @Test
    void setEmail() {
        tester.setEmail("email");

        assertEquals("email", tester.getEmail());
    }

    @Test
    void getPhoneNumber() {
        assertEquals("0768683673", tester.getPhoneNumber());
    }

    @Test
    void setPhoneNumber() {
        tester.setPhoneNumber("0000");

        assertEquals("0000", tester.getPhoneNumber());
    }

    @Test
    void getUsername() {
        assertEquals("emily.brown", tester.getUsername());
    }

    @Test
    void setUsername() {
        tester.setUsername("username");

        assertEquals("username", tester.getUsername());
    }

    @Test
    void getPassword() {
        assertEquals("emily200172", tester.getPassword());
    }

    @Test
    void setPassword() {
        tester.setPassword("password");

        assertEquals("password", tester.getPassword());
    }
}