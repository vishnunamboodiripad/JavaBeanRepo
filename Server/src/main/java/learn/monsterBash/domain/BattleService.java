package learn.monsterBash.domain;

import learn.monsterBash.data.BattleRepo;
import learn.monsterBash.models.*;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BattleService {

    private final BattleRepo repo;

    public BattleService(BattleRepo repo) {
        this.repo = repo;
    }

    public Battle findById(int battleId) {
        return repo.findById(battleId);
    }

    public Battle findWinner(Monster playerMonster, Equipment playerEquipment, AppUser user) {
        return repo.findWinner(playerMonster, playerEquipment, user);
    }

    public Result<Battle> add(Battle battle) {
        Result<Battle> result = new Result<>();
        if (battle == null) {
            result.addMessage("Battle cannot be null", ResultType.INVALID);
            return result;
        }

        if (battle.getBattleId() != 0) {
            result.addMessage("battleId should not be set by user", ResultType.INVALID);
            return result;
        }
        battle = repo.add(battle);

        result.setPayload(battle);
        return result;
    }

}

