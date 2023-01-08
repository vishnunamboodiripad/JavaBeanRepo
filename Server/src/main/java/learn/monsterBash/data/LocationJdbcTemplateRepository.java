package learn.monsterBash.data;

import learn.monsterBash.data.mappers.LocationMapper;
import learn.monsterBash.models.Location;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

public class LocationJdbcTemplateRepository implements LocationRepo {
    private final JdbcTemplate jdbcTemplate;

    public LocationJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;}

    @Override
    public List<Location> findAll() {
        final String sql = """
               select location_id, location_name, location_image, element_id
               from location limit 1000;
               """;
        return jdbcTemplate.query(sql, new LocationMapper());
    }

    @Override
    @Transactional
    public Location findById(int locationId) {

        final String sql = """
               select location_id, location_name, location_image, element_id
               from location
               where location_id = ?;
               """;

        Location location = jdbcTemplate.query(sql, new LocationMapper(), locationId).stream()
                .findFirst().orElse(null);

        return location;
    }

    @Override
    public Location add(Location location) {


        final String sql = """
               insert into location (location_name, location_image, element_id)
               values (?,?,?,?);
                """;

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, location.getLocationName());
            ps.setString(2, location.getLocationImage());
            ps.setString(3, location.getLocationImage());
            ps.setInt(4, location.getElementId());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        location.setLocationId(keyHolder.getKey().intValue());
        return location;
    }

    @Override
    public boolean update(Location location) {

        final String sql = """
                update location set
                location_image = ?,
                location_name = ?,
                element_id = ?
                where location_id = ?
                """;

        return jdbcTemplate.update(sql,
                location.getLocationImage(),
                location.getLocationName(),
                location.getElementId(),
                location.getLocationId()) > 0;
    }

    @Override
    @Transactional
    public boolean deleteById(int locationId) {
        return jdbcTemplate.update("delete from Location where location_id = ?;", locationId) > 0;
    }
}
