package learn.monsterBash.models;

public class Battle {

    private int battleId;

    private int playerMonsterId; //Monster playerMonster;

    private int computerMonsterId; //Monster computerMonster

    private int playerEquipmentId; // Equipment playerEquipment

    private int computerEquipmentId; // Equipment computerEquipment

    private int weatherId; //Weather weather

    private int locationId; //Location location

    private int appUserId;

    private Boolean playerWin;

    public int getBattleId() {
        return battleId;
    }

    public void setBattleId(int battleId) {
        this.battleId = battleId;
    }



    public int getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(int appUser) {appUserId = appUserId;
    }

    public Boolean getPlayerWin() {
        return playerWin;
    }

    public void setPlayerWin(Boolean playerWin) {
        this.playerWin = playerWin;
    }

    public Battle() {
    }
    public int getPlayerMonsterId() {
        return playerMonsterId;
    }

    public void setPlayerMonsterId(int playerMonster) {
        this.playerMonsterId = playerMonsterId;
    }

    public int getComputerMonsterId() {
        return computerMonsterId;
    }

    public void setComputerMonsterId(int computerMonster) {
        this.computerMonsterId = computerMonster;
    }

    public int getPlayerEquipmentId() {
        return playerEquipmentId;
    }

    public void setPlayerEquipmentId(int playerEquipmentId) {
        this.playerEquipmentId = playerEquipmentId;
    }

    public int getComputerEquipmentId() {
        return computerEquipmentId;
    }

    public void setComputerEquipmentId(int computerEquipmentId) {
        this.computerEquipmentId = computerEquipmentId;
    }

    public int getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(int weather) {
        this.weatherId = weatherId;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int location) {
        this.locationId = locationId;
    }
}
