package learn.monsterBash.data.mappers;

import learn.monsterBash.models.Affinity;
import learn.monsterBash.models.Equipment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AffinityMapper implements RowMapper<Affinity> {
    @Override
    public Affinity mapRow(ResultSet resultSet, int i) throws SQLException {
        Affinity affinity = new Affinity();
        affinity.setAffinityId(resultSet.getInt("affinity_id"));
        affinity.setAffinityName(resultSet.getString("affinity_name"));
        return affinity;
    }
}
