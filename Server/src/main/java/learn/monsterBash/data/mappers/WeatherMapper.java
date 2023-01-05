package learn.monsterBash.data.mappers;

import learn.monsterBash.models.Weather;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WeatherMapper implements RowMapper<Weather> {
    @Override
    public Weather mapRow(ResultSet rs, int rowNum) throws SQLException {
        Weather weather = new Weather();
        weather.setWeatherId(rs.getInt("weather_id"));
        weather.setWeatherName(rs.getString("weather_name"));
        weather.setWeatherImage(rs.getString("weather_image"));
        weather.setAffinityId(rs.getInt("affinity_id"));

        return weather;
    }
}
