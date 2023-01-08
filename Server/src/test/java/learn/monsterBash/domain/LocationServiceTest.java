package learn.monsterBash.domain;

import learn.monsterBash.data.LocationRepo;
import learn.monsterBash.models.Location;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class LocationServiceTest {

    @Autowired
    LocationService service;

    @MockBean
    LocationRepo repo;

    @Test
    void findByID() {
        Location expected = new Location();
        expected.setLocationName("EGG");
        expected.setLocationImage("intel.com");
        expected.setElementId(5);
        when(repo.findById(1)).thenReturn(expected);
        Location actual = service.findById(1);
        assertEquals(expected, actual);
    }

    @Test
    void shouldAdd() {
        Location test = new Location();
        test.setLocationName("Test Location");
        test.setLocationImage("elgoog.com");

        Location mock = new Location();
        test.setLocationName("Test weather");
        test.setLocationImage("google.com");

        when(repo.add(test)).thenReturn(mock);

        Result<Location> actual = service.add(test);
        assertEquals(ResultType.SUCCESS, actual.getType());
        assertEquals(mock, actual.getPayload());
    }

    @Test
    void shouldNotAddBlank(){
        Location test = new Location();
        test.setLocationName("");
        test.setLocationImage("Test image");
        Result<Location> actual = service.add(test);
        assertEquals(ResultType.INVALID, actual.getType());

        Location test2 = new Location();
        test2.setLocationName("test Name");
        test2.setLocationImage("");
        Result<Location> actual2 = service.add(test2);
        assertEquals(ResultType.INVALID, actual2.getType());

    }
    @Test
    void shouldUpdate() {
        Location test = new Location();
        test.setLocationName("Test weather");
        test.setLocationImage("google.com");

        when(repo.update(test)).thenReturn(true);

        Result<Location> actual = service.update(test);
        assertEquals(ResultType.SUCCESS, actual.getType());
    }

    @Test
    void shouldNotUpdate(){
        Location duplicate = new Location();
        duplicate.setLocationName("Test");
        duplicate.setLocationImage("intel.com");
        Result<Location> result = service.update(duplicate);
        assertEquals(ResultType.INVALID, result.getType());

        Location blank = new Location();
        blank.setLocationName("");
        Result<Location> result2 = service.update(blank);
        assertEquals(ResultType.INVALID, result2.getType());
    }

    @Test
    void deleteById() {
        Location test = new Location();
        test.setLocationName("Minneapolis");
        test.setLocationImage("drupal.org");
        test.setLocationId(1);
        Result<Location> actual = service.deleteById(1);
        assertEquals(ResultType.SUCCESS, actual.getType());
    }
}