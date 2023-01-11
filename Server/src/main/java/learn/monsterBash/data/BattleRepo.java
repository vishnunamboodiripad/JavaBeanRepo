package learn.monsterBash.data;

import learn.monsterBash.models.AppUser;
import learn.monsterBash.models.Battle;
import learn.monsterBash.models.Equipment;
import learn.monsterBash.models.Monster;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BattleRepo {
    Battle findWinner(Monster playerMonster,
                      Equipment playerEquipment, int userId);

    @Transactional
    public Battle findById(int battleId);

    List<Battle> findBattlesByUser(int user_id);

    Battle add(Battle battle);
}

