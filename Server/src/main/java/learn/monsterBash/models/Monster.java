package learn.monsterBash.models;

public class Monster {
    private int monsterId;
    public String monsterName;
    public String monsterImage;
    public int power;

    public String element;

    public Monster() {
    }

    public Monster(int monsterId, String monsterName, String monsterImage, int power, String element, int equipmentId) {
        this.monsterId = monsterId;
        this.monsterName = monsterName;
        this.monsterImage = monsterImage;
        this.power = power;
        this.element = element;
        this.equipmentId = equipmentId;
    }

    public int getMonsterId() {
        return monsterId;
    }

    public void setMonsterId(int monsterId) {
        this.monsterId = monsterId;
    }

    public String getMonsterName() {
        return monsterName;
    }

    public void setMonsterName(String monsterName) {
        this.monsterName = monsterName;
    }

    public String getMonsterImage() {
        return monsterImage;
    }

    public void setMonsterImage(String monsterImage) {
        this.monsterImage = monsterImage;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public int getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    public int equipmentId;

    @Override
    public String toString() {
        return "Monster{" +
                "monsterId=" + monsterId +
                ", monsterName='" + monsterName + '\'' +
                ", monsterImage='" + monsterImage + '\'' +
                ", power=" + power +
                ", element='" + element + '\'' +
                ", equipmentId=" + equipmentId +
                '}';
    }
}

