package learn.monsterBash.controller;

import learn.monsterBash.domain.MonsterService;
import learn.monsterBash.domain.Result;
import learn.monsterBash.domain.ResultType;
import learn.monsterBash.models.Monster;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MonsterController {

    private final MonsterService service;


    public MonsterController(MonsterService service) {
        this.service = service;
    }
    /*
    @GetMapping("/monster/viewAll")
    public List<Monster> findAll(){
        return service.findAll();
    }

    @GetMapping("/monster/{monsterId}")
    public Monster findMonsterById(@PathVariable int monsterId) {
        return service.findById(monsterId);
    }

    @PostMapping("/add/monster")
    public ResponseEntity<?> add(@RequestBody(required = false) Monster monster) {
        Result<Monster> result = service.create(monster);
        if (result.getType() == ResultType.INVALID) {
            ValidationErrorResult validationErrorResult = new ValidationErrorResult();
            result.getMessages().forEach(validationErrorResult::addMessage);
            return new ResponseEntity<>(validationErrorResult, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
    }

    @PutMapping("/edit/monster/{monsterId}")
    public ResponseEntity<Void> update(@PathVariable int monsterId, @RequestBody Monster monster) {
        if (monsterId != monster.getMonsterId()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Result<Monster> result = service.update(monster);
        if (result.getType() == ResultType.INVALID) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else if (result.getType() == ResultType.NOT_FOUND) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @DeleteMapping("/delete/monster/{monsterId}")
    public ResponseEntity<Void> delete(@PathVariable int monsterId) {
        Result<Monster> result = service.delete(monsterId);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
*/
}
