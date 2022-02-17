import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DriverClass {

    private static final String insertTicket =  "INSERT INTO ticket(ticket_id, type) VALUES (UUID_TO_BIN(UUID()), 'Normal');";
    private static final String insertSpeaker =  "INSERT INTO speaker(speaker_id, title, company, linkedin_acc, twitter_acc, github_acc, biography)\n" +
            "VALUES (UUID_TO_BIN(UUID()), 'Test', 'Test', 'Test', 'Test', 'Test', 'Test');";
    private static final String insertSessionType =  "INSERT INTO session_type(session_type_id, type, length) VALUES (UUID_TO_BIN(UUID()), 'presentation', '30');";
    private static final String insertSession =  "INSERT INTO session(session_id, title, description, session_type_id, topic, techlvl, keywords, length, date, end_time, review)\n" +
            "VALUES (UUID_TO_BIN(UUID()), 'Test', 'Test', UUID_TO_BIN((SELECT HEX(t.session_type_id) FROM session_type t)), 'Test', 'beginner', 'Test', 10, NOW(), NOW(), 5);";
    private static final String insertParticipant =  "INSERT INTO participant(participant_id, first_name, last_name, title, email, phone_number, username, password, session_id, is_organiser, is_speaker)\n" +
            "VALUES (UUID_TO_BIN(UUID()), 'Test', 'Test', 'Test', 'test@test.com', '0729000000', 'testuser', 'password', UUID_TO_BIN((SELECT HEX(t.session_id) FROM session t)), 1, 1);";
    private static final String insertConference =  "INSERT INTO conference(conference_id, location, theme, description, ticket_id, participant_id)\n" +
            "VALUES (UUID_TO_BIN(UUID()), 'Bucharest', 'Testing', 'Test', UUID_TO_BIN((SELECT HEX(t.ticket_id) FROM ticket t)), UUID_TO_BIN((SELECT HEX(t.participant_id) FROM participant t)));";
    private static final String insertTrack =  "INSERT INTO track(track_id, title, session_id) VALUES (UUID_TO_BIN(UUID()), 'Test', UUID_TO_BIN((SELECT HEX(t.session_id) FROM session t)));\n";
    private static final String insertDay =  "INSERT INTO day (day_id, date, conference_id, track_id) VALUES (UUID_TO_BIN(UUID()), NOW(), UUID_TO_BIN((SELECT HEX(t.conference_id) FROM conference t)), UUID_TO_BIN((SELECT HEX(t.track_id) FROM track t)));";

    private static final String getSql = "SELECT * FROM conference;";

    public static void main(String[] args) throws Exception {

        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/conference", "root" , "root")) {
            Statement statement = connection.createStatement();
            System.out.println("Insert row " + statement.executeUpdate(insertTicket));
            System.out.println("Insert row " + statement.executeUpdate(insertSpeaker));
            System.out.println("Insert row " + statement.executeUpdate(insertSessionType));
            System.out.println("Insert row " + statement.executeUpdate(insertSession));
            System.out.println("Insert row " + statement.executeUpdate(insertParticipant));
            System.out.println("Insert row " + statement.executeUpdate(insertConference));
            System.out.println("Insert row " + statement.executeUpdate(insertTrack));
            System.out.println("Insert row " + statement.executeUpdate(insertDay));
            ResultSet result  = statement.executeQuery(getSql);
            System.out.println(result);
            while(result.next()) {
                System.out.println(result.getString(1));
                System.out.println(result.getString(2));
                System.out.println(result.getString(3));
                System.out.println(result.getString(4));
                System.out.println(result.getString(5));
                System.out.println(result.getString(6));
            }
        }
    }
}
