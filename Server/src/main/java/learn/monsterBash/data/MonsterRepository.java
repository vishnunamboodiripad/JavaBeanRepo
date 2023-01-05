package learn.monsterBash.data;

import learn.monsterBash.models.Monster;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MonsterRepository {
    List<Monster> findAll();
    Monster add(Monster monster);

    @Transactional
    Monster findById(int monsterId);

}
