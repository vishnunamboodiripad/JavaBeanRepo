package learn.monsterBash.controller;

import learn.monsterBash.domain.Result;
import learn.monsterBash.domain.ResultType;
import learn.monsterBash.domain.WeatherService;
import learn.monsterBash.models.Weather;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class WeatherController {

    private final WeatherService service;

    public WeatherController(WeatherService service) {
        this.service = service;
    }

    @GetMapping("/weather/viewAll")
    public List<Weather> findAll(){
        return service.findAll();
    }

    @GetMapping("/weather/{weatherId}")
    public Weather findWeatherById(@PathVariable int weatherId) {
        return service.findByID(weatherId);
    }

    @PostMapping("/add/weather")
    public ResponseEntity<?> add(@RequestBody(required = false) Weather weather) {
        Result<Weather> result = service.add(weather);
        if (result.getType() == ResultType.INVALID){
            ValidationErrorResult validationErrorResult = new ValidationErrorResult();
            result.getMessages().forEach(validationErrorResult::addMessage);
            return new ResponseEntity<>(validationErrorResult, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);

    }

    @PutMapping("edit/weather/{weatherId}")
    public ResponseEntity<?> update(@PathVariable int weatherId, @RequestBody Weather weather){
        if (weatherId != weather.getWeatherId()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Result<Weather> result = service.update(weather);
        if (result.getType() == ResultType.INVALID) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else if (result.getType() == ResultType.NOT_FOUND) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/weather/{weatherId}")
    public ResponseEntity<Void> delete(@PathVariable int weatherId) {
        Result<Weather> result = service.deleteById(weatherId);
        if (result.isSuccess()){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }


}
