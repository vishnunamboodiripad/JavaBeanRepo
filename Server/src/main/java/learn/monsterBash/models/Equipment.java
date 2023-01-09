package learn.monsterBash.models;

public class Equipment {

    private int equipmentId;
    public String equipmentName;
    public String equipmentImage;
    public int strength;
    public Affinity affinity;

    public Equipment() {
    }



    public Equipment(int equipmentId, String equipmentName, String equipmentImage, int strength, Affinity affinity) {
        this.equipmentId = equipmentId;
        this.equipmentName = equipmentName;
        this.equipmentImage = equipmentImage;
        this.strength = strength;
        this.affinity = affinity;
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

    public Affinity getAffinity() {
        return affinity;
    }

    public String getAffinityName(){ return affinity.getAffinityName();}

    public int getAffinityId(){return affinity.getAffinityId();}

    public void setAffinity(Affinity affinity) {
        this.affinity = affinity;
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "equipmentId=" + equipmentId +
                ", equipmentName='" + equipmentName + '\'' +
                ", equipmentImage='" + equipmentImage + '\'' +
                ", strength=" + strength +
                ", affinity=" + affinity +
                '}';
    }
}
