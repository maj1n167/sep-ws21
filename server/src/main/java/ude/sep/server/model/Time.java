package ude.sep.server.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class Time {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String start;
    String end;
    int timeFor;
    int timeOf;

    public Time(int toAdd, int timeOf) {
        this.start = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(LocalDateTime.now());
        this.end = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(LocalDateTime.now().plusMinutes(toAdd));
        this.timeOf = timeOf;
    }

    public Time() {}

    public Time(int toAdd, int timeOf, int timeFor) {
        this.start = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(LocalDateTime.now());
        this.end = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(LocalDateTime.now().plusMinutes(toAdd));
        this.timeFor = timeFor;
        this.timeOf = timeOf;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getStart() {return start;}

    public void setStart(String start) {this.start = start;}

    public String getEnd() {return end;}

    public void setEnd(String end) {this.end = end;}

    public int getTimeOf() {return timeOf;}

    public void setTimeOf(int timeOf) {this.timeOf = timeOf;}

    public int getTimeFor() {return timeFor;}

    public void setTimeFor(int timeFor) {this.timeFor = timeFor;}
}