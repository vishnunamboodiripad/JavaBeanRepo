package learn.monsterBash.data;

import learn.monsterBash.models.Element;
import learn.monsterBash.models.Monster;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class MonsterJdbcTemplateRepositoryTest {


    @Autowired
    MonsterJdbcTemplateRepository repository;


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
}
