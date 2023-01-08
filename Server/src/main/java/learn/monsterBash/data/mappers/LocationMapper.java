package learn.monsterBash.data.mappers;

import learn.monsterBash.models.Equipment;
import learn.monsterBash.models.Location;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LocationMapper implements RowMapper<Location> {
    @Override
    public Location mapRow(ResultSet resultSet, int i) throws SQLException {
        Location location = new Location();
        location.setLocationId(resultSet.getInt("location_id"));
        location.setLocationName(resultSet.getString("location_name"));
        location.setLocationImage(resultSet.getString("location_image"));
        location.setElementId(resultSet.getInt("element_id"));
        return location;
    }
}
