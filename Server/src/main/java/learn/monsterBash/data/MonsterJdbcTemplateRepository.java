package learn.monsterBash.data;

import learn.monsterBash.models.Monster;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
@Repository
public class MonsterJdbcTemplateRepository implements MonsterRepository{
    private final JdbcTemplate jdbcTemplate;

    public MonsterJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Monster add(Monster monster) {


        final String sql = """
                insert into monster (monster_name, monster_image, equipment_id, power, element_id) "
                 values (?,?,?,?,?);
                 """;

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, monster.getMonsterName());
            ps.setString(2, monster.getMonsterImage());
            ps.setInt(3, monster.getEquipmentId());
            ps.setInt(4, monster.getPower());
            ps.setString(5, monster.getElement());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        monster.setMonsterId(keyHolder.getKey().intValue());
        return monster;
    }
}