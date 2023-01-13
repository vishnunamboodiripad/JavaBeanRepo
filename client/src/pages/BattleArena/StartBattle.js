import React, {useRef, useEffect, useState, useLayoutEffect} from "react";
import {gsap} from "gsap";

export default function StartBattle(props){

    const[playerMonster, setPlayerMonster] = useState({playerMonsterId: "", playerMonsterName: "", playerMonsterImage: "", power: 0, elementId: 0})
    const [playerEquipment, setPlayerEquipment] = useState({playerEquipmentId: "", playerEquipmentImage: "", strength: 0, affinityId: 0})
    const [playerMonsterInt, setPlayerMonsterInt] = useState("");
    const [playerEquipmentInt, setPlayerEquipmentInt] = useState("");

    const titleRef = useRef(null);
    
    const setPlayerMonsterFetch = () => {
        localStorage.removeItem("playerMonster");
        fetch(`http://localhost:8080/api/monster/${playerMonsterInt}`, 
        {method: "GET"})
        .then((response) => {
            return response.json()
        })
        .then((json) => {
        setPlayerMonster(json)
        localStorage.setItem("playerMonster", JSON.stringify(json))
       
    })  
    }

    const titleMovement = () => {
        gsap.fromTo(
            [titleRef.current],
            1.5,
            {y: 5, repeat: -1}, {y:-5, repeat: -1, yoyo: true}
      
          );
    }

    const setPlayerEquipmentFetch = () => {
        localStorage.removeItem("playerEquipment");
        fetch(`http://localhost:8080/api/equipment/${playerEquipmentInt}`, 
        {method: "GET"})
        .then((response) => {
            return response.json()
        })
        .then((json) => {setPlayerEquipment(json) 
        localStorage.setItem("playerEquipment", JSON.stringify(json))})  
    }

    useEffect(titleMovement, [])

    useEffect(setPlayerMonsterFetch, [playerMonsterInt])
    useEffect(setPlayerEquipmentFetch, [playerEquipmentInt])


    return(
        <div>

            <div class = "header">
        <h1 ref = {titleRef}>Choose your fighter and weapon!</h1>


         </div>
         </div>
        <div class = "flex-box"></div>
        <h2>{playerMonster.monsterId}</h2>
        <h3>{playerEquipment.equipmentId}</h3>
 
        
            <div>
            <table className = "table table-dark">
            <thead key = "header">
                <tr>
                    <td>Name</td>
                    <td>Image</td>
                    <td>Element</td>
                    <td>Power</td>
                </tr>
            </thead>
            <tbody>
                {props.monsterList.length === 0 ? <tr>Please wait, table is loading</tr> : props.monsterList.map((monster) => {
                    return (
                        <tr key = {monster.monsterId}>
                            <td>{monster.monsterName}</td>
                            <td><img id = "monsterImage"src={monster.monsterImage} height = "50" width = "50"></img></td>
                            <td>{props.getElementName(monster.elementId)}</td>
                            <td>{monster.power}</td>
                        </tr>
                    )
                })}
            </tbody>
        </table>
                <label htmlFor = "playerMonster-input">Choose a monster for your battle:</label>
                <select id = "playerMonster-input" value = {playerMonsterInt} onChange = {(event) => {setPlayerMonsterInt(parseInt(event.target.value))}}>
                    {props.monsterList.map((monster) => (
                        <option key = {monster.monsterId} value = {monster.monsterId}>{monster.monsterName}</option>
                        
                    ))}
                </select>
                <img ref = {monster} className = "monster" src = "https://app.pixelencounter.com/api/basic/monsters/random" height = "200" width = "200"></img>  
            </div>
        

            <div>
            <table class = "table table-dark">
            <thead key = "header">
                <tr>
                    <td>Name</td>
                    <td>Image</td>
                    <td>Affinity</td>
                    <td>Strength</td>
                </tr>
            </thead>
            <tbody>
                {props.equipmentList.length === 0 ? <tr>Please wait, table is loading</tr> : props.equipmentList.map((equipment) => {
                    return (
                        <tr key = {equipment.equipmentId}>
                            <td>{equipment.equipmentName}</td>
                            <td><img id = "equipmentImage"src={equipment.equipmentImage} height = "50" width = "50"></img></td>
                            <td>{props.getAffinityName(equipment.affinityId)}</td>
                            <td>{equipment.strength}</td>
                        </tr>
                    )
                })}
            </tbody>
            </table>
                <label htmlFor = "playerEquipment-input">Choose a weapon for your monster:</label>
                <select id = "playerEquipment-input" value = {playerEquipmentInt} onChange = {(event) => {setPlayerEquipmentInt(parseInt(event.target.value))}}>
                    {props.equipmentList.map((equipment) => (
                        <option key = {equipment.equipmentId} value = {equipment.equipmentId}>{equipment.equipmentName}</option>
                    ))}
                    
                </select>
            </div>
     
                <div>

                </div>
            
            
            <a className = "btn btn-secondary" href = "/battle/arena" >Enter Arena</a>

            </div>
 
    )
    
}
