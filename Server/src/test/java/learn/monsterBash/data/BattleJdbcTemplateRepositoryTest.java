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
    final static int NEXT_ID = 1;
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
        Battle battle = new Battle();
        battle.setWeatherId(1);
        battle.setLocationId(1);

        battle.setComputerEquipmentId(1);
        battle.setComputerMonsterId(1);

        battle.setPlayerEquipmentId(1);
        battle.setPlayerMonsterId(1);
        battle.setAppUserId(1);
        Battle actual = repository.add(battle);


        Battle test = repository.findById(1);

    }

    @Test
    void findBattlesByUser() {
        Battle battle = new Battle();
        battle.setWeatherId(1);
        battle.setLocationId(1);

        battle.setComputerEquipmentId(1);
        battle.setComputerMonsterId(1);

        battle.setPlayerEquipmentId(1);
        battle.setPlayerMonsterId(1);
        battle.setAppUserId(1);
        Battle actual = repository.add(battle);

        List<Battle> test = repository.findBattlesByUser(1);

        assertEquals(1, battle.getBattleId());
        assertEquals(1, battle.getWeatherId());
        assertEquals(1, battle.getLocationId());
        assertEquals(1, battle.getComputerEquipmentId());
        assertEquals(1, battle.getComputerMonsterId());
        assertEquals(1, battle.getPlayerEquipmentId());
        assertEquals(1, battle.getPlayerMonsterId());
        assertEquals(1, battle.getAppUserId());




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