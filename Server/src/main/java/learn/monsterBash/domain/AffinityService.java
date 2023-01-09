package learn.monsterBash.domain;

import learn.monsterBash.data.AffinityRepo;
import learn.monsterBash.data.WeatherRepo;
import learn.monsterBash.models.Affinity;
import learn.monsterBash.models.Weather;

import java.util.List;
import java.util.Optional;

public class AffinityService {
    private final AffinityRepo repo;


    public AffinityService(AffinityRepo repo) {
        this.repo = repo;
    }

    public List<Affinity> findAll() {
        return repo.findAll();
    }

    public Affinity findByID(int affinityId){
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
        boolean delete = repo.deleteById(affinityId);
        if (!delete) {
            String message = String.format("Affinity with Id: %s was not found", affinityId);
            result.addMessage(message, ResultType.NOT_FOUND);
        }
        return result;
    }

    private Result<Affinity> validate(Affinity affinity) {
        Result<Affinity> result = new Result<>();
        if (affinity == null) {
            result.addMessage("Cannot add a null weather", ResultType.INVALID);
            return result;
        }
        if (affinity.getAffinityName().isBlank() || affinity.getAffinityName() == null) {
            result.addMessage("Cannot add weather without a name", ResultType.INVALID);
            return result;
        }

        return result;
    }
}
