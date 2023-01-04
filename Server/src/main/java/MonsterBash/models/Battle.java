package MonsterBash.models;

public class Battle {

    private int battleId;
    private int monsterEquipmentId1;
    private int monsterEquipmentId2;
    private int weatherId;
    private int locationId;

    public Battle() {
    }

    @Override
    public String toString() {
        return "Battle{" +
                "battleId=" + battleId +
                ", monsterEquipmentId1=" + monsterEquipmentId1 +
                ", monsterEquipmentId2=" + monsterEquipmentId2 +
                ", weatherId=" + weatherId +
                ", locationId=" + locationId +
                '}';
    }

    public Battle(int battleId, int monsterEquipmentId1, int monsterEquipmentId2, int weatherId, int locationId) {
        this.battleId = battleId;
        this.monsterEquipmentId1 = monsterEquipmentId1;
        this.monsterEquipmentId2 = monsterEquipmentId2;
        this.weatherId = weatherId;
        this.locationId = locationId;
    }

    public int getBattleId() {
        return battleId;
    }

    public void setBattleId(int battleId) {
        this.battleId = battleId;
    }

    public int getMonsterEquipmentId1() {
        return monsterEquipmentId1;
    }

    public void setMonsterEquipmentId1(int monsterEquipmentId1) {
        this.monsterEquipmentId1 = monsterEquipmentId1;
    }

    public int getMonsterEquipmentId2() {
        return monsterEquipmentId2;
    }

    public void setMonsterEquipmentId2(int monsterEquipmentId2) {
        this.monsterEquipmentId2 = monsterEquipmentId2;
    }

    public int getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(int weatherId) {
        this.weatherId = weatherId;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }
}
