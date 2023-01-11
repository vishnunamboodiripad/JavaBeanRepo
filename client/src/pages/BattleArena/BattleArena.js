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

    const playerMonster = JSON.parse(localStorage.getItem("playerMonster"))

    const getBattle = () => {
          const playerEquipment = JSON.parse(localStorage.getItem("playerEquipment"))
          const userId = parseInt(user.userData.userId);
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
          .then((json) => {setBattle(json)})
        }

    //useEffect(getBattle, [])   

    
     
    const grabWeather = () => {
      let weatherId = battle.weatherId;
      fetch(`http://localhost:8080/api/weather/${weatherId}`, 
      {method: "GET"})
      .then((response) => {
          return response.json()
      })
      .then((json) => {setWeather(json)
    })} 

    const grabLocation = () => {
      fetch(`http://localhost:8080/api/location/${battle.locationId}`, 
      {method: "GET"})
      .then((response) => {
          return response.json()
      })
      .then((json) => {setLocation(json)
    })}  

    const grabComputerMonster = () => {
      fetch(`http://localhost:8080/api/monster/${battle.computerMonsterId}`, 
      {method: "GET"})
      .then((response) => {
          return response.json()
      })
      .then((json) => {setComputerMonster(json)
    })}  

    const grabComputerEquipment = () => {
      fetch(`http://localhost:8080/api/equipment/${battle.computerEquipmentId}`, 
      {method: "GET"})
      .then((response) => {
          return response.json()
      })
      .then((json) => {setComputerEquipment(json)
    })}

    useEffect(grabWeather, [battle])
    //useEffect(grabLocation, [battle])
    //useEffect(grabComputerMonster, [battle])
    //useEffect(grabComputerEquipment, [battle])

    const displayWinner = () => {
        if (battle.playerWin === true) {
          setWinnerReveal("You won! Congratulations you have slayed the enemy")
        }
        else {
          setWinnerReveal("The enemy has slayed you. Try again")
        }
    }

    useEffect(() => {
        gsap.to(
          [computerMonsterRef.current],
          3,
          {x:-200}
        );
      }, [winnerReveal]);

      useEffect(() => {
        gsap.to(
          [playerMonsterRef.current],
          3,
          { x: 200 }
        
        );
      }, [winnerReveal]);

    return (
        <div id = "#battle-arena">
            <h1>Welcome to the Arena!</h1>
            <h3>The weather for today's battle:</h3>
            <img id = "weather-battle" src = {weather.weatherImage}
            height = "100" width = "200"></img>

            <h4>Today's battle will take place at: </h4>
            <img id = "location-battle" src = {location.locationImage} 
            height = "200" width = "200"></img>

            <p id = "computer-monster-battle">Computer monster:</p>
            <img ref = {computerMonsterRef} id = "computer-monster-battle" src = {computerMonster.monsterImage} height = "100" width = "100"></img>

            <p id = "player-monster-battle">Player monster:</p>
            <img ref = {playerMonsterRef} id = "player-monster-battle" src = {playerMonster.monsterImage} height = "100" width = "100"></img>

            <button id = "start-battle-button" onClick = {displayWinner}>START BATTLE!</button>

            <div class = "winner-reveal">
                <div>{winnerReveal}</div>
            </div>
        </div>
    )

    
}