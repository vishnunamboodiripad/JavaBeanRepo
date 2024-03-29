package learn.monsterBash.data.mappers;

import learn.monsterBash.models.Affinity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AffinityMapper implements RowMapper<Affinity> {
    @Override
    public Affinity mapRow(ResultSet resultSet, int i) throws SQLException {
        Affinity affinity = new Affinity();
        affinity.setAffinityId(resultSet.getInt("affinity_id"));
        affinity.setAffinityName(resultSet.getString("affinity_name"));
        affinity.setAffinityImage(resultSet.getString("affinity_image"));
        return affinity;
    }
}
