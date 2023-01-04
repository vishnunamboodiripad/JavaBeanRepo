package MonsterBash.models;

public class Equipment {

    private int equipmentId;
    public String equipmentName;
    public String equipmentImage;
    public int strength;
    public int affinityId;

    public Equipment() {
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "equipmentId=" + equipmentId +
                ", equipmentName='" + equipmentName + '\'' +
                ", equipmentImage='" + equipmentImage + '\'' +
                ", strength=" + strength +
                ", affinityId=" + affinityId +
                '}';
    }

    public Equipment(int equipmentId, String equipmentName, String equipmentImage, int strength, int affinityId) {
        this.equipmentId = equipmentId;
        this.equipmentName = equipmentName;
        this.equipmentImage = equipmentImage;
        this.strength = strength;
        this.affinityId = affinityId;
    }

    public int getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getEquipmentImage() {
        return equipmentImage;
    }

    public void setEquipmentImage(String equipmentImage) {
        this.equipmentImage = equipmentImage;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getAffinityId() {
        return affinityId;
    }

    public void setAffinityId(int affinityId) {
        this.affinityId = affinityId;
    }
}
