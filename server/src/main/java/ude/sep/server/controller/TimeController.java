package ude.sep.server.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ude.sep.server.model.Time;
import ude.sep.server.service.TimeService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/time")
public class TimeController {
    private final TimeService timeService;
    public static int altTime = 0;

    public TimeController(TimeService timeService) {this.timeService = timeService;}

    @GetMapping
    public ResponseEntity<List<Time>> getAllTimes() {
        List<Time> times = timeService.findAllTimes();
        return new ResponseEntity<>(times, HttpStatus.OK);
    }

    @PostMapping("/add/{userId}/{restId}/{distance}")
    public ResponseEntity<Time> addTime(@PathVariable int userId, @PathVariable int restId, @PathVariable int distance, @RequestBody String input) throws JSONException {
        int toAdd = 10;
        List<Time> times = timeService.findAllTimesOfRest(restId);
        if(times.size()>=2) {
            toAdd+=10;
        }
        while(distance>0){
            toAdd+=10;
            distance = distance-5000;
        }
        Time newTime = new Time(toAdd, restId, userId);
        Time time = timeService.addTime(newTime);
        return new ResponseEntity<>(time, HttpStatus.OK);
    }

    @GetMapping("/findof/{restId}")
    public ResponseEntity<List<Time>> getAllTimesOfRest(@PathVariable int restId) {
        List<Time> times = timeService.findAllTimesOfRest(restId);
        return new ResponseEntity<>(times, HttpStatus.OK);
    }

    @GetMapping("/findfor/{userId}")
    public ResponseEntity<List<Time>> getAllTimesForUser(@PathVariable int userId) {
        List<Time> times = timeService.findAllTimesForUser(userId);
        return new ResponseEntity<>(times, HttpStatus.OK);
    }

//    @PostMapping("/add/{timeOf}/{orderId}")
//    public ResponseEntity<Time> addTime(@PathVariable int timeOf, @PathVariable long orderId @RequestBody input) throws JSONException {
//        int toAdd = 10;
//        List<Time> times = timeService.findAllTimesOf(timeOf);
//        if(times.size()>=2) {
//            toAdd+=10;
//        }
//        while(distance>5000){
//            toAdd+=10;
//            distance = distance-5000;
//        }
//        Time newTime = new Time(toAdd, timeOf, timeFor);
//        Time time = timeService.addTime(newTime);
//        return new ResponseEntity<>(time, HttpStatus.OK);
//    }

    @GetMapping("/find/{orderId}")
    public ResponseEntity<List<Time>> getAllTimesFor(@PathVariable long orderId) {
        List<Time> times = timeService.findAllByOrderId(orderId);
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
        altTime = altTime + Integer.parseInt(input.getString("toAdd"));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/test")
    public ResponseEntity<String> testTime() throws JSONException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return new ResponseEntity<>(getTime().format(dtf), HttpStatus.OK);
    }

    @DeleteMapping("/reset")
    public ResponseEntity changeTime() throws JSONException {
        altTime = 0;
        return new ResponseEntity(HttpStatus.OK);
    }

    public static LocalDateTime getTime() {
        return LocalDateTime.now().plusMinutes(altTime);
    }
}