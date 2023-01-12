package learn.monsterBash.data;

import learn.monsterBash.data.mappers.BattleMapper;
import learn.monsterBash.models.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class BattleJdbcTemplateRepository implements BattleRepo {

    private JdbcTemplate jdbcTemplate;

    private final WeatherRepo weatherRepo;
    private final LocationRepo locationRepo;
    private final MonsterRepository monsterRepo;
    private final EquipmentRepo equipmentRepo;

    public BattleJdbcTemplateRepository(WeatherRepo weatherRepo, LocationRepo locationRepo, MonsterRepository monsterRepo, EquipmentRepo equipmentRepo, JdbcTemplate jdbcTemplate) {
        this.weatherRepo = weatherRepo;
        this.locationRepo = locationRepo;
        this.monsterRepo = monsterRepo;
        this.equipmentRepo = equipmentRepo;
        this.jdbcTemplate = jdbcTemplate;
    }

    public int calcTotalMonsterPower(Location location, Monster monster, Equipment equipment, Weather weather) {
        monster.setPower(setElementEffects(location, monster));
        equipment.setStrength(setAffinityEffects(weather, equipment));
        int totalMonsterPower = (monster.getPower() + equipment.getStrength());
        return totalMonsterPower;
    }

    public Weather getWeather() {
        int weatherId = (int) (Math.random() * (weatherRepo.findAll().size()) + 1);
        Weather battleWeather = weatherRepo.findById(weatherId);
        return battleWeather;
    }

    public Monster getComputerMonster() {
        int monsterId = (int) (Math.random() * (monsterRepo.findAll().size()) + 1);
        Monster computerMonster = monsterRepo.findById(monsterId);
        return computerMonster;
    }

    public Equipment getComputerEquipment() {
        int equipmentId = (int) (Math.random() * (equipmentRepo.findAll().size()) + 1);
        Equipment computerEquipment = equipmentRepo.findById(equipmentId);
        return computerEquipment;
    }

    public Location getLocation() {
        int locationId = (int) (Math.random() * (locationRepo.findAll().size()) + 1);
        Location battleLocation = locationRepo.findById(locationId);
        return battleLocation;
    }


    public int setElementEffects(Location location, Monster monster) {
        int monsterPower = 0;
        if (location.getElementId() == monster.getElementId()) {
            monsterPower = (monster.getPower() + 15);
        } else monsterPower = monster.getPower();

        return monsterPower;
    }

    public int setAffinityEffects(Weather weather, Equipment equipment) {
        int equipmentPower = 0;
        if (equipment.getAffinityId() == weather.getAffinityId()) {
            equipmentPower = (equipment.getStrength() + 15);
        } else equipmentPower = equipment.getStrength();

        return equipmentPower;
    }

    @Override
    public Battle findWinner(Monster playerMonster,
                             Equipment playerEquipment, int userId) {

        Battle battle = new Battle();
        Weather battleWeather = getWeather();
        Location battleLocation = getLocation();
        Monster computerMonster = getComputerMonster();
        Equipment computerEquipment = getComputerEquipment();

        battle.setPlayerTotalPower(calcTotalMonsterPower(battleLocation, computerMonster, computerEquipment, battleWeather));
        battle.setComputerTotalPower(calcTotalMonsterPower(battleLocation, playerMonster, playerEquipment, battleWeather));

        if (battle.getPlayerTotalPower() > battle.getComputerTotalPower())
            battle.setPlayerWin(true);
        else battle.setPlayerWin(false);

        battle.setWeatherId(battleWeather.getWeatherId());
        battle.setLocationId(battleLocation.getLocationId());
        battle.setComputerMonsterId(computerMonster.getMonsterId());
        battle.setComputerEquipmentId(computerEquipment.getEquipmentId());
        battle.setPlayerMonsterId(playerMonster.getMonsterId());
        battle.setPlayerEquipmentId(playerEquipment.getEquipmentId());
        battle.setAppUserId(userId);

        return battle;
    }

    @Transactional
    @Override
    public Battle findById(int battleId){
        final String sql = """
                select battle_id, player_monster_id, computer_monster_id, player_equipment_id, computer_equipment_id, weather_id, location_id, app_user_id, player_win
                from battle
                where battle_id = ?;
                """;

        Battle battle = jdbcTemplate.query(sql, new BattleMapper(), battleId).stream()
                .findFirst().orElse(null);

        return battle;
    }

    @Override
    public List<Battle> findBattlesByUser(int user_id) {
        final String sql = """
                select battle_id, player_monster_id, computer_monster_id, player_equipment_id, computer_equipment_id, weather_id, location_id, app_user_id, player_win
                from battle
                where app_user_id = ?;
                """;

        List<Battle> battles = jdbcTemplate.query(sql, new BattleMapper(), user_id);
        return battles;
    }

    @Override
    public Battle add(Battle battle){
        final String sql = """
                insert into battle (player_monster_id, computer_monster_id, player_equipment_id, computer_equipment_id, weather_id, location_id, app_user_id, player_win)
                values (?, ?, ?, ?, ?, ?, ?, ?);
                """;
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, battle.getPlayerMonsterId());
            ps.setInt(2, battle.getComputerMonsterId());
            ps.setInt(3, battle.getPlayerEquipmentId());
            ps.setInt(4, battle.getComputerEquipmentId());
            ps.setInt(5, battle.getWeatherId());
            ps.setInt(6, battle.getLocationId());
            ps.setInt(7, battle.getAppUserId());
            ps.setBoolean(8, battle.getPlayerWin());

            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        battle.setBattleId(keyHolder.getKey().intValue());
        return battle;
    }
    @Override
    public UserHistory findRecord(int userId){
        UserHistory userHistory = new UserHistory();
        List<Battle> battles = findBattlesByUser(userId);

        long winTotal = battles.stream().filter((b -> b.getPlayerWin() == true)).count();
        long lossTotal = battles.stream().filter((b -> b.getPlayerWin() == false)).count();

        userHistory.setUserLosses(lossTotal);
        userHistory.setUserWins(winTotal);

        return userHistory;
    }

}





