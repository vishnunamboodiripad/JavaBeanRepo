package learn.monsterBash.data;

import learn.monsterBash.models.Affinity;
import learn.monsterBash.models.Equipment;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AffinityRepo {
    List<Affinity> findAll();

    Affinity add(Affinity affinity);

    @Transactional
    Affinity findById(int affinityId);

    boolean update(Affinity affinity);

    @Transactional
    boolean deleteById(int affinityId);

}
