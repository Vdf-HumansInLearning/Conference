import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpeakerTest {

    Participant participant = new Participant("Tom", "Morrison", "tom.morrison@gmail.com", "0722446699", "tom.morrison", "password123");
    Speaker tester = new Speaker(participant, "Senior Software Developer", "Vodafone", "Tom.Morrison", "Tom.Morrison", "Tom.Morrison", "Passionate about the fast emerging pace of technology.");

    @Test
    void getParticipant() {
        assertTrue(participant.equals(tester.getParticipant()));
    }

    @Test
    void setParticipant() {
        Participant participant2 = new Participant("Jane", "Thomson", "jane.thomson@gmail.com", "0788149825", "jane.thomson", "pwd000#");
        tester.setParticipant(participant2);

        assertTrue(participant2.equals(tester.getParticipant()));
    }

    @Test
    void getTitle() {
        assertEquals("Senior Software Developer", tester.getTitle());
    }

    @Test
    void setTitle() {
        tester.setTitle("title");

        assertEquals("title", tester.getTitle());
    }

    @Test
    void getCompany() {
        assertEquals("Vodafone", tester.getCompany());
    }

    @Test
    void setCompany() {
        tester.setCompany("company");

        assertEquals("company", tester.getCompany());
    }

    @Test
    void getLinkedinAcc() {
        assertEquals("Tom.Morrison", tester.getLinkedinAcc());
    }

    @Test
    void setLinkedinAcc() {
        tester.setLinkedinAcc("Linked-In");

        assertEquals("Linked-In", tester.getLinkedinAcc());
    }

    @Test
    void getTwitterAcc() {
        assertEquals("Tom.Morrison", tester.getTwitterAcc());
    }

    @Test
    void setTwitterAcc() {
        tester.setTwitterAcc("Twitter");

        assertEquals("Twitter", tester.getTwitterAcc());
    }

    @Test
    void getGithubAcc() {
        assertEquals("Tom.Morrison", tester.getGithubAcc());
    }

    @Test
    void setGithubAcc() {
        tester.setGithubAcc("GitHub");

        assertEquals("GitHub", tester.getGithubAcc());
    }

    @Test
    void getBiography() {
        assertEquals("Passionate about the fast emerging pace of technology.", tester.getBiography());
    }

    @Test
    void setBiography() {
        tester.setBiography("biography");

        assertEquals("biography", tester.getBiography());
    }
}