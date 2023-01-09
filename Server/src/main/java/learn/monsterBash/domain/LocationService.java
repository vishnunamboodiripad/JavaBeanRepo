package learn.monsterBash.domain;

import learn.monsterBash.data.EquipmentJdbcTemplateRepository;
import learn.monsterBash.data.LocationJdbcTemplateRepository;
import learn.monsterBash.models.Equipment;
import learn.monsterBash.models.Location;
import learn.monsterBash.models.Weather;

import java.util.List;

public class LocationService {
    private final LocationJdbcTemplateRepository repository;

    public LocationService(LocationJdbcTemplateRepository repository) {
        this.repository = repository;
    }

    public List<Location> findAll() {
        return repository.findAll();
    }

    public Location findById(int locationId) {
        return repository.findById(locationId);
    }

    public Result<Location> add(Location location) {
        Result<Location> result = validate(location);
        if (!result.isSuccess()) {
            return result;
        }

        if (location.getLocationId() != 0) {
            result.addMessage("LocationId cannot be set for `add` operation", ResultType.INVALID);
            return result;
        }

        location = repository.add(location);
        result.setPayload(location);
        return result;
    }

    public Result<Location> update(Location location) {
        Result<Location> result = validate(location);
        if (!result.isSuccess()) {
            return result;
        }

        if (location.getLocationId() <= 0) {
            result.addMessage("locationId must be set for `update` operation", ResultType.INVALID);
            return result;
        }

        if (!repository.update(location)) {
            String msg = String.format("locationId: %s, not found", location.getLocationId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }

        return result;
    }

    public Result<Location> deleteById(int locationId){
        Result<Location> result = new Result<>();
        boolean delete = repository.deleteById(locationId);
        if (!delete) {
            String message = String.format("Weather with Id: %s was not found", locationId);
            result.addMessage(message, ResultType.NOT_FOUND);
        }
        return result;
    }

    private Result<Location> validate(Location location) {
        Result<Location> result = new Result<>();
        if (location == null) {
            result.addMessage("location cannot be null", ResultType.INVALID);
            return result;
        }

        if (location.getLocationName() == null || location.getLocationName().isBlank()) {
            result.addMessage("location name is required", ResultType.INVALID);
        }

        if (!location.getLocationImage().contains(".")){
            result.addMessage("image must call from a website address", ResultType.INVALID);
        }

        return result;
    }
}
