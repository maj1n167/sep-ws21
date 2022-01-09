package ude.sep.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ude.sep.server.model.Time;
import ude.sep.server.repo.TimeRepo;

import java.util.List;

@Service
public class TimeService {
    private final TimeRepo timeRepo;

    @Autowired
    public TimeService(TimeRepo timeRepo) {this.timeRepo = timeRepo;}

    public Time addTime(Time time) {return timeRepo.save(time);}

    public List<Time> findAllTimesOf(int timeOf) {return timeRepo.findAllByTimeOf(timeOf);}

    public void delTime(int time) {timeRepo.deleteById(time);}

    public List<Time> findAllTimes() {return timeRepo.findAll();}
}
