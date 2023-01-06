package learn.monsterBash.data;

import learn.monsterBash.models.Monster;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MonsterJdbcTemplateRepositoryTest {

    final static int NEXT_ID = 11;

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
        assertEquals(47, len.getPower());
    }

    @Test
    void shouldAdd() {
        Monster monster = makeMonster();
        Monster actual = repository.add(monster);
        assertNotNull(actual);
        assertEquals(NEXT_ID, actual.getMonsterId());}

    @Test
    void shouldUpdate() {
        Monster monster = repository.findById(2);
        monster.setMonsterName("Tester");
        assertTrue(repository.update(monster));
        monster.setMonsterId(25);
        assertFalse(repository.update(monster));
    }

    @Test
    void shouldDelete() {
        assertTrue(repository.deleteById(2));
        repository.deleteById(2);
        assertFalse(repository.deleteById(11));
    }

    private Monster makeMonster() {
        Monster monster = new Monster();
        monster.setMonsterName("Test");
        monster.setMonsterImage("tinyurl.com/d10ttjbtestimg");
        monster.setPower(2);
        monster.setElement("earth");
        return monster;
    }
}