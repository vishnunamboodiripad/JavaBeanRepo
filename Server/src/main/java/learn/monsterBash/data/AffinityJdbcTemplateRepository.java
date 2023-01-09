package learn.monsterBash.data;

import learn.monsterBash.data.mappers.AffinityMapper;
import learn.monsterBash.data.mappers.EquipmentMapper;
import learn.monsterBash.data.mappers.WeatherMapper;
import learn.monsterBash.models.Affinity;
import learn.monsterBash.models.Equipment;
import learn.monsterBash.models.Weather;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

public class AffinityJdbcTemplateRepository implements AffinityRepo {
    private final JdbcTemplate jdbcTemplate;

    public AffinityJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;}
    @Override
    public List<Affinity> findAll() {

            final String sql = """
                    select affinity_id, affinity_name
                    from affinity
                    order by affinity_id;
                    """;

            return jdbcTemplate.query(sql, new AffinityMapper());

    }

    @Override
    public Affinity add(Affinity affinity) {

        final String sql = """
                insert into affinity (affinity_name)
                values (?);
                 """;

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, affinity.getAffinityName());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        affinity.setAffinityId(keyHolder.getKey().intValue());
        return affinity;
    }

    @Override
    @Transactional
    public Affinity findById(int affinityId) {

        final String sql = """
               select affinity_id, affinity_name
               from affinity
               where affinity_id = ?;
               """;

        Affinity affinity = jdbcTemplate.query(sql, new AffinityMapper(), affinityId).stream()
                .findFirst().orElse(null);

        return affinity;
    }

    @Override
    public boolean update(Affinity affinity) {
        final String sql = """
                update affinity set
                affinity_name = ?,
                where affinity_id = ?
                """;

        return jdbcTemplate.update(sql,
                affinity.getAffinityName(),
                affinity.getAffinityId()) > 0;
    }


    @Override
    public boolean deleteById(int affinityId) {
            return jdbcTemplate.update("delete from equipment where affinity_id = ?;", affinityId) > 0;
        }
    }

