package main.java;

import java.util.ArrayList;
import java.util.Date;

public class Conference {
    private ArrayList<Date> dates;
    private String location;
    private String theme;
    private String description;
    private String schedule;
    private String ticketType;

    public Conference(ArrayList<Date> dates, String location, String theme, String description, String schedule, String ticketType){
        this.dates = dates;
        this.location = location;
        this.theme = theme;
        this.description = description;
        this.schedule = schedule;
        this.ticketType = ticketType;
    }
}
