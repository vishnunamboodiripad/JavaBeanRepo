package learn.monsterBash.data;

import learn.monsterBash.models.Battle;
import learn.monsterBash.models.Weather;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BattleJdbcTemplateRepositoryTest {
    final static int NEXT_ID = 7;
    @Autowired
   BattleJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup(){
        knownGoodState.set();
    }

    @Test
    void findById() {
    }

    @Test
    void findBattlesByUser() {

    }

    @Test
    void add() {
        Battle battle = new Battle();
        battle.setWeatherId(1);
        battle.setLocationId(1);

        battle.setComputerEquipmentId(2);
        battle.setComputerMonsterId(2);

        battle.setPlayerEquipmentId(2);
        battle.setPlayerMonsterId(1);
        battle.setAppUserId(1);
        Battle actual = repository.add(battle);
        assertNotNull(actual);
        assertEquals(NEXT_ID, actual.getWeatherId());
    }
}