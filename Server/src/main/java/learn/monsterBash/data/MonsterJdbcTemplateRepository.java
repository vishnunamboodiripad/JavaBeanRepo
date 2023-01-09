package learn.monsterBash.data;
import learn.monsterBash.data.mappers.MonsterMapper;
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
public class MonsterJdbcTemplateRepository implements MonsterRepository {
    private final JdbcTemplate jdbcTemplate;

    public MonsterJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;}

    @Override
    public List<Monster> findAll() {
        final String sql = "select monster_id, monster_name, monster_image, power, element, equipment_id "
                + "from Monster limit 1000;";
        return jdbcTemplate.query(sql, new MonsterMapper());
    }

    @Override
    @Transactional
    public Monster findById(int MonsterId) {

        final String sql = "select monster_id, monster_name, monster_image, power, element, equipment_id "
                + "from Monster "
                + "where monster_id = ?;";

        Monster monster = jdbcTemplate.query(sql, new MonsterMapper(), MonsterId).stream()
                .findFirst().orElse(null);

        return monster;
    }

    @Override
    public Monster add(Monster monster) {


        final String sql = """
                insert into monster (monster_name, monster_image, power, element)
                 values (?,?,?,?);
                 """;

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, monster.getMonsterName());
            ps.setString(2, monster.getMonsterImage());
            ps.setInt(3, monster.getPower());
            ps.setString(4, monster.getElement());
            ps.setInt(5, monster.getEquipmentId());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        monster.setMonsterId(keyHolder.getKey().intValue());
        return monster;
    }

    @Override
    public boolean update(Monster monster) {

        final String sql = "update monster set "
                + "monster_name = ?, "
                + "monster_image = ?, "
                + "power = ?, "
                + "element = ? "
                + "where monster_id = ?;";

        return jdbcTemplate.update(sql,
                monster.getMonsterName(),
                monster.getMonsterImage(),
                monster.getPower(),
                monster.getElement(),
                monster.getMonsterId()) > 0;
    }

    @Override
    @Transactional
    public boolean deleteById(int monsterId) {
        return jdbcTemplate.update("delete from monster where monster_id = ?;", monsterId) > 0;
    }

}