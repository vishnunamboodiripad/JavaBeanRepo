package learn.monsterBash.data;

import learn.monsterBash.data.mappers.BattleMapper;
import learn.monsterBash.data.mappers.WeatherMapper;
import learn.monsterBash.models.*;

import java.util.Random;

public class BattleJdbcTemplateRepository implements BattleRepo {
    private Location location;
    private final Random random = new Random();
    private Monster playerMonster;
    private Monster computerMonster;
    private Equipment playerEquipment;
    private Equipment computerEquipment;
    private Weather weather;

    private final WeatherRepo weatherRepo;
    private final LocationRepo locationRepo;
    private final MonsterRepository monsterRepo;
    private final EquipmentRepo equipmentRepo;


    public BattleJdbcTemplateRepository(WeatherRepo weatherRepo, LocationRepo locationRepo, MonsterRepository monsterRepo, EquipmentRepo equipmentRepo) {
        this.weatherRepo = weatherRepo;
        this.locationRepo = locationRepo;
        this.monsterRepo = monsterRepo;
        this.equipmentRepo = equipmentRepo;
    }

    private int calcTotalMonsterPower(Location location, Monster monster, Equipment equipment, Weather weather) {
        monster.setPower(setElementEffects(location, monster));
        equipment.setStrength(setAffinityEffects(weather, equipment));
        int totalMonsterPower = (monster.getPower() + equipment.getStrength());
        return totalMonsterPower;
    }

    private Weather getWeather() {
        int weatherId = (int) (Math.random() * (weatherRepo.findAll().size() + 1));
        Weather battleWeather = weatherRepo.findById(weatherId);
        return battleWeather;
    }

    private Monster getComputerMonster() {
        int monsterId = (int) (Math.random() * (monsterRepo.findAll().size() + 1));
        Monster computerMonster = monsterRepo.findById(monsterId);
        return computerMonster;
    }

    private Equipment getComputerEquipment() {
        int equipmentId = (int) (Math.random() * (equipmentRepo.findAll().size() + 1));
        Equipment computerEquipment = equipmentRepo.findById(equipmentId);
        return computerEquipment;
    }

    private Location getLocation() {
        int locationId = (int) (Math.random() * (locationRepo.findAll().size() + 1));
        Location battleLocation = locationRepo.findById(locationId);
        return battleLocation;
    }


    private int setElementEffects(Location location, Monster monster) {
        int monsterPower = 0;
        if (location.getElementName().equals(monster.getElementName())) {
            monsterPower = (monster.getPower() + 15);
        } else monsterPower = monster.getPower();

        return monsterPower;
    }

    private int setAffinityEffects(Weather weather, Equipment equipment) {
        int equipmentPower = 0;
        if (equipment.getAffinityName().equals(weather.getAffinityName())) {
            equipmentPower = (equipment.getStrength() + 15);
        } else equipmentPower = equipment.getStrength();

        return equipmentPower;
    }


    public Battle findWinner(Monster playerMonster,
                             Equipment playerEquipment, int appUserId) {

        Battle battle = new Battle();
        Weather battleWeather = getWeather();
        Location battleLocation = getLocation();
        Monster computerMonster = getComputerMonster();
        Equipment computerEquipment = getComputerEquipment();

        battle.setWeather(battleWeather);
        battle.setLocation(battleLocation);
        battle.setComputerMonster(computerMonster);
        battle.setComputerEquipment(computerEquipment);
        battle.setPlayerMonster(playerMonster);
        battle.setPlayerEquipment(playerEquipment);
        battle.setAppUserId(appUserId);

        computerMonster.setPower(calcTotalMonsterPower(battleLocation, computerMonster, computerEquipment, battleWeather));
        playerMonster.setPower(calcTotalMonsterPower(battleLocation, playerMonster, playerEquipment, battleWeather));

        if (computerMonster.getPower() > playerMonster.getPower())
            battle.setPlayerWin(true);
        else battle.setPlayerWin(false);

        return battle;
    }

    public Battle findbyId(int battleId){
        final String sql = """
                select battle_id, app_user_id, player_win
                from battle
                where battle_id = ?;
                """;

        Battle battle = jdbcTemplate.query(sql, new BattleMapper(), battleId).stream()
                .findFirst().orElse(null);

        return weather;

    }

}





