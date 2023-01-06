package learn.monsterBash.data;

import learn.monsterBash.models.Element;
import learn.monsterBash.models.Monster;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MonsterJdbcTemplateRepositoryTest {


    @Autowired
    MonsterJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup(){
        knownGoodState.set();
    }


    @Test
    void shouldFindAll() {
        List<Monster> Monsters = repository.findAll();
        assertNotNull(Monsters);
        assertTrue(Monsters.size() >= 7 && Monsters.size() <= 10);
    }

    @Test
    void shouldFindLen() {
        Monster len = repository.findById(1);
        assertEquals(1, len.getMonsterId());
        assertEquals("Lén", len.getMonsterName());
        assertEquals(47, len.getPower());
        assertEquals(Element.FIRE, len.getElement());
    }
    @Test
    void shouldAdd() {
        Monster monster = makeMonster();
        Monster actual = repository.add(monster);
        assertNotNull(actual);
        assertEquals(11, actual.getMonsterId());

        // null name
        monster = makeMonster();
        monster.setMonsterName(null);
        actual = repository.add(monster);
        assertNull(actual);
    }

    private Monster makeMonster() {
        Monster monster = new Monster();
        monster.setMonsterName("Test");
        monster.setMonsterImage("tinyurl.com/d10ttjbtestimg");
        monster.setPower(2);
        monster.setElement("earth");
        monster.setEquipmentId(0);
        return monster;
    }
}