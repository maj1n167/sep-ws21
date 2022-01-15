package ude.sep.server.model;


import ude.sep.server.controller.TimeController;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.format.DateTimeFormatter;

@Entity
public class Time {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String start;
    String end;
    int restId;
    int userId;
    long orderId;

    public Time(int toAdd, long orderId) {
        this.start = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(TimeController.getTime());
        this.end = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(TimeController.getTime().plusMinutes(toAdd));
        this.orderId = orderId;
    }

    public Time() {}

    public Time(int toAdd, int restId, int userId) {
        this.start = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(TimeController.getTime());
        this.end = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(TimeController.getTime().plusMinutes(toAdd));
        this.restId = restId;
        this.userId = userId;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getStart() {return start;}

    public void setStart(String start) {this.start = start;}

    public String getEnd() {return end;}

    public void setEnd(String end) {this.end = end;}

    public int getRestId() {return restId;}

    public void setRestId(int restId) {this.restId = restId;}

    public int getUserId() {return userId;}

    public void setUserId(int userId) {this.userId = userId;}

    public long getOrderId() {return orderId;}

    public void setOrderId(long orderId) {this.orderId = orderId;}
}