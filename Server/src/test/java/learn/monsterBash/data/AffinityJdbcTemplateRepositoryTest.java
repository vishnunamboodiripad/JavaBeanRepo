package learn.monsterBash.data;

import learn.monsterBash.models.Affinity;
import learn.monsterBash.models.Weather;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AffinityJdbcTemplateRepositoryTest {

    final static int NEXT_ID = 7;
    @Autowired
    AffinityJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup(){
        knownGoodState.set();
    }

    @Test
    void findAll() {
        List<Affinity> affinities = repository.findAll();
        assertNotNull(affinities);
        assertTrue(affinities.size() >= 5 && affinities.size() <= 9);
    }

    @Test
    void findById() {
        Affinity electric = repository.findById(1);
        assertNotNull(electric);
        assertEquals(electric.getAffinityName(), "Electric");
    }

    @Test
    void add() {
        Affinity affinity = new Affinity();
        affinity.setAffinityName("Electric");
        Affinity actual = repository.add(affinity);
        assertNotNull(actual);
        assertEquals(NEXT_ID, actual.getAffinityId());
    }
    @Test
    void update() {
        Affinity affinity = new Affinity();
        affinity.setAffinityName("Tsunami");;
        affinity.setAffinityId(3);
        assertTrue(repository.update(affinity));
        affinity.setAffinityId(15);
        assertFalse(repository.update(affinity));
    }

    @Test
    void deleteById() {
        assertTrue(repository.deleteById(2));
        assertFalse(repository.deleteById(200));
    }
}
