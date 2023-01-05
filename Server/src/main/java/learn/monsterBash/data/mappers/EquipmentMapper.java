package learn.monsterBash.data.mappers;

import learn.monsterBash.models.Element;
import learn.monsterBash.models.Equipment;
import learn.monsterBash.models.Monster;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EquipmentMapper implements RowMapper<Equipment> {

    @Override
    public Equipment mapRow(ResultSet resultSet, int i) throws SQLException {
        Equipment equipment = new Equipment();
        equipment.setEquipmentId(resultSet.getInt("equipment_id"));
        equipment.setEquipmentName(resultSet.getString("equipment_name"));
        equipment.setStrength(resultSet.getInt("strength"));
        //equipment.setElement(resultSet.getString(Element.valueOf(input));
        equipment.setEquipmentImage(resultSet.getString("equipment_image"));
        equipment.setAffinityId(resultSet.getInt("affinity_id"));
        return equipment;
    }
}
