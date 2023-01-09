package learn.monsterBash.data.mappers;

import learn.monsterBash.models.Element;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ElementMapper implements RowMapper<Element> {
    @Override
    public Element mapRow(ResultSet resultSet, int i) throws SQLException {
        Element element = new Element();
        element.setElementId(resultSet.getInt("element_id"));
        element.setElementName(resultSet.getString("element_name"));
        return element;
    }
}
