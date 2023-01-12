import React, { useEffect, useState, useRef, useContext } from "react";
import gsap from "gsap";
import UserContext from '../../context/AuthContext';

export default function BattleArena(props){

    const [battle, setBattle] = useState("");
    const [weather, setWeather] = useState("");
    const [location, setLocation] = useState("");
    const [computerMonster, setComputerMonster] = useState("");
    const [computerEquipment, setComputerEquipment] = useState("");   
    const [winnerReveal, setWinnerReveal] = useState("");

    
    const playerMonsterRef = useRef(null)
    const computerMonsterRef = useRef(null)

    const user = useContext(UserContext);
    let userId = 1;

    const playerMonster = JSON.parse(localStorage.getItem("playerMonster"))
    const playerEquipment = JSON.parse(localStorage.getItem("playerEquipment"))

    let playerAffinityId = playerEquipment.affinityId;
    let playerElementId = playerMonster.elementId;
    let computerElementId = computerMonster.elementId;
    let computerAffinityId = computerEquipment.affinityId

    const grabWeather = (b) => {
      let weatherId = b.weatherId;
      fetch(`http://localhost:8080/api/weather/${weatherId}`, 
      {method: "GET"})
      .then((response) => {
          return response.json()
      })
      .then((json) => {setWeather(json)
    })} 

    const grabLocation = (b) => {
      let locationId = b.locationId;
      fetch(`http://localhost:8080/api/location/${locationId}`, 
      {method: "GET"})
      .then((response) => {
          return response.json()
      })
      .then((json) => {setLocation(json)
    })}  

    const grabComputerMonster = (b) => {
      let compMonsterId = b.computerMonsterId;
      fetch(`http://localhost:8080/api/monster/${compMonsterId}`, 
      {method: "GET"})
      .then((response) => {
          return response.json()
      })
      .then((json) => {setComputerMonster(json)
    })}  

    const grabComputerEquipment = (b) => {
      let compEquipmentId = b.computerEquipmentId;

      fetch(`http://localhost:8080/api/equipment/${compEquipmentId}`, 
      {method: "GET"})
      .then((response) => {
          return response.json()
      })
      .then((json) => {setComputerEquipment(json)
    })
  }
    const addBattle = (b) => {
      fetch("http://localhost:8080/api/add/battle", {
        method: "POST", 
        headers: {
          "Content-Type": "application/json",
          "Accept": "application/json"
        },
        body: JSON.stringify(b),
      })
    }

    const getBattle = () => {
          const playerEquipment = JSON.parse(localStorage.getItem("playerEquipment"))
          if (user !== null) {
            userId = parseInt(user.userData.userId);
          }
          
          const playerChoice = {
              chosenMonster: playerMonster,
              chosenEquipment: playerEquipment,
              app_user_id: userId

            }
        fetch("http://localhost:8080/api/battle/findWinner", {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
              "Accept": "application/json"
          },
          body: JSON.stringify(playerChoice),
          }
        ).then((response) => {
          if (response.status === 201){
            return response.json()
            

          }
          else {
            response.json().then((errors) => {
              props.setErrors(errors)
            })
          }})
          .then((json) => {
            grabWeather(json)
            grabLocation(json)
            grabComputerEquipment(json)
            grabComputerMonster(json)
            setBattle(json)
          })
        
    }
  
    
    useEffect(getBattle, [])   

    
    const displayWinner = () => {
        addBattle(battle)
        gsap.to(
        [computerMonsterRef.current],
        3,
        {x:-200}
        );
        gsap.to(
          [playerMonsterRef.current],
          3,
          { x: 200 }
        
        );
        if (battle.playerWin === true) {
          setWinnerReveal("You won! Congratulations you have slayed the enemy")
        }
        else {
          setWinnerReveal("The enemy has slayed you. Try again")
        }
    }

    

    return (
        <div id = "#battle-arena">
            <h1>Welcome to the Arena!</h1>
            <h3>Weather forecast: {weather.weatherName}</h3>
            <img id = "weather-battle" src = {weather.weatherImage}
            height = "100" width = "200"></img>

            <h4>Battle location: {location.locationName} </h4>
            <img id = "location-battle" src = {location.locationImage} ></img>
            
            <p id = "computer-monster-battle">Computer monster: {computerMonster.monsterName}</p>
            <img ref = {computerMonsterRef} id = "computer-monster-battle" src = {computerMonster.monsterImage} height = "100" width = "100"></img>

            <p id = "player-monster-battle">Player monster: {playerMonster.monsterName}</p>
            <img ref = {playerMonsterRef} id = "player-monster-battle" src = {playerMonster.monsterImage} height = "100" width = "100"></img>
            <button id = "start-battle-button" onClick = {displayWinner}>START BATTLE!</button>

            {/* <div id = "player-info" className = "grid-container">
              <p>Player monster element: {props.getElementName(playerElementId)}</p>
              <p>Player equipment: {playerEquipment.equipmentName}</p>
              <img src = {playerEquipment.equipmentImage} height = "50" width = "50"></img>
              <p>Equipment affinity: {props.getAffinityName(playerAffinityId)}</p>
              <p>Player battle power: {playerMonster.power}</p>
            </div>

            <div id = "computer-info" className = "grid-container">
              <p>Computer monster element: {props.getElementName(computerElementId)}</p>
              <p>Computer equipment: {computerEquipment.equipmentName}</p>
              <img src = {computerEquipment.equipmentImage} height = "50" width = "50"></img>
              <p>Equipment affinity: {props.getAffinityName(computerAffinityId)}</p>
              <p>Computer battle power: {computerMonster.power}</p>
            </div> */}

            <div class = "winner-reveal">
                <div>{winnerReveal}</div>
            </div>
        </div>
    )

    
}