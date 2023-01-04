package learn.monsterBash.data;

import learn.monsterBash.data.mappers.MonsterMapper;
import learn.monsterBash.models.Monster;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class MonsterJdbcTemplateRepository implements MonsterRepository {
    private final JdbcTemplate jdbcTemplate;

    public MonsterJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;}

    @Override
    public List<Monster> findAll() {
        final String sql = "select Monster_id, first_name, middle_name, last_name, dob, height_in_inches "
                + "from Monster limit 1000;";
        return jdbcTemplate.query(sql, new MonsterMapper());
    }

    @Override
    @Transactional
    public Monster findById(int MonsterId) {

        final String sql = "select Monster_id, first_name, middle_name, last_name, dob, height_in_inches "
                + "from Monster "
                + "where Monster_id = ?;";

        Monster monster = jdbcTemplate.query(sql, new MonsterMapper(), MonsterId).stream()
                .findFirst().orElse(null);

        return monster;
    }

}
