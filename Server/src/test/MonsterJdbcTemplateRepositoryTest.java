package learn.monsterBash.data;

import learn.monsterBash.models.Monster;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class MonsterJdbcTemplateRepositoryTest {

    final static int NEXT_ID = 9;

    @Autowired
    MonsterJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindAll() {
        List<Monster> monsters = repository.findAll();
        assertNotNull(monsters);
        assertTrue(monsters.size() >= 10);
    }

    @Test
    void shouldFindLen() {
        Monster len = repository.findById(1);
        assertEquals(1, len.getMonsterId());
        assertEquals("Lén", len.getMonsterName());
        assertEquals(47, hazel.getPower());
        assertEquals("fire", len.getElement());
    }

    @Test
    void shouldAdd() {
        Monster monster = makeMonster();
        Monster actual = repository.add(monster);
        assertNotNull(actual);
        assertEquals(NEXT_ID, actual.getMonsterId());

        // null name
        monster = makeMonster();
        monster.setMonsterName(null);
        actual = repository.add(monster);
        assertFalse(actual);
    }

    @Test
    void shouldUpdate() {
        Monster monster = makeMonster();
        monster.setMonsterId(11);
        assertTrue(repository.update(monster));
        monster.setMonsterId(13);
        assertFalse(repository.update(monster));
    }

    @Test
    void shouldDelete() {
        assertTrue(repository.deleteById(11));
        repository.deleteById(11);
        assertFalse(repository.deleteById(11));
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