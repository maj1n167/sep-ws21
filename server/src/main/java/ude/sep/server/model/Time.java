package ude.sep.server.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Time {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    int start;
    int end;
    int timeOf;

    public Time(int start, int end, int timeOf) {
        this.start = start;
        this.end = end;
        this.timeOf = timeOf;
    }

    public Time() {}

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public int getStart() {return start;}

    public void setStart(int start) {this.start = start;}

    public int getEnd() {return end;}

    public void setEnd(int end) {this.end = end;}

    public int getTimeOf() {return timeOf;}

    public void setTimeOf(int timeOf) {this.timeOf = timeOf;}
}