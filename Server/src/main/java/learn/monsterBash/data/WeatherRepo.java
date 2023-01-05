package learn.monsterBash.data;

import learn.monsterBash.models.Weather;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface WeatherRepo {
    List<Weather> findAll() throws DataAccessException;

    Weather findById(int weatherId) throws DataAccessException;

    Weather add(Weather weather) throws DataAccessException;

    boolean update(Weather weather) throws DataAccessException;

    boolean deleteById(int id) throws DataAccessException;



}
