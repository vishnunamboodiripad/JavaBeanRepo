package learn.monsterBash.data;

import learn.monsterBash.data.mappers.EquipmentMapper;
import learn.monsterBash.data.mappers.MonsterMapper;
import learn.monsterBash.models.Equipment;
import learn.monsterBash.models.Monster;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
@Repository
public class EquipmentJdbcTemplateRepository implements EquipmentRepo {
    private final JdbcTemplate jdbcTemplate;

    public EquipmentJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;}

    @Override
    public List<Equipment> findAll() {
        final String sql = """
               select equipment_id, equipment_name, equipment_image, strength, affinity_id
               from equipment limit 1000;
               """;
        return jdbcTemplate.query(sql, new EquipmentMapper());
    }

    @Override
    @Transactional
    public Equipment findById(int equipmentId) {

        final String sql = """
               select equipment_id, equipment_name, equipment_image, strength, affinity_id
               from equipment
               where equipment_id = ?;
               """;

        Equipment equipment = jdbcTemplate.query(sql, new EquipmentMapper(), equipmentId).stream()
                .findFirst().orElse(null);

        return equipment;
    }

    @Override
    public Equipment add(Equipment equipment) {


        final String sql = """
               insert into equipment (equipment_name, equipment_image, strength, affinity_id)
               values (?,?,?,?);
                """;

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, equipment.getEquipmentName());
            ps.setString(2, equipment.getEquipmentImage());
            ps.setInt(3, equipment.getStrength());
            ps.setInt(4, equipment.getAffinityId());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        equipment.setEquipmentId(keyHolder.getKey().intValue());
        return equipment;
    }

    @Override
    public boolean update(Equipment equipment) {

        final String sql = """
                update equipment set
                equipment_name = ?,
                equipment_image = ?,
                strength = ?,
                affinity_id = ?
                where agent_id = ?
                """;

        return jdbcTemplate.update(sql,
                equipment.getEquipmentImage(),
                equipment.getEquipmentName(),
                equipment.getStrength(),
                equipment.getAffinityId(),
                equipment.getEquipmentId()) > 0;
    }

    @Override
    @Transactional
    public boolean deleteById(int equipmentId) {
        return jdbcTemplate.update("delete from equipment where equipment_id = ?;", equipmentId) > 0;
    }
}
