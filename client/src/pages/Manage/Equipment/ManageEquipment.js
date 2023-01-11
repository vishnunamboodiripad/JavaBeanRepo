import React from "react";

export default function ManageEquipment(){
    

    return (
        < >
        <h1>Manage the Equipment database here.</h1>
        <h2>Begin by viewing the current Equipments or add a new one.</h2>

        <div id = "flexbox1" class ="flex-container">
            <div>
        <a href = "/manage/equipment/form" className = "btn btn-primary">Add a Equipment</a>
            </div>
            <div>
        <a href = "/manage/equipment/displayAll" className = "btn btn-secondary">Display all Equipments</a>
            </div>
        </div>
        
        </>
        
    )
}