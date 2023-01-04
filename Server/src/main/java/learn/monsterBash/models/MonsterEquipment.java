package learn.monsterBash.models;


public class MonsterEquipment {

    private int monsterEquipmentId;
    public Monster monster;
    public Equipment equipment;

    public MonsterEquipment() {
    }

    public MonsterEquipment(int monsterEquipmentId, Monster monster, Equipment equipment) {
        this.monsterEquipmentId = monsterEquipmentId;
        this.monster = monster;
        this.equipment = equipment;
    }

    public int getMonsterEquipmentId() {
        return monsterEquipmentId;
    }

    public void setMonsterEquipmentId(int monsterEquipmentId) {
        this.monsterEquipmentId = monsterEquipmentId;
    }

    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }
}
