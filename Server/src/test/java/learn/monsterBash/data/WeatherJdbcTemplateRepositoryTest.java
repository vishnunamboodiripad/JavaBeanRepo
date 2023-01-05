package learn.monsterBash.data;

import learn.monsterBash.models.Weather;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WeatherJdbcTemplateRepositoryTest {
    final static int NEXT_ID = 7;
    @Autowired
    WeatherJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup(){
        knownGoodState.set();
    }

    @Test
    void findAll() {
        List<Weather> weathers = repository.findAll();
        assertNotNull(weathers);
        assertTrue(weathers.size() >= 5 && weathers.size() <= 9);
    }

    @Test
    void findById() {
        Weather blizzard = repository.findById(1);
        assertNotNull(blizzard);
        assertEquals(blizzard.getWeatherName(), "Blizzard");
        assertEquals(blizzard.getAffinityId(), 5);
    }

    @Test
    void add() {
        Weather weather = new Weather();
        weather.setWeatherName("Tsunami");
        weather.setWeatherImage("woosh");
        weather.setAffinityId(2);
        Weather actual = repository.add(weather);
        assertNotNull(actual);
        assertEquals(NEXT_ID, actual.getWeatherId());
    }

    @Test
    void addNullAffinity() {
        Weather weather = new Weather();
        weather.setWeatherName("Tsunami");
        weather.setWeatherImage("woosh");
        Weather actual = repository.add(weather);
        assertNotNull(actual);
        assertEquals(NEXT_ID, actual.getWeatherId());
    }

    @Test
    void update() {
        Weather weather = new Weather();
        weather.setWeatherName("Tsunami");
        weather.setWeatherImage("woosh");
        weather.setAffinityId(2);
        weather.setWeatherId(3);
        assertTrue(repository.update(weather));
        weather.setWeatherId(15);
        assertFalse(repository.update(weather));
    }

    @Test
    void deleteById() {
        assertTrue(repository.deleteById(2));
        assertFalse(repository.deleteById(200));
    }
}