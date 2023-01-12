package learn.monsterBash.domain;

import learn.monsterBash.data.BattleRepo;
import learn.monsterBash.models.*;
import org.apache.catalina.User;
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

    public Result<Battle> findWinner(Monster playerMonster, Equipment playerEquipment, int userId) {
        Result<Battle> result = new Result<>();
        if (playerMonster == null || playerEquipment == null) {
            result.addMessage("You must choose a monster and equipment to battle with!", ResultType.INVALID);
            return result;
        }

        Battle battle = new Battle();
        battle = repo.findWinner(playerMonster, playerEquipment, userId);
        result.setPayload(battle);
        return result;

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

    public Result<UserHistory> findRecord(int userId) {
        Result<UserHistory> result = new Result<>();
        if (userId <= 0) {
            result.addMessage("Something went wrong with your user information", ResultType.INVALID);
            return result;
        }

        UserHistory userHistory = repo.findRecord(userId);
        result.setPayload(userHistory);
        return result;
    }

}

