package learn.monsterBash.domain;

import learn.monsterBash.data.MonsterJdbcTemplateRepository;
import learn.monsterBash.models.Monster;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class MonsterServiceTest {

    @Autowired
    MonsterService service;

    @MockBean
    MonsterJdbcTemplateRepository repo;

    @Test
    void findById() {
        Monster expected = makeMonster();
        when(repo.findById(1)).thenReturn(expected);
        Monster actual = service.findById(1);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotFindById() {
        when(repo.findById(1)).thenReturn(null);
        Monster actual = service.findById(1);
        assertEquals(null, actual);
    }

    @Test
    void add() {
        Monster expected = makeMonster();
        Monster mock = makeMonster();

        when(repo.add(expected)).thenReturn(mock);

        Result<Monster> actual = service.add(expected);
        assertEquals(ResultType.SUCCESS, actual.getType());
        assertEquals(mock, actual.getPayload());
    }

    @Test
    void update() {
        Monster test = makeMonster();

        when(repo.update(test)).thenReturn(true);

        Result<Monster> actual = service.update(test);
        assertEquals(ResultType.SUCCESS, actual.getType());
    }

    @Test
    void deleteById() {
        Monster test = makeMonster();
        Result<Monster> actual = service.deleteById(1);
        assertEquals(ResultType.SUCCESS, actual.getType());
    }

    private Monster makeMonster() {
        Monster monster = new Monster();
        monster.setMonsterName("Test");
        monster.setMonsterImage("tinyurl.com/d10ttjbtestimg");
        monster.setPower(2);
        monster.setElementId(1);
        return monster;
    }
}