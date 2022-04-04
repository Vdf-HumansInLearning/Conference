package com.vodafone.conference.models.entities.DTO;

import com.vodafone.conference.models.entities.SessionType;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class SessionCreationDTO {

    private String title;
    //private List<Speaker> speakers;
    private String description;
    private SessionType sessionType;
    private String topic;
    private SessionDTO.TechLevel techLevel;
    private List<String> keywords;
    private LocalDate date;
    private LocalDate endTime;
    private int review;
    //private List<Participant> participants;
    //private Track track;

    enum TechLevel {
        BEGINNER,
        MID_LEVEL,
        ADVANCED;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SessionType getSessionType() {
        return sessionType;
    }

    public void setSessionType(SessionType sessionType) {
        this.sessionType = sessionType;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public SessionDTO.TechLevel getTechLevel() {
        return techLevel;
    }

    public void setTechLevel(SessionDTO.TechLevel techLevel) {
        this.techLevel = techLevel;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDate endTime) {
        this.endTime = endTime;
    }

    public int getReview() {
        return review;
    }

    public void setReview(int review) {
        this.review = review;
    }

    @Override
    public String toString() {
        return "SessionCreationDTO{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", sessionType=" + sessionType +
                ", topic='" + topic + '\'' +
                ", techLevel=" + techLevel +
                ", keywords=" + keywords +
                ", date=" + date +
                ", endTime=" + endTime +
                ", review=" + review +
                '}';
    }
}
