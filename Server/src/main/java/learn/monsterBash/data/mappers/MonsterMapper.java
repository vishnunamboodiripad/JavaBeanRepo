package learn.monsterBash.data.mappers;

import learn.monsterBash.models.Monster;
import org.springframework.jdbc.core.RowMapper;

import javax.swing.text.html.parser.Element;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.nio.file.Files.readString;


public class MonsterMapper implements RowMapper<Monster> {

    @Override
    public Monster mapRow(ResultSet resultSet, int i) throws SQLException {
        Monster Monster = new Monster();
        Monster.setMonsterId(resultSet.getInt("monster_id"));
        Monster.setMonsterName(resultSet.getString("monster_name"));
        Monster.setPower(resultSet.getInt("power"));
        Monster.setElement(resultSet.getString(Element.valueOf(input));
        Monster.setEquipmentId(resultSet.getInt("equipment_id"));
        Monster.setMonsterImage(resultSet.getString("monster_image"));
        return Monster;
    }
}