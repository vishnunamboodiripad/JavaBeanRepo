package learn.monsterBash.data;

import learn.monsterBash.models.Location;
import learn.monsterBash.models.Monster;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface LocationRepo {
    List<Location> findAll();
    Location add(Location location);

    @Transactional
    Location findById(int locationId);

    boolean update(Location location);

    @Transactional
    boolean deleteById(int locationId);
}

