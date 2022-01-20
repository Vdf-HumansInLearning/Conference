import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParticipantTest {

    Participant tester = new Participant("Tom", "Morrison", "tom.morrison@gmail.com", "0722446699", "tom.morrison", "password123");

    @Test
    void getLastName() {
        assertEquals("Morrison", tester.getLastName());
    }

    @Test
    void setLastName() {
        tester.setLastName("last name");

        assertEquals("last name", tester.getLastName());
    }

    @Test
    void getEmail() {
        assertEquals("tom.morrison@gmail.com", tester.getEmail());
    }

    @Test
    void setEmail() {
        tester.setEmail("email");

        assertEquals("email", tester.getEmail());
    }

    @Test
    void getPhoneNumber() {
        assertEquals("0722446699", tester.getPhoneNumber());
    }

    @Test
    void setPhoneNumber() {
        tester.setPhoneNumber("0000");

        assertEquals("0000", tester.getPhoneNumber());
    }

    @Test
    void getUsername() {
        assertEquals("tom.morrison", tester.getUsername());
    }

    @Test
    void setUsername() {
        tester.setUsername("username");

        assertEquals("username", tester.getUsername());
    }

    @Test
    void getPassword() {
        assertEquals("password123", tester.getPassword());
    }

    @Test
    void setPassword() {
        tester.setPassword("password");

        assertEquals("password", tester.getPassword());
    }
}