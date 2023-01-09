package learn.monsterBash.models;

public class Weather {
    private int weatherId;
    public String weatherName;
    public String weatherImage;
    public Affinity affinity;

    public Weather() {
    }



    public Weather(int weatherId, String weatherName, String weatherImage, Affinity affinity) {
        this.weatherId = weatherId;
        this.weatherName = weatherName;
        this.weatherImage = weatherImage;
        this.affinity = affinity;
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

    public Affinity getAffinity(){return affinity;}

    public String getAffinityName(){return affinity.getAffinityName();}

    public int getAffinityId() {
        return affinity.getAffinityId();
    }

    public void setAffinity(Affinity affinity) {
        this.affinity = affinity;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "weatherId=" + weatherId +
                ", weatherName='" + weatherName + '\'' +
                ", weatherImage='" + weatherImage + '\'' +
                ", affinity=" + affinity +
                '}';
    }
}
