package learn.monsterBash.data;

import learn.monsterBash.models.Element;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ElementRepo {
    List<Element> findAll();

    @Transactional
    Element findById(int elementId);

    Element add(Element element);

    boolean update(Element element);

    @Transactional
    boolean deleteById(int elementId);
}
