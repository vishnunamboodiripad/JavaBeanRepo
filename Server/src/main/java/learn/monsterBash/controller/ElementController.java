package learn.monsterBash.controller;

import learn.monsterBash.domain.AffinityService;
import learn.monsterBash.domain.ElementService;
import learn.monsterBash.domain.Result;
import learn.monsterBash.models.Affinity;
import learn.monsterBash.models.Element;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api")
public class ElementController {
    private final ElementService service;

    public ElementController(ElementService service) {
        this.service = service;
    }

    @GetMapping("/element/viewAll")
    public List<Element> findAll() {
        return service.findAll();
    }

    @GetMapping("/element/{elementId}")
    public Element findById(@PathVariable int elementId) {
        return service.findById(elementId);
    }

    @PostMapping("/add/element")
    public ResponseEntity<Object> add(@RequestBody Element element) {
        Result<Element> result = service.add(element);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @PutMapping("/edit/element/{elementId}")
    public ResponseEntity<Object> update(@PathVariable int elementId, @RequestBody Element element) {
        if (elementId != element.getElementId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<Element> result = service.update(element);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ErrorResponse.build(result);
    }

    @DeleteMapping("/delete/element/{elementId}")
    public ResponseEntity<Void> delete(@PathVariable int elementId) {
        Result<Element> result = service.deleteById(elementId);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}