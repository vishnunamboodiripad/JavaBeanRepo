package learn.monsterBash.data;

import learn.monsterBash.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class BattleJdbcTemplateRepositoryTest {
    final static int NEXT_ID = 2;
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
        Battle test = repository.findById(1);
        assertNotNull(test);

    }

    @Test
    void findBattlesByUser() {
        List<Battle> test = repository.findBattlesByUser(1);
        assertEquals(test.size(), 1);
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
        battle.setPlayerWin(true);
        Battle actual = repository.add(battle);
        assertNotNull(actual);
        assertEquals(NEXT_ID, actual.getBattleId());
    }
}