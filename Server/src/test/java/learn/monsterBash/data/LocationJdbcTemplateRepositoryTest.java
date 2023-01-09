package learn.monsterBash.data;

import learn.monsterBash.models.Element;
import learn.monsterBash.models.Location;
import learn.monsterBash.models.Monster;
import learn.monsterBash.models.Weather;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LocationJdbcTemplateRepositoryTest {

    @Autowired
    LocationJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup(){
        knownGoodState.set();
    }


    @Test
    void shouldFindAll() {
        List<Location> locations = repository.findAll();
        assertNotNull(locations);
        assertTrue(locations.size() >= 7 && locations.size() <= 10);
    }

    @Test
    void shouldFindMinneapolis() {
        Location minneapolis = repository.findById(1);
        assertEquals(1, minneapolis.getLocationId());
        assertEquals("Minneapolis", minneapolis.getLocationName());
        assertEquals("drupal.org", minneapolis.getLocationImage());
    }
    @Test
    void findById() {
        Location minneapolis = repository.findById(1);
        assertNotNull(minneapolis);
        assertEquals(minneapolis.getLocationName(), "Minneapolis");
        assertEquals(minneapolis.getElementId(), 1);
    }

    @Test
    void add() {
        Location location = new Location();
        location.setLocationName("Tsunami");
        location.setLocationImage("woosh.org");
        location.setElementId(1);
        Location actual = repository.add(location);
        assertNotNull(actual);
        assertEquals(7, actual.getLocationId());
    }

    @Test
    void addNullElement() {
        Location location = new Location();
        location.setLocationName("Tsunami");
        location.setLocationImage("woosh.com");
        Location actual = repository.add(location);
        assertNotNull(actual);
        assertEquals(7, actual.getLocationId());
    }

    @Test
    void update() {
        Location location = new Location();
        location.setLocationName("Tsunami");
        location.setLocationImage("woosh.com");
        location.setElementId(2);
        location.setLocationId(3);
        assertTrue(repository.update(location));
        location.setLocationId(15);
        assertFalse(repository.update(location));
    }

    @Test
    void deleteById() {
        assertTrue(repository.deleteById(2));
        assertFalse(repository.deleteById(200));
    }
}