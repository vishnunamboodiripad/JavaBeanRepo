package learn.monsterBash.data;

import learn.monsterBash.models.Element;
import learn.monsterBash.models.Equipment;
import learn.monsterBash.models.Monster;
import learn.monsterBash.models.Weather;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class EquipmentJdbcTemplateRepositoryTest {


    @Autowired
    EquipmentJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup(){
        knownGoodState.set();
    }

    @Test
    void findAll() {
        List<Equipment> equipments = repository.findAll();
        assertNotNull(equipments);
        assertTrue(equipments.size() > 5 && equipments.size() < 9);
    }
    @Test
    void shouldFindStaffOfSogginess() {
        Equipment staff = repository.findById(2);
        assertEquals(2, staff.getEquipmentId());
        assertEquals("Staff of Soggyness", staff.getEquipmentName());
        assertEquals(60, staff.getStrength());
    }

    @Test
    void shouldAdd() {
        Equipment equipment = makeEquipment();
        Equipment actual = repository.add(equipment);
        assertNotNull(actual);
        assertEquals(7, actual.getEquipmentId());
    }


    @Test
    void shouldUpdate() {
        Equipment equipment = makeEquipment();
        equipment.setEquipmentId(3);
        assertTrue(repository.update(equipment));
        equipment.setEquipmentId(10);
        assertFalse(repository.update(equipment));
    }

    @Test
    void deleteById() {
        assertTrue(repository.deleteById(2));
        assertFalse(repository.deleteById(2));

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