package ude.sep.server.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ude.sep.server.ServerApplication;
import ude.sep.server.model.Time;
import ude.sep.server.service.TimeService;

import java.time.format.DateTimeFormatter;
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

    @PostMapping("/add/{timeFor}/{timeOf}/{distance}")
    public ResponseEntity<Time> addTime(@PathVariable int timeFor, @PathVariable int timeOf, @PathVariable int distance) throws JSONException {
        int toAdd = 10;
        List<Time> times = timeService.findAllTimesOf(timeOf);
        if(times.size()>=2) {
            toAdd+=10;
        }
        while(distance>5000){
            toAdd+=10;
            distance = distance-5000;
        }
        Time newTime = new Time(toAdd, timeOf, timeFor);
        Time time = timeService.addTime(newTime);
        return new ResponseEntity<>(time, HttpStatus.OK);
    }

    @GetMapping("/findof/{timeOf}")
    public ResponseEntity<List<Time>> getAllTimesOf(@PathVariable int timeOf) {
        List<Time> times = timeService.findAllTimesOf(timeOf);
        return new ResponseEntity<>(times, HttpStatus.OK);
    }

    @GetMapping("/findfor/{timeFor}")
    public ResponseEntity<List<Time>> getAllTimesFor(@PathVariable int timeFor) {
        List<Time> times = timeService.findAllTimesFor(timeFor);
        return new ResponseEntity<>(times, HttpStatus.OK);
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity delTime(@PathVariable String id) {
        timeService.delTime(Integer.valueOf(id));
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/change")
    public ResponseEntity<Time> changeTime(@RequestBody String body) throws JSONException {
        JSONObject input = new JSONObject(body);
        ServerApplication.toAdd = Integer.parseInt(input.getString("toAdd"));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/test")
    public ResponseEntity<String> testTime() throws JSONException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return new ResponseEntity<>(ServerApplication.getTime().format(dtf), HttpStatus.OK);
    }

    @DeleteMapping("/reset")
    public ResponseEntity changeTime() throws JSONException {
        ServerApplication.toAdd = 0;
        return new ResponseEntity(HttpStatus.OK);
    }
}