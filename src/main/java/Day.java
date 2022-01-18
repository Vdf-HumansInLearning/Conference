import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class Day {
    public ArrayList<Track> tracks;
    private Date date;

    public Day(ArrayList<Track> tracks){
        this.tracks = tracks;
        this.date = new Date();
    }
}
