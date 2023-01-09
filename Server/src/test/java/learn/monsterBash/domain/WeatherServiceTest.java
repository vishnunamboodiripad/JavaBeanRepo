package learn.monsterBash.domain;

import learn.monsterBash.data.WeatherRepo;
import learn.monsterBash.models.Weather;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class WeatherServiceTest {

    @Autowired
    WeatherService service;

    @MockBean
    WeatherRepo repo;

    @Test
    void findByID() {
        Weather expected = new Weather();
        expected.setWeatherName("Blizzard");
        expected.setWeatherImage("intel.com");
        expected.setAffinityId(5);
        when(repo.findById(1)).thenReturn(expected);
        Weather actual = service.findByID(1);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotFindById() {
        when(repo.findById(10)).thenReturn(null);
        Weather actual = service.findByID(10);
        assertEquals(null, actual);
    }

    @Test
    void shouldAdd() {
        Weather test = new Weather();
        test.setWeatherName("Test weather");
        test.setWeatherImage("Test image");

        Weather mock = new Weather();
        test.setWeatherName("Test weather");
        test.setWeatherImage("Test image");

        when(repo.add(test)).thenReturn(mock);

        Result<Weather> actual = service.add(test);
        assertEquals(ResultType.SUCCESS, actual.getType());
        assertEquals(mock, actual.getPayload());
    }

    @Test
    void shouldNotAddBlank(){
        Weather test = new Weather();
        test.setWeatherName("");
        test.setWeatherImage("Test image");
        Result<Weather> actual = service.add(test);
        assertEquals(ResultType.INVALID, actual.getType());

        Weather test2 = new Weather();
        test2.setWeatherName("test Name");
        test2.setWeatherImage("");
        Result<Weather> actual2 = service.add(test2);
        assertEquals(ResultType.INVALID, actual2.getType());

    }
    @Test
    void shouldUpdate() {
        Weather test = new Weather();
        test.setWeatherName("Test weather");
        test.setWeatherImage("Test image");
        test.setAffinityId(4);
        test.setWeatherId(1);

        when(repo.update(test)).thenReturn(true);

        Result<Weather> actual = service.update(test);
        assertEquals(ResultType.SUCCESS, actual.getType());
    }

    @Test
    void shouldNotUpdate(){
        Weather duplicate = new Weather();
        duplicate.setWeatherName("Blizzard");
        duplicate.setWeatherImage("intel.com");
        duplicate.setAffinityId(4);
        duplicate.setWeatherId(1);
        Result<Weather> result = service.update(duplicate);
        assertEquals(ResultType.INVALID, result.getType());

        Weather blank = new Weather();
        blank.setWeatherName("");
        blank.setWeatherImage("woosh");
        blank.setAffinityId(2);
        blank.setWeatherId(1);
        Result<Weather> result2 = service.update(blank);
        assertEquals(ResultType.INVALID, result2.getType());
    }

    @Test
    void deleteById() {
        Weather test = new Weather();
        test.setWeatherName("Blizzard");
        test.setWeatherImage("intel.com");
        test.setWeatherId(1);
        Result<Weather> actual = service.deleteById(1);
        assertEquals(ResultType.SUCCESS, actual.getType());
    }
}