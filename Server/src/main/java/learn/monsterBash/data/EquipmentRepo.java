package learn.monsterBash.data;

import learn.monsterBash.models.Equipment;
import learn.monsterBash.models.Monster;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EquipmentRepo {
    List<Equipment> findAll();

    Equipment add(Equipment equipment);

    @Transactional
    Equipment findById(int equipmentId);

    boolean update(Equipment equipment);

    @Transactional
    boolean deleteById(int equipmentId);

}