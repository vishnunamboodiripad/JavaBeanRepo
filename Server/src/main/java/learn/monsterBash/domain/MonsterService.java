package learn.monsterBash.domain;

import learn.monsterBash.data.MonsterRepository;
import learn.monsterBash.models.Monster;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MonsterService {

    private final MonsterRepository repository;

    public MonsterService(MonsterRepository repository) {
        this.repository = repository;
    }

    public List<Monster> findAll() {
        return repository.findAll();
    }

    public Monster findById(int monsterId) {
        return repository.findById(monsterId);
    }

    public Result<Monster> add(Monster monster) {
        Result<Monster> result = validate(monster);
        if (!result.isSuccess()) {
            return result;
        }

        if (monster.getMonsterId() != 0) {
            result.addMessage("monsterId cannot be set for `add` operation", ResultType.INVALID);
            return result;
        }

        monster = repository.add(monster);
        result.setPayload(monster);
        return result;
    }

    public Result<Monster> update(Monster monster) {
        Result<Monster> result = validate(monster);
        if (!result.isSuccess()) {
            return result;
        }

        if (monster.getMonsterId() <= 0) {
            result.addMessage("monsterId must be set for `update` operation", ResultType.INVALID);
            return result;
        }

        if (!repository.update(monster)) {
            String msg = String.format("monsterId: %s, not found", monster.getMonsterId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }

        return result;
    }

    public boolean deleteById(int monsterId) {
        return repository.deleteById(monsterId);
    }

    private Result<Monster> validate(Monster monster) {
        Result<Monster> result = new Result<>();
        if (monster == null) {
            result.addMessage("monster cannot be null", ResultType.INVALID);
            return result;
        }

        if (Validations.isNullOrBlank(monster.getFirstName())) {
            result.addMessage("firstName is required", ResultType.INVALID);
        }

        if (Validations.isNullOrBlank(monster.getLastName())) {
            result.addMessage("lastName is required", ResultType.INVALID);
        }

        if (monster.getDob() != null && monster.getDob().isAfter(LocalDate.now().minusYears(12))) {
            result.addMessage("monsters younger than 12 are not allowed", ResultType.INVALID);
        }

        if (monster.getHeightInInches() < 36 || monster.getHeightInInches() > 96) {
            result.addMessage("height must be between 36 and 96 inches", ResultType.INVALID);
        }

        return result;
    }
}