package learn.monsterBash.models;

public class Affinity {
    int affinityId;
    String affinityName;
    String affinityImage;

    public Affinity() {
    }

    public Affinity(int affinityId, String affinityName, String affinityImage) {
        this.affinityId = affinityId;
        this.affinityName = affinityName;
        this.affinityImage = affinityImage;
    }

    public String getAffinityImage() {
        return affinityImage;
    }

    public void setAffinityImage(String affinityImage) {
        this.affinityImage = affinityImage;
    }

    public int getAffinityId() {
        return affinityId;
    }

    public void setAffinityId(int affinityId) {
        this.affinityId = affinityId;
    }

    public String getAffinityName() {
        return affinityName;
    }

    public void setAffinityName(String affinityName) {
        this.affinityName = affinityName;
    }
}
