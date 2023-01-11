package learn.monsterBash.domain;

import learn.monsterBash.data.AffinityRepo;
import learn.monsterBash.data.EquipmentRepo;
import learn.monsterBash.data.WeatherRepo;
import learn.monsterBash.models.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AffinityService {
    private final AffinityRepo repo;

    private final EquipmentRepo equipmentRepo;

    private final WeatherRepo weatherRepo;


    public AffinityService(AffinityRepo repo, EquipmentRepo equipmentRepo, WeatherRepo weatherRepo) {
        this.repo = repo;
        this.equipmentRepo = equipmentRepo;
        this.weatherRepo = weatherRepo;
    }

    public List<Affinity> findAll() {
        return repo.findAll();
    }

    public Affinity findById(int affinityId){
        return repo.findById(affinityId);
    }

    public Result<Affinity> add(Affinity affinity){
        Result<Affinity> result = validate(affinity);
        if (!result.isSuccess()){
            return result;
        }

        if(affinity.getAffinityId() !=0) {
            result.addMessage("affinityId should not be set by user", ResultType.INVALID);
            return result;
        }
        List<Affinity> affinities = repo.findAll();
        String name = affinity.getAffinityName();
        Optional<Affinity> duplicate = affinities.stream().filter(w -> w.getAffinityName().equalsIgnoreCase(name)).findAny();
        if (duplicate.isPresent()) {
            result.addMessage("Affinity name must be unique", ResultType.INVALID);
            return result;
        }
        affinity = repo.add(affinity);
        result.setPayload(affinity);
        return result;
    }

    public Result<Affinity> update(Affinity affinity) {
        Result<Affinity> result = validate(affinity);
        if (!result.isSuccess()) {
            return result;
        }
        if (affinity.getAffinityId() <= 0) {
            result.addMessage("affinityId was not correctly set by user", ResultType.INVALID);
            return result;
        }
        List<Affinity> affinities = repo.findAll();
        String name = affinity.getAffinityName();
        Optional<Affinity> duplicateName = affinities.stream().filter(w -> w.getAffinityName().equalsIgnoreCase(name)).findAny();
        if (duplicateName.isPresent()) {
            result.addMessage("That affinity name already exists", ResultType.INVALID);
            return result;
        }

        boolean update = repo.update(affinity);
        if (!update) {
            String message = String.format("Affinity with Id: %s was not found", affinity.getAffinityId());
            result.addMessage(message, ResultType.NOT_FOUND);
        }

        return result;

    }

    public Result<Affinity> deleteById(int affinityId){
        Result<Affinity> result = new Result<>();
        List<Equipment> equipments = equipmentRepo.findAll();
        Optional<Equipment> duplicateEquipment = equipments.stream().filter(e -> e.getAffinityId() == affinityId).findAny();
        if (duplicateEquipment.isPresent()){
            result.addMessage("Affinity cannot be deleted because it is attached to an equipment", ResultType.INVALID);
            return result;
        }
        List<Weather> weathers = weatherRepo.findAll();
        Optional<Weather> duplicateWeather = weathers.stream().filter(w -> w.getAffinityId() == affinityId).findAny();
        if (duplicateWeather.isPresent()){
            result.addMessage("Affinity cannot be deleted because it is attached to a weather event", ResultType.INVALID);
            return result;
        }
        boolean delete = repo.deleteById(affinityId);
        if (!delete){
            String message = String.format("Affinity with Id: %s was not found", affinityId);
            result.addMessage(message, ResultType.NOT_FOUND);
        }
        return result;
    }

    private Result<Affinity> validate(Affinity affinity) {
        Result<Affinity> result = new Result<>();
        if (affinity == null) {
            result.addMessage("Cannot add a null affinity", ResultType.INVALID);
            return result;
        }
        if (affinity.getAffinityName().isBlank() || affinity.getAffinityName() == null) {
            result.addMessage("Cannot add affinity without a name", ResultType.INVALID);
            return result;
        }

        return result;
    }
}
