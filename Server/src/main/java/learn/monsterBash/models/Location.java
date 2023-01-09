package learn.monsterBash.models;

public class Location {

    private int locationId;
    public String locationName;
    public String locationImage;
    private Element element;

    public Location() {
    }



    public Location(int locationId, String locationName, String locationImage, Element element) {
        this.locationId = locationId;
        this.locationName = locationName;
        this.locationImage = locationImage;
        this.element = element;
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

    public Element getElement(){
        return element;
    }
    public String getElementName() {
        return element.getElementName();
    }

    public int getElementId(){
        return element.getElementId();
    }

    public void setElement(Element element) {
        this.element = element;
    }

    @Override
    public String toString() {
        return "Location{" +
                "locationId=" + locationId +
                ", locationName='" + locationName + '\'' +
                ", locationImage='" + locationImage + '\'' +
                ", element" + element +
                '}';
    }
}
