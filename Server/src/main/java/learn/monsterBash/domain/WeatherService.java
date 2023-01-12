package learn.monsterBash.domain;

import learn.monsterBash.data.WeatherRepo;
import learn.monsterBash.models.Weather;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WeatherService {

    private final WeatherRepo repo;


    public WeatherService(WeatherRepo repo) {
        this.repo = repo;
    }

    public List<Weather> findAll() {
        return repo.findAll();
    }

    public Weather findByID(int id){
        return repo.findById(id);
    }

    public Result<Weather> add(Weather weather){
        Result<Weather> result = validate(weather);
        if (!result.isSuccess()){
            return result;
        }

        if(weather.getWeatherId() !=0) {
            result.addMessage("weatherId should not be set by user", ResultType.INVALID);
            return result;
        }
        List<Weather> weathers = repo.findAll();
        String name = weather.getWeatherName();
        Optional<Weather> duplicate = weathers.stream().filter(w -> w.getWeatherName().equalsIgnoreCase(name)).findAny();
        if (duplicate.isPresent()) {
            result.addMessage("Weather name needs to be unique", ResultType.INVALID);
            return result;
        }
        weather = repo.add(weather);
        result.setPayload(weather);
        return result;
    }

    public Result<Weather> update(Weather weather) {
        Result<Weather> result = validate(weather);
        if (!result.isSuccess()) {
            return result;
        }
        if (weather.getWeatherId() <= 0) {
            result.addMessage("weatherId was not correctly set by user", ResultType.INVALID);
            return result;
        }
        List<Weather> weathers = repo.findAll();
        String name = weather.getWeatherName();
        Optional<Weather> duplicateName = weathers.stream().filter(w -> w.getWeatherName().equalsIgnoreCase(name) && (w.getWeatherId() != weather.getWeatherId())).findAny();
        if (duplicateName.isPresent()) {
            result.addMessage("That weather name already exists", ResultType.INVALID);
            return result;
        }
        String image = weather.getWeatherImage();
        Optional<Weather> duplicateImage = weathers.stream().filter(w -> w.getWeatherImage().equalsIgnoreCase(image) && (w.getWeatherId() != weather.getWeatherId())).findAny();
        if (duplicateImage.isPresent()) {
            result.addMessage("That weather image is already being used in the database", ResultType.INVALID);
            return result;
        }

        boolean update = repo.update(weather);
        if (!update) {
            String message = String.format("Weather with Id: %s was not found", weather.getWeatherId());
            result.addMessage(message, ResultType.NOT_FOUND);
        }

        return result;

    }

    public Result<Weather> deleteById(int weatherId){
        Result<Weather> result = new Result<>();
        boolean delete = repo.deleteById(weatherId);
        if (!delete) {
            String message = String.format("Weather with Id: %s was not found", weatherId);
            result.addMessage(message, ResultType.NOT_FOUND);
        }
        return result;
    }

    private Result<Weather> validate(Weather weather) {
        Result<Weather> result = new Result<>();
        if (weather == null) {
            result.addMessage("Cannot add a null weather", ResultType.INVALID);
            return result;
        }
        if (weather.getWeatherName().isBlank() || weather.getWeatherName() == null) {
            result.addMessage("Cannot add weather without a name", ResultType.INVALID);
            return result;
        }
        if (weather.getWeatherImage().isBlank() || weather.getWeatherImage() == null) {
            result.addMessage("Cannot add weather without an image", ResultType.INVALID);
            return result;
        }

        return result;
    }
}
