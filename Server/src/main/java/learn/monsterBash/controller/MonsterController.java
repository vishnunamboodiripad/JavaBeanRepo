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
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api")
public class MonsterController {

    private final MonsterService service;


    public MonsterController(MonsterService service) {
        this.service = service;
    }

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
        Result<Monster> result = service.add(monster);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @PutMapping("/edit/monster/{monsterId}")
    public ResponseEntity<Object> update(@PathVariable int monsterId, @RequestBody Monster monster) {
        if (monsterId != monster.getMonsterId()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Result<Monster> result = service.update(monster);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ErrorResponse.build(result);

    }

    @DeleteMapping("/delete/monster/{monsterId}")
    public ResponseEntity<Void> delete(@PathVariable int monsterId) {
        Result<Monster> result = service.deleteById(monsterId);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
