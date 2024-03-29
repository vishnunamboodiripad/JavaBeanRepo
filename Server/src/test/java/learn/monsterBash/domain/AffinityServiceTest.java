package learn.monsterBash.domain;

import learn.monsterBash.data.AffinityRepo;
import learn.monsterBash.data.WeatherRepo;
import learn.monsterBash.models.Affinity;
import learn.monsterBash.models.Weather;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@SpringBootTest
class AffinityServiceTest {

        @Autowired
        AffinityService service;

        @MockBean
        AffinityRepo repo;

        @Test
        void findByID() {
            Affinity expected = new Affinity();
            expected.setAffinityName("shock");
            expected.setAffinityId(5);
            when(repo.findById(1)).thenReturn(expected);
            Affinity actual = service.findById(1);
            assertEquals(expected, actual);
        }

        @Test
        void shouldNotFindById() {
            when(repo.findById(10)).thenReturn(null);
            Affinity actual = service.findById(10);
            assertEquals(null, actual);
        }

        @Test
        void shouldAdd() {
            Affinity test = new Affinity();
            test.setAffinityName("Test affinity");

            Affinity mock = new Affinity();
            test.setAffinityName("Test weather");

            when(repo.add(test)).thenReturn(mock);

            Result<Affinity> actual = service.add(test);
            assertEquals(ResultType.SUCCESS, actual.getType());
            assertEquals(mock, actual.getPayload());
        }

        @Test
        void shouldNotAddBlank(){
            Affinity test = new Affinity();
            test.setAffinityName("");
            Result<Affinity> actual = service.add(test);
            assertEquals(ResultType.INVALID, actual.getType());

        }
        @Test
        void shouldUpdate() {
            Affinity test = new Affinity();
            test.setAffinityName("Test affinity");
            test.setAffinityId(4);
            test.setAffinityId(1);

            when(repo.update(test)).thenReturn(true);

            Result<Affinity> actual = service.update(test);
            assertEquals(ResultType.SUCCESS, actual.getType());
        }

        @Test
        void shouldNotUpdate(){
            Affinity duplicate = new Affinity();
            duplicate.setAffinityName("Electric");
            duplicate.setAffinityId(1);
            Result<Affinity> result = service.update(duplicate);
            assertEquals(ResultType.INVALID, result.getType());

            Affinity blank = new Affinity();
            blank.setAffinityName("");
            blank.setAffinityId(1);
            Result<Affinity> result2 = service.update(blank);
            assertEquals(ResultType.INVALID, result2.getType());
        }

        @Test
        void shouldNotDeleteByIdWhenInUse() {

            Result<Affinity> actual = service.deleteById(1);
            assertEquals(ResultType.INVALID, actual.getType());
        }

    @Test
    void shouldDeleteById() {
            Result<Affinity> actual = service.deleteById(6);
            assertEquals(ResultType.SUCCESS, actual.getType());
    }
}