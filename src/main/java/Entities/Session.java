package Entities;

import java.util.ArrayList;
import java.util.Date;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title", nullable = false)
    private String title;

    //  DE DEFINIT RELATIA
//    private ArrayList<Entities.Speaker> speakers;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "topic", nullable = false)
    private String topic;

    @Column(name = "tech_lvl", nullable = false)
    private String techlvl;

    //  DE VAZUT CUM DEFINIT ARRAY-UL IN DB
//    @Column(name = "keywords", nullable = false)
//    private String[] keywords;

    // length in minutes
    @Column(name = "length", nullable = false)
    private int length;

    @Column(name = "date", nullable = false)
    private Date date;

    // review between 1 and 5
    @Column(name = "review", nullable = false)
    private int review;

    // DE DEFINIT RELATIA
//    private ArrayList<Entities.Participant> participants;
}