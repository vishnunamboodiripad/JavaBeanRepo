package learn.monsterBash.domain;

import learn.monsterBash.data.EquipmentJdbcTemplateRepository;
import learn.monsterBash.data.WeatherRepo;
import learn.monsterBash.models.Equipment;
import learn.monsterBash.models.Weather;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class EquipmentServiceTest {
    @Autowired
    EquipmentService service;

    @MockBean
    EquipmentJdbcTemplateRepository repo;

    @Test
    void shouldFindTest() {
        // pass-through test, probably not useful
        Equipment expected = makeEquipment();
        when(repo.findById(1)).thenReturn(expected);
        Equipment actual = service.findById(1);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddWhenInvalid() {
        Equipment equipment = makeEquipment();
        Result<Equipment> result = service.add(equipment);
        assertEquals(ResultType.INVALID, result.getType());

        equipment.setEquipmentId(0);
        equipment.setEquipmentName(null);
        result = service.add(equipment);
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldNotAddWhenValid() {
        Equipment expected = makeEquipment();
        Equipment other = makeEquipment();
        other.setEquipmentId(0);

        when(repo.add(other)).thenReturn(expected);
        Result<Equipment> result = service.add(other);
        assertEquals(ResultType.SUCCESS, result.getType());

        assertEquals(expected, result.getPayload());
    }

    private Equipment makeEquipment() {
        Equipment equipment = new Equipment();
        equipment.setEquipmentName("Test");
        equipment.setEquipmentId(1);
        equipment.setEquipmentImage("google.com");
        equipment.setStrength(10);
        equipment.setAffinityId(5);

        return equipment;
    }

}