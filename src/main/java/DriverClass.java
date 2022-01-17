import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class DriverClass {
    public static void main(String[] args) {

        ArrayList<Participant> participants = new ArrayList<>();
        ArrayList<Speaker> speakers = new ArrayList<>();

        Date date1 = new Date(20-1-122);
        Date date2 = new Date(21-1-122);
        Date date3 = new Date(22-1-122);

        Participant participant1 = new Participant("Tom", "Morrison", "tom.morrison@gmail.com", "0722446699", "tom.morrison", "password123");
        Participant participant2 = new Participant("Jane", "Thomson", "jane.thomson@gmail.com", "0788149825", "jane.thomson", "pwd000#");
        Participant participant3 = new Participant("Jon", "Adams", "jon.adams@gmail.com", "0707079988", "jon.adams", "jon((#))");

        participants.add(participant1);
        participants.add(participant2);
        participants.add(participant3);

        Organiser organiser1 = new Organiser("Emily", "Brown", "Dr.", "emily.brown@gmail.com", "0768683673", "emily.brown", "emily200172");

        Speaker speaker = new Speaker(participant1, "Senior Software Developer", "Vodafone", "Tom.Morrison", "Tom.Morrison", "Tom.Morrison", "Passionate about the fast emerging pace of technology.");

        speakers.add(speaker);

        Session session1 = new Session("Emerging Software Analysis", speakers, "Brief overview of software development opportunities", "Workshop", "Software Development", "Intermediate", new String[]{"software", "technology", "intermediate"}, 60, date1, 4, participants);
        Session session2 = new Session("Python Daily", speakers, "Python coding tutorial", "Tutorial", "Software Development in Python", "Advanced", new String[]{"software", "technology", "advanced"}, 90, date3, 3, participants);

        Track track1 = new Track(new ArrayList<>(Arrays.asList(session1)));
        Track track2 = new Track(new ArrayList<>(Arrays.asList(session2)));

        Day day = new Day(new ArrayList<>(Arrays.asList(track1, track2)));

        Conference conference = new Conference(new ArrayList<>(Arrays.asList(date1, date2, date3)), "Romania, Bucharest", "Technological Breakthroughs", "Conference presenting the latest technological breakthroughs", "20-22 January 2022", "Normal Entry");
    }
}