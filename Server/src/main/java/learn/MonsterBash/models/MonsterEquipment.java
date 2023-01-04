package learn.MonsterBash.models;


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

    public learn.MonsterBash.models.Monster getMonster() {
        return monster;
    }

    public void setMonster(learn.MonsterBash.models.Monster monster) {
        this.monster = monster;
    }

    public learn.MonsterBash.models.Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(learn.MonsterBash.models.Equipment equipment) {
        this.equipment = equipment;
    }
}
