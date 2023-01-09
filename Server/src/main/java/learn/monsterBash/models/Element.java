package learn.monsterBash.models;

public class Element {
    private int elementId;

    public String elementName;

    public Element() {
    }

    public Element(int elementId, String elementName) {
        this.elementId = elementId;
        this.elementName = elementName;
    }

    public int getElementId() {
        return elementId;
    }

    public void setElementId(int elementId) {
        this.elementId = elementId;
    }

    public String getElementName() {
        return elementName;
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    @Override
    public String toString() {
        return "Element{" +
                "elementId=" + elementId +
                ", elementName='" + elementName + '\'' +
                '}';
    }
}
