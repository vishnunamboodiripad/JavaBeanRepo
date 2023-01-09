package learn.monsterBash.data.mappers;

import learn.monsterBash.models.*;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BattleMapper implements RowMapper<Battle> {
    @Override
    public Battle mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Battle battle = new Battle();

        WeatherMapper weatherMapper = new WeatherMapper();
        battle.setWeather(weatherMapper.mapRow(resultSet, rowNum));

        LocationMapper locationMapper = new LocationMapper();
        battle.setLocation(locationMapper.mapRow(resultSet, rowNum));

        battle.setPlayerWin(resultSet.getBoolean("player_win"));
        battle.setAppUserId(resultSet.getInt("app_user_id"));


        Monster playerMonster = new Monster();
        playerMonster.setMonsterId(resultSet.getInt("monster_id"));
        playerMonster.setMonsterName(resultSet.getString("monster_name"));
        playerMonster.setPower(resultSet.getInt("power"));
        playerMonster.setElementId(resultSet.getInt("element_id"));
        playerMonster.setMonsterImage(resultSet.getString("monster_image"));

        battle.setPlayerMonster(playerMonster);

        Monster computerMonster = new Monster();
        computerMonster.setMonsterId(resultSet.getInt("monster_id"));
        computerMonster.setMonsterName(resultSet.getString("monster_name"));
        computerMonster.setPower(resultSet.getInt("power"));
        computerMonster.setElementId(resultSet.getInt("element_id"));
        computerMonster.setMonsterImage(resultSet.getString("monster_image"));

        battle.setComputerMonster(computerMonster);

        Equipment playerEquipment = new Equipment();
        playerEquipment.setEquipmentId(resultSet.getInt("equipment_id"));
        playerEquipment.setEquipmentName(resultSet.getString("equipment_name"));
        playerEquipment.setStrength(resultSet.getInt("strength"));
        playerEquipment.setEquipmentImage(resultSet.getString("equipment_image"));
        playerEquipment.setAffinityId(resultSet.getInt("affinity_id"));

        battle.setPlayerEquipment(playerEquipment);

        Equipment computerEquipment = new Equipment();
        computerEquipment.setEquipmentId(resultSet.getInt("equipment_id"));
        computerEquipment.setEquipmentName(resultSet.getString("equipment_name"));
        computerEquipment.setStrength(resultSet.getInt("strength"));
        computerEquipment.setEquipmentImage(resultSet.getString("equipment_image"));
        computerEquipment.setAffinityId(resultSet.getInt("affinity_id"));

        battle.setComputerEquipment(computerEquipment);


        return battle;
    }


}
