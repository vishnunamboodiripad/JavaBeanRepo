package learn.monsterBash.controller;

import learn.monsterBash.domain.BattleService;
import learn.monsterBash.domain.Result;
import learn.monsterBash.domain.ResultType;
import learn.monsterBash.models.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api")
public class BattleController {

    private final BattleService service;


    public BattleController(BattleService service) {
        this.service = service;
    }

    @GetMapping("/battle/{battleId}")
    public Battle findById(@PathVariable int battleId) {
        return service.findById(battleId);
    }

    @PostMapping("/battle/findWinner")
    public ResponseEntity<Object> findWinner(@RequestBody PlayerBattleChoice choice){
        Monster playerMonster = choice.getChosenMonster();
        Equipment playerEquipment = choice.getChosenEquipment();
        int userId = choice.getApp_user_id();

        Result<Battle> result = service.findWinner(playerMonster, playerEquipment, userId);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @PostMapping("/add/battle")
    public ResponseEntity<?> add(@RequestBody(required=false) Battle battle){
        Result<Battle> result = service.add(battle);
        if (result.isSuccess()){
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);

        }
        return ErrorResponse.build(result);
    }

    @GetMapping ("/findRecord/{userId}")
    public ResponseEntity<Object> findRecord(@PathVariable int userId) {
        Result<UserHistory> result = service.findRecord(userId);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }


}
