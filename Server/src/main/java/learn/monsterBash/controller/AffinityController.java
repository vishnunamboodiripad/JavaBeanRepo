package learn.monsterBash.controller;

import learn.monsterBash.domain.AffinityService;
import learn.monsterBash.domain.LocationService;
import learn.monsterBash.domain.Result;
import learn.monsterBash.models.Affinity;
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
public class AffinityController {
    private final AffinityService service;
    public AffinityController(AffinityService service) {
        this.service = service;
    }
    @GetMapping("/affinity/viewAll")
    public List<Affinity> findAll() {
        return service.findAll();
    }

    @GetMapping("/affinity/{affinityId}")
    public Affinity findById(@PathVariable int affinityId) {
        return service.findById(affinityId);
    }

    @PostMapping("/add/affinity")
    public ResponseEntity<Object> add(@RequestBody Affinity affinity) {
        Result<Affinity> result = service.add(affinity);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @PutMapping("/edit/affinity/{affinityId}")
    public ResponseEntity<Object> update(@PathVariable int affinityId, @RequestBody Affinity affinity) {
        if (affinityId != affinity.getAffinityId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<Affinity> result = service.update(affinity);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ErrorResponse.build(result);
    }

    @DeleteMapping("/delete/affinity/{affinityId}")
    public ResponseEntity<Void> delete(@PathVariable int affinityId) {
        Result<Affinity> result = service.deleteById(affinityId);
        if (result.isSuccess()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

}

