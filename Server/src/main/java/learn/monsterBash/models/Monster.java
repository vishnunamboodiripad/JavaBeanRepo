package learn.monsterBash.models;

public class Monster {
    private int monsterId;
    public String monsterName;
    public String monsterImage;
    public int power;

    public int elementId;

    public Monster() {
    }

    public Monster(int monsterId, String monsterName, String monsterImage, int power, int elementId) {
        this.monsterId = monsterId;
        this.monsterName = monsterName;
        this.monsterImage = monsterImage;
        this.power = power;
        this.elementId = elementId;

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

    public int getElementId() {
        return elementId;
    }

    public void setElementId(int elementId) {
        this.elementId = elementId;
    }
}

