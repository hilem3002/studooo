package com.example.studooodemoapi.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id", nullable = false)
    private int eventId;
    @Column(name = "event_name", nullable = false)
    private String eventName;
    @Column(name = "event_description", nullable = false)
    private String eventDescription;
    @Column(name = "location_lat", nullable = false)
    private double locationLat;
    @Column(name = "location_long", nullable = false)
    private double locationLong;
    @Column(name = "start_date_time", nullable = false)
    private String start_date_time;
    @Column(name = "end_date_time", nullable = false)
    private String end_date_time;
    @Column(name = "location_description", nullable = false)
    private String locationDescription;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private regularUser user;

    public Event() {

    }

    public Event(int eventId, String eventName, String eventDescription, double locationLat, double locationLong, String start_date_time, String end_date_time, String locationDescription, regularUser user) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.locationLat = locationLat;
        this.locationLong = locationLong;
        this.start_date_time = start_date_time;
        this.end_date_time = end_date_time;
        this.locationDescription = locationDescription;
        this.user = user;
    }

    public int getEventId() {
        return eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public double getLocationLat() {
        return locationLat;
    }

    public double getLocationLong() {
        return locationLong;
    }

    public String getStart_date_time() {
        return start_date_time;
    }

    public String getEnd_date_time() {
        return end_date_time;
    }

    public String getLocationDescription() {
        return locationDescription;
    }

    public regularUser getUser() {
        return user;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public void setLocationLat(double locationLat) {
        this.locationLat = locationLat;
    }

    public void setLocationLong(double locationLong) {
        this.locationLong = locationLong;
    }

    public void setStart_date_time(String start_date_time) {
        this.start_date_time = start_date_time;
    }

    public void setEnd_date_time(String end_date_time) {
        this.end_date_time = end_date_time;
    }

    public void setLocationDescription(String locationDescription) {
        this.locationDescription = locationDescription;
    }

    public void setUser(regularUser user) {
        this.user = user;
    }
}
