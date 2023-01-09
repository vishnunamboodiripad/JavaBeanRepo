package learn.monsterBash.domain;


import learn.monsterBash.data.MonsterRepository;
import learn.monsterBash.models.Monster;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
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
            result.addMessage("monsterId must be set for update", ResultType.INVALID);
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
            result.addMessage("Monster cannot be null.", ResultType.INVALID);
            return result;
        }

        if (monster.getMonsterName() == null || monster.getMonsterName().isBlank()) {
            result.addMessage("A name is required", ResultType.INVALID);
        }

        if (monster.getPower() <= 0 || monster.getPower() >=100) {
            result.addMessage("Monster's power must be between 0 & 100.", ResultType.INVALID);
        }

        List<Monster> monsters = repository.findAll();
        if (monsters==null) {
            monsters = new ArrayList<Monster>();
        }
        for (Monster m: monsters) {
            if(monster.getMonsterName() == m.getMonsterName()) {
                result.addMessage("Monster is a duplicate.", ResultType.INVALID);
            }
        }


        return result;
    }
}