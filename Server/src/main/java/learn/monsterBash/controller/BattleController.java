package learn.monsterBash.controller;

import learn.monsterBash.domain.BattleService;
import learn.monsterBash.domain.Result;
import learn.monsterBash.domain.ResultType;
import learn.monsterBash.models.Battle;
import learn.monsterBash.models.Equipment;
import learn.monsterBash.models.Monster;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api")
public class BattleController {
    /*
    private final BattleService service;


    public BattleController(BattleService service) {
        this.service = service;
    }

    @GetMapping("/battle/{battleId}")
    public Battle findById(@PathVariable int battleId) {
        return service.findById(battleId);
    }

    @GetMapping("/battle/findWinner")
    public Battle findWinner(@RequestBody (required=false)Monster playerMonster, Equipment playerEquipment, int appUserId){
        return service.findWinner(playerMonster, playerEquipment, appUserId);
    }

    @PostMapping("/battle/add")
    public ResponseEntity<?> add(@RequestBody(required=false) Battle battle){
        Result<Battle> result = service.add(battle);
        if (result.getType() == ResultType.INVALID){
            ValidationErrorResult validationErrorResult = new ValidationErrorResult();
            result.getMessages().forEach(validationErrorResult::addMessage);
            return new ResponseEntity<>(validationErrorResult, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
    }
*/

}
