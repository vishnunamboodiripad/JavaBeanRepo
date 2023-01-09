package learn.monsterBash.domain;

import learn.monsterBash.data.ElementRepo;
import learn.monsterBash.data.LocationRepo;
import learn.monsterBash.data.MonsterRepository;
import learn.monsterBash.models.Element;
import learn.monsterBash.models.Location;
import learn.monsterBash.models.Monster;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ElementService {

    private final ElementRepo repo;

    private final MonsterRepository monsterRepo;

    private final LocationRepo locationRepo;


    public ElementService(ElementRepo repo, MonsterRepository monsterRepo, LocationRepo locationRepo) {
        this.repo = repo;
        this.monsterRepo = monsterRepo;
        this.locationRepo = locationRepo;
    }

    public Element findById(int elementId) {
        return repo.findById(elementId);
    }

    public Result<Element> add(Element element){
        Result<Element> result = validate(element);
        if(!result.isSuccess()){
            return result;
        }

        if (element.getElementId() != 0){
            result.addMessage("elementId should not be set by user", ResultType.INVALID);
            return result;
        }
        List<Element> elements = repo.findAll();
        String name = element.getElementName();
        Optional<Element> duplicate = elements.stream().filter(e -> e.getElementName().equalsIgnoreCase(name)).findAny();
        if (duplicate.isPresent()){
            result.addMessage("Element name must be unique", ResultType.INVALID);
            return result;
        }
        element = repo.add(element);
        result.setPayload(element);
        return result;
    }

   public Result<Element> update(Element element){
        Result<Element> result = validate(element);
        if (!result.isSuccess()){
            return result;
        }
        if (element.getElementId() <= 0) {
            result.addMessage("elementId was not correctly set by user", ResultType.INVALID);
            return result;
        }
        List<Element> elements = repo.findAll();
       String name = element.getElementName();
       Optional<Element> duplicate = elements.stream().filter(e -> e.getElementName().equalsIgnoreCase(name)).findAny();
       if (duplicate.isPresent()){
           result.addMessage("Element name already exists", ResultType.INVALID);
           return result;
       }

       Boolean update = repo.update(element);
       if (!update) {
           String message = String.format("Element with Id: %s was not found", element.getElementId());
           result.addMessage(message, ResultType.NOT_FOUND);
       }
       return result;
   }

   public Result<Element> deleteById(int elementId){
        Result<Element> result = new Result<>();
        List<Monster> monsters = monsterRepo.findAll();
        Optional<Monster> duplicateMonster = monsters.stream().filter(m -> m.getElementId() == elementId).findAny();
       if (duplicateMonster.isPresent()){
           result.addMessage("Element cannot be deleted because it is attached to a monster", ResultType.INVALID);
           return result;
       }
       List<Location> locations = locationRepo.findAll();
       Optional<Location> duplicateLocation = locations.stream().filter(l -> l.getElementId() == elementId).findAny();
       if (duplicateLocation.isPresent()){
           result.addMessage("Element cannot be deleted because it is attached to a location", ResultType.INVALID);
           return result;
       }
        boolean delete = repo.deleteById(elementId);
        if (!delete){
            String message = String.format("Element with Id: %s was not found", elementId);
            result.addMessage(message, ResultType.NOT_FOUND);
        }
        return result;
   }

   private Result<Element> validate (Element element){
       Result<Element> result = new Result<>();
       if (element == null) {
           result.addMessage("Cannot add a null element", ResultType.INVALID);
           return result;
       }
       if (element.getElementName().isBlank() || element.getElementName() == null) {
           result.addMessage("Cannot add element without a name", ResultType.INVALID);
           return result;
       }

       return result;
   }
}
