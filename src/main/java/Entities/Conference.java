package Entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Conference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    // DE DEFINIT RELATIA
//    private ArrayList<Date> dates;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "theme", nullable = false)
    private String theme;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "schedule", nullable = false)
    private String schedule;

    @Column(name = "ticket_type", nullable = false)
    private String ticketType;
}