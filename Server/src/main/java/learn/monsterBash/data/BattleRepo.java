package learn.monsterBash.data;

import learn.monsterBash.models.Battle;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BattleRepo {
    @Transactional
    public Battle findById(int battleId);

    List<Battle> findBattlesByUser(int user_id);
    public Battle add(Battle battle);
}

