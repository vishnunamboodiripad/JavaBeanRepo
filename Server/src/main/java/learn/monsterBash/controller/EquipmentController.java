package learn.monsterBash.controller;

import learn.monsterBash.domain.EquipmentService;
import learn.monsterBash.domain.Result;
import learn.monsterBash.models.Equipment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api")
public class EquipmentController {

    private final EquipmentService service;

    public EquipmentController(EquipmentService service) {
        this.service = service;
    }

    @GetMapping("/equipment/viewAll")
    public List<Equipment> findAll() {
        return service.findAll();
    }

    @GetMapping("/equipment/{equipmentId}")
    public Equipment findById(@PathVariable int equipmentId) {
        return service.findById(equipmentId);
    }

    @PostMapping("/add/equipment")
    public ResponseEntity<Object> add(@RequestBody Equipment equipment) {
        Result<Equipment> result = service.add(equipment);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @PutMapping("edit/equipment/{equipmentId}")
    public ResponseEntity<Object> update(@PathVariable int equipmentId, @RequestBody Equipment equipment) {
        if (equipmentId != equipment.getEquipmentId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<Equipment> result = service.update(equipment);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ErrorResponse.build(result);
    }

    @DeleteMapping("/delete/equipment/{equipmentId}")
    public ResponseEntity<Void> deleteById(@PathVariable int equipmentId) {
        if (service.deleteById(equipmentId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}

