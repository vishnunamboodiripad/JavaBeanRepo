package learn.monsterBash.models;

public class Battle {

    private int battleId;

    private Monster playerMonster;

    private Monster computerMonster;

    private Equipment playerEquipment;

    private Equipment computerEquipment;

    private Weather weather;

    private Location location;

    private String result;

    private int AppUser;

    private Boolean playerWin;

    public int getBattleId() {
        return battleId;
    }

    public void setBattleId(int battleId) {
        this.battleId = battleId;
    }

    public Monster getPlayerMonster() {
        return playerMonster;
    }

    public void setPlayerMonster(Monster playerMonster) {
        this.playerMonster = playerMonster;
    }

    public Monster getComputerMonster() {
        return computerMonster;
    }

    public void setComputerMonster(Monster computerMonster) {
        this.computerMonster = computerMonster;
    }

    public Equipment getPlayerEquipment() {
        return playerEquipment;
    }

    public void setPlayerEquipment(Equipment playerEquipment) {
        this.playerEquipment = playerEquipment;
    }

    public Equipment getComputerEquipment() {
        return computerEquipment;
    }

    public void setComputerEquipment(Equipment computerEquipment) {
        this.computerEquipment = computerEquipment;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getAppUser() {
        return AppUser;
    }

    public void setAppUser(int appUser) {
        AppUser = appUser;
    }

    public Boolean getPlayerWin() {
        return playerWin;
    }

    public void setPlayerWin(Boolean playerWin) {
        this.playerWin = playerWin;
    }

    public Battle() {
    }



}
