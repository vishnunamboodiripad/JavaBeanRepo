package learn.monsterBash.domain;

import learn.monsterBash.data.EquipmentJdbcTemplateRepository;
import learn.monsterBash.models.Equipment;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public class EquipmentService {
        private final EquipmentJdbcTemplateRepository repository;

        public EquipmentService(EquipmentJdbcTemplateRepository repository) {
            this.repository = repository;
        }

        public List<Equipment> findAll() {
            return repository.findAll();
        }

        public Equipment findById(int equipmentId) {
            return repository.findById(equipmentId);
        }

        public Result<Equipment> add(Equipment equipment) {
            Result<Equipment> result = validate(equipment);
            if (!result.isSuccess()) {
                return result;
            }

            if (equipment.getEquipmentId() != 0) {
                result.addMessage("EquipmentID cannot be set for `add` operation", ResultType.INVALID);
                return result;
            }

            equipment = repository.add(equipment);
            result.setPayload(equipment);
            return result;
        }

        public Result<Equipment> update(Equipment equipment) {
            Result<Equipment> result = validate(equipment);
            if (!result.isSuccess()) {
                return result;
            }

            if (equipment.getEquipmentId() <= 0) {
                result.addMessage("equipmentId must be set for `update` operation", ResultType.INVALID);
                return result;
            }

            if (!repository.update(equipment)) {
                String msg = String.format("equipmentId: %s, not found", equipment.getEquipmentId());
                result.addMessage(msg, ResultType.NOT_FOUND);
            }

            return result;
        }

        public boolean deleteById(int equipmentId) {
            return repository.deleteById(equipmentId);
        }

        private Result<Equipment> validate(Equipment equipment) {
            Result<Equipment> result = new Result<>();
            if (equipment == null) {
                result.addMessage("equipment cannot be null", ResultType.INVALID);
                return result;
            }

            if (equipment.getEquipmentName() == null || equipment.getEquipmentName().isBlank()) {
                result.addMessage("equipment name is required", ResultType.INVALID);
            }


            if (equipment.getStrength() < 0 || equipment.getStrength() > 100) {
                result.addMessage("strength must be between 0 and 100", ResultType.INVALID);
            }
            if (!equipment.getEquipmentImage().contains(".")){
                result.addMessage("image must call from a website address", ResultType.INVALID);
            }

            return result;
        }

    }


