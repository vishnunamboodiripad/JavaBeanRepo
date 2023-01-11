package learn.monsterBash.models;

public class PlayerBattleChoice {

    Monster chosenMonster;

    Equipment chosenEquipment;

    int app_user_id;


    public Monster getChosenMonster() {
        return chosenMonster;
    }

    public void setChosenMonster(Monster chosenMonster) {
        this.chosenMonster = chosenMonster;
    }

    public Equipment getChosenEquipment() {
        return chosenEquipment;
    }

    public void setChosenEquipment(Equipment chosenEquipment) {
        this.chosenEquipment = chosenEquipment;
    }

    public int getApp_user_id() {
        return app_user_id;
    }

    public void setApp_user_id(int app_user_id) {
        this.app_user_id = app_user_id;
    }
}
