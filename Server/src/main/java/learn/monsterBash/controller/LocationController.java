package learn.monsterBash.controller;

import learn.monsterBash.domain.LocationService;
import learn.monsterBash.domain.Result;
import learn.monsterBash.models.Equipment;
import learn.monsterBash.models.Location;
import learn.monsterBash.models.Weather;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api")
public class LocationController {
    private final LocationService service;
    public LocationController(LocationService service) {
            this.service = service;
        }
        @GetMapping("/location/viewAll")
        public List<Location> findAll() {
            return service.findAll();
        }

        @GetMapping("/location/{locationId}")
        public Location findById(@PathVariable int locationId) {
            return service.findById(locationId);
        }

        @PostMapping("/add/location")
        public ResponseEntity<Object> add(@RequestBody Location location) {
            Result<Location> result = service.add(location);
            if (result.isSuccess()) {
                return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
            }
            return ErrorResponse.build(result);
        }

        @PutMapping("/edit/location/{locationId}")
        public ResponseEntity<Object> update(@PathVariable int locationId, @RequestBody Location location) {
            if (locationId != location.getLocationId()) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }

            Result<Location> result = service.update(location);
            if (result.isSuccess()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return ErrorResponse.build(result);
        }

    @DeleteMapping("/delete/location/{locationId}")
    public ResponseEntity<Void> delete(@PathVariable int locationId) {
        Result<Location> result = service.deleteById(locationId);
        if (result.isSuccess()){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    }

