package learn.monsterBash.models;

public class Location {

    private int locationId;
    public String locationName;
    public String locationImage;
    private int elementId;

    public Location() {
    }



    public Location(int locationId, String locationName, String locationImage, int elementId) {
        this.locationId = locationId;
        this.locationName = locationName;
        this.locationImage = locationImage;
        this.elementId = elementId;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationImage() {
        return locationImage;
    }

    public void setLocationImage(String locationImage) {
        this.locationImage = locationImage;
    }

    public int getElementId() {
        return elementId;
    }

    public void setElementId(int elementId) {
        this.elementId = elementId;
    }
}
