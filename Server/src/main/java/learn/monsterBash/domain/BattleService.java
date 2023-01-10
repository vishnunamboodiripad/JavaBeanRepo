package learn.monsterBash.domain;

import learn.monsterBash.data.BattleRepo;
import learn.monsterBash.models.Battle;
import learn.monsterBash.models.Element;
import learn.monsterBash.models.Monster;
import learn.monsterBash.models.Weather;

import java.util.List;

public class BattleService {

    private final BattleRepo repo;

    public BattleService(BattleRepo repo) {
        this.repo = repo;
    }

    public Battle findById(int battleId) {
        return repo.findById(battleId);
    }

    public Result<Battle> add(Battle battle) {
        Result<Battle> result = null;
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

