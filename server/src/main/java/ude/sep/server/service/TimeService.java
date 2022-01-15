package ude.sep.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ude.sep.server.controller.TimeController;
import ude.sep.server.model.Time;
import ude.sep.server.repo.TimeRepo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class TimeService {
    private final TimeRepo timeRepo;

    @Autowired
    public TimeService(TimeRepo timeRepo) {this.timeRepo = timeRepo;}

    public Time addTime(Time time) {return timeRepo.save(time);}

    public List<Time> findAllTimesOfRest(int restId) {
        // Hier kommt die Erkennung, ob bestellungen bereits abgeschlossen sind rein, diese sollen dann auch geloescht werden
        List<Time> output = timeRepo.findAllByRestId(restId);
        for(int i=0;i<output.size();i++) {
            boolean isCompleted = false;
            Time current = output.get(i);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            isCompleted = LocalDateTime.parse(current.getEnd(), dtf).isBefore(TimeController.getTime());
            System.out.println("currentTime: " + TimeController.getTime() + "\nendTime: " + LocalDateTime.parse(current.getEnd(), dtf) + "\nisCompleted: " + isCompleted);
            if(isCompleted) {
                timeRepo.deleteById(current.getId());
            };
        }
        return timeRepo.findAllByRestId(restId);
    }

    public void delTime(int time) {timeRepo.deleteById(time);}

    public List<Time> findAllTimes() {return timeRepo.findAll();}

    public List<Time> findAllTimesForUser(int userId) {return timeRepo.findAllByUserId(userId);}

    public List<Time> findAllByOrderId(long orderId) {return timeRepo.findAllByOrderId(orderId);}
}
