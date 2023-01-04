package MonsterBash.models;

public class Weather {
    private int weatherId;
    public String weatherName;
    public String weatherImage;
    public int affinityId;

    public Weather() {
    }

    @Override
    public String toString() {
        return "Weather{" +
                "weatherId=" + weatherId +
                ", weatherName='" + weatherName + '\'' +
                ", weatherImage='" + weatherImage + '\'' +
                ", affinityId=" + affinityId +
                '}';
    }

    public Weather(int weatherId, String weatherName, String weatherImage, int affinityId) {
        this.weatherId = weatherId;
        this.weatherName = weatherName;
        this.weatherImage = weatherImage;
        this.affinityId = affinityId;
    }

    public int getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(int weatherId) {
        this.weatherId = weatherId;
    }

    public String getWeatherName() {
        return weatherName;
    }

    public void setWeatherName(String weatherName) {
        this.weatherName = weatherName;
    }

    public String getWeatherImage() {
        return weatherImage;
    }

    public void setWeatherImage(String weatherImage) {
        this.weatherImage = weatherImage;
    }

    public int getAffinityId() {
        return affinityId;
    }

    public void setAffinityId(int affinityId) {
        this.affinityId = affinityId;
    }
}
