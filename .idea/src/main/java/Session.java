package main.java;

import java.util.ArrayList;
import java.util.Date;

public class Session {

    private String title;
    private ArrayList<Speaker> speakers;
    private String description;
    private String type;
    private String topic;
    private String techlvl;
    private String[] keywords;

    // length in minutes
    private int length;

    private Date date;

    // review between 1 and 5
    private int review;

    private ArrayList<Participant> participants;

    public Session(String title, ArrayList<Speaker> speakers, String description, String type, String topic, String techlvl, String[] keywords, int length, Date date, int review, ArrayList<Participant> participants) {
        this.title = title;
        this.speakers = speakers;
        this.description = description;
        this.type = type;
        this.topic = topic;
        this.techlvl = techlvl;
        this.keywords = keywords;
        this.length = length;
        this.date = date;
        this.review = review;
        this.participants = participants;
    }

}
