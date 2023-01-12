package learn.monsterBash.data;

import learn.monsterBash.models.*;
import org.springframework.transaction.annotation.Transactional;
import learn.monsterBash.models.Battle;
import java.util.List;

public interface BattleRepo {
    Battle findWinner(Monster playerMonster,
                      Equipment playerEquipment, int userId);

    @Transactional
    public Battle findById(int battleId);

    List<Battle> findBattlesByUser(int user_id);

    Battle add(Battle battle);

    UserHistory findRecord(int userId);
}

