package ude.sep.server.controller;

import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ude.sep.server.model.Time;
import ude.sep.server.service.TimeService;

import java.util.List;

@RestController
@RequestMapping("/time")
public class TimeController {
    private final TimeService timeService;

    public TimeController(TimeService timeService) {this.timeService = timeService;}

    @GetMapping
    public ResponseEntity<List<Time>> getAllTimes() {

        List<Time> times = timeService.findAllTimes();

        return new ResponseEntity<>(times, HttpStatus.OK);

    }

    @PostMapping("/add")
    public ResponseEntity<Time> addTime() throws JSONException {
        Time newTime = new Time();
        Time time = timeService.addTime(newTime);
        return new ResponseEntity<>(time, HttpStatus.OK);
    }

    @GetMapping("/find/{timeOf}")
    public ResponseEntity<List<Time>> getAllTimesOf(@PathVariable String timeOf) {
        List<Time> times = timeService.findAllTimesOf(Integer.valueOf(timeOf));
//        if(favs.size()!= 0) {
        return new ResponseEntity<>(times, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(favs, HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("/del/{id}")
    public ResponseEntity delTime(@PathVariable String id) {
        timeService.delTime(Integer.valueOf(id));
        return new ResponseEntity(HttpStatus.OK);
    }
}