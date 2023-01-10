import React, { useState } from "react";

export default function StartBattle(props){

   const [playerMonster, setPlayerMonster] = useState("");
   const [playerEquipment, setPlayerEquipment] = useState("");

    return(
        <div>
        <h1>Here's the page for starting a battle</h1>
       
        <div>
                <label htmlFor = "playerMonster-input">Choose a monster for your battle:</label>
                <select id = "playerMonster-input" value = {playerMonster} onChange = {(event) => {setPlayerMonster(parseInt(event.target.value))}}>
                    {props.monsterList.map((monster) => (
                        <option key = {monster.monsterId} value = {monster.monsterId}>{monster.monsterName}</option>
                    ))}
                    
                </select>
                <img src = "https://app.pixelencounter.com/api/basic/monsters/random" height = "200" width = "200"></img>  
        </div>
        
        {/* <div>
                <label htmlFor = "playerEquipment-input">Choose a weapon for your monster:</label>
                <select id = "playerEquipment-input" value = {playerEquipment} onChange = {(event) => {setPlayerEquipment(parseInt(event.target.value))}}>
                    {props.equipmentList((equipment) => (
                        <option key = {equipment.equipmentId} value = {equipment.equipmentId}>{equipment.equipmentName}</option>
                    ))}
                    
                </select>
        </div> */}
        
                
            
            
            <a className = "btn btn-secondary" href = "/battle/arena" >Enter Arena</a>

        
        </div>
    )
    
}