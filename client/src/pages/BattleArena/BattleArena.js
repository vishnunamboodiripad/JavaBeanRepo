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
    const [powerReveal, setPowerReveal] = useState("");
    const [playerElement, setPlayerElement] = useState("");
    const [enemyElement, setEnemyElement] = useState("");
    const [playerAffinity, setPlayerAffinity] = useState("");
    const [enemyAffinity, setEnemyAffinity] = useState("");

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

    const grabPlayerMonsterElement = (m) => {
      let elementId = m.elementId;
      fetch(`http://localhost:8080/api/element/${elementId}`, 
      {method: "GET"})
      .then((response) => {
          return response.json()
      })
      .then((json) => {setPlayerElement(json)
      })}

    const grabComputerMonsterElement = (m) => {
        let elementId = m.elementId;
        fetch(`http://localhost:8080/api/element/${elementId}`, 
        {method: "GET"})
        .then((response) => {
            return response.json()
        })
        .then((json) => {setEnemyElement(json)
        })}

    const grabPlayerEquipmentAffinity = (e) => {
          let affinityId = e.affinityId;
          fetch(`http://localhost:8080/api/affinity/${affinityId}`, 
          {method: "GET"})
          .then((response) => {
              return response.json()
          })
          .then((json) => {setPlayerAffinity(json)
          })}
    
    const grabComputerEquipmentAffinity = (e) => {
            let affinityId = e.affinityId;
            fetch(`http://localhost:8080/api/affinity/${affinityId}`, 
            {method: "GET"})
            .then((response) => {
                return response.json()
            })
            .then((json) => {setEnemyAffinity(json)
            })}

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
      .then((json) => {
        setComputerMonster(json)
        grabComputerMonsterElement(json)
        grabPlayerMonsterElement(playerMonster)

    })}  

    const grabComputerEquipment = (b) => {
      let compEquipmentId = b.computerEquipmentId;

      fetch(`http://localhost:8080/api/equipment/${compEquipmentId}`, 
      {method: "GET"})
      .then((response) => {
          return response.json()
      })
      .then((json) => {
        setComputerEquipment(json)
        grabPlayerEquipmentAffinity(playerEquipment)
        grabComputerEquipmentAffinity(json)

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
            gsap.to(
              [computerMonsterRef.current],
              4,
              {x:-250,
              y: 270}
    
            );
            gsap.to(
              [playerMonsterRef.current],
              4,
              { x:250,
                y: 270},
        
            );
          })
        
    }
  
    useEffect(getBattle, [])   

    
    const displayWinner = () => {
        console.log(battle)
        addBattle(battle)
       
        if (battle.playerWin === true) {
          setWinnerReveal("You won! Congratulations you have slain the enemy")
          gsap.to(
            [playerMonsterRef.current],
            2,
            { x:900,
              y: 270},
      
          );
          gsap.to(
            [computerMonsterRef.current],
            2,
            {x:-930,
            y: 270}
  
          );
          gsap.to(
            [computerMonsterRef.current],
            {rotation:-90, transformOrigin: "center", ease: "none", duration: 3}, ">"
      
          );
        }
        else {
          setWinnerReveal("The enemy has slain you. Try again")
          gsap.to(
            [computerMonsterRef.current],
            2,
            {x:-930,
            y: 270}
  
          );
          gsap.to(
            [playerMonsterRef.current],
            2,
            { x:900,
              y: 270},
      
          );
          gsap.to(
            [playerMonsterRef.current],
            {rotation:90, transformOrigin: "center", ease: "none", duration: 3}, ">"
      
          );
        }
          
        if (battle.playerWin === true) {
          setWinnerReveal("You won! Congratulations you have slayed the enemy.")
          setPowerReveal(`Enemy battle power: ${battle.computerTotalPower}. Player battle power: ${battle.playerTotalPower}`)

        }
        else {
          setWinnerReveal("The enemy has slain you. Try again.")
          setPowerReveal(`Enemy battle power: ${battle.computerTotalPower}. Player battle power: ${battle.playerTotalPower}`)
        }
    }

    

    return (
      <div id = "#battle-arena">
           <h1>Welcome to the Arena!</h1>
     <div class = "wrapper" id = "arenaGrid">
      <div id = "b1" className = "box a">
      <p id = "player-monster-battle"> Your monster: {playerMonster.monsterName}</p>
           <img ref = {playerMonsterRef} id = "player-monster-battle" src = {playerMonster.monsterImage} height = "100" width = "100"></img>
      </div>
      <div id = "b2" className = "box b">
      <h3>Weather: {weather.weatherName}</h3>
      <img id = "weather-battle" src = {weather.weatherImage}
          height = "100" width = "200"></img>
      </div>
      <div id = "b3" className = "box c">
      <p id = "computer-monster-battle"> Enemy monster: {computerMonster.monsterName}</p>
      <img ref = {computerMonsterRef} id = "computer-monster-battle" src = {computerMonster.monsterImage} height = "100" width = "100"></img>
      </div>
      <div id = "b4" className = "box d">
          <div>
             <p>Player monster element: {props.getElementName(playerElementId)}</p>
             <img src = {playerElement.elementImage} height = "50" width = "50"></img>

             <p>Player equipment: {playerEquipment.equipmentName}</p>
             <img src = {playerEquipment.equipmentImage} height = "50" width = "50"></img>
             <p>Equipment affinity: {props.getAffinityName(playerAffinityId)}</p>
             <img src = {playerAffinity.affinityImage} height = "50" width = "50"></img>

             <p></p>
           </div>
      </div>
      <div id = "b5" className = "box e">
      <h4>Location: {location.locationName} </h4>

      <img id = "location-battle" src = {location.locationImage}
      height = "200" width = "400" ></img>
      </div>
  <p>    </p>
<div id = "b6" className = "box f">
      <div>
             <p>Enemy monster element: {props.getElementName(computerElementId)}</p>
             <img src = {enemyElement.elementImage} height = "50" width = "50"></img>
             <p>Computer equipment: {computerEquipment.equipmentName}</p>
            <img src = {computerEquipment.equipmentImage} height = "50" width = "50"></img>

             <p>Equipment affinity: {props.getAffinityName(computerAffinityId)}</p>
             <img src = {enemyAffinity.affinityImage} height = "50" width = "50"></img>

           </div>
      </div>
      </div>
      <div id = "b7" className = "box g">
      <button id = "start-battle-button" onClick = {displayWinner}>SEE WHO WON!</button>
 </div>
      <div id = "b8" class = "box h">
          <div className = "winner-reveal">

              <div>{winnerReveal}</div>
              <div>{powerReveal}</div>
          </div>
      </div>

      <div id = "b9" className = "box j">
      <a className = "btn btn-secondary" href = "/battle" >Exit Arena</a>

      </div>
      </div>

      
        

    
           
            
       
    )

    
}