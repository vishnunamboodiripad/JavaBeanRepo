package learn.monsterBash.data;

import learn.monsterBash.data.mappers.MonsterMapper;
import learn.monsterBash.data.mappers.WeatherMapper;
import learn.monsterBash.models.Monster;
import learn.monsterBash.models.Weather;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class WeatherJdbcTemplateRepository implements WeatherRepo {
    private final JdbcTemplate jdbcTemplate;

    public WeatherJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Weather> findAll() {
        final String sql = "select weather_id, weather_name, weather_image, affinity_id\n" +
                "from weather\n" +
                "order by weather_id;";

        return jdbcTemplate.query(sql, new WeatherMapper());
    }

    @Transactional
    public Weather findById(int weatherId) {
        final String sql = "select weather_id, weather_name, weather_image, affinity_id\n" +
                "from weather\n" +
                "where weather_id = ?;";

        Weather weather = jdbcTemplate.query(sql, new WeatherMapper(), weatherId).stream()
                .findFirst().orElse(null);

        return weather;
    }

    public Weather add(Weather weather) {


        final String sql = """
                insert into weather (weather_name, weather_image, affinity_id) "
                 values (?,?,?);
                 """;

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, weather.getWeatherName());
            ps.setString(2, weather.getWeatherImage());
            ps.setInt(3, weather.getWeatherId());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        weather.setWeatherId(keyHolder.getKey().intValue());
        return weather;
    }

    public boolean update(Weather weather){
        final String sql = "update weather set " +
                "weather_name = ?" +
                "weather_image = ?" +
                "affinity_id = ?;";

        int rowsUpdated = jdbcTemplate.update(sql, weather.getWeatherName(),
                weather.getWeatherImage(),
                weather.getAffinityId());

        return rowsUpdated > 0;
    }
    public boolean deleteById(int id) {
        final String sql = "delete from weather where weather_id;";
        return jdbcTemplate.update(sql, id) > 0;
    }
}
