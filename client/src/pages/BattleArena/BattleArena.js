import React, { useEffect, useState, useRef, useContext } from "react";
import gsap from "gsap";
import PlayerMonsterContext from '../../context/PlayerMonsterContext';

export default function BattleArena(){

    const [battle, setBattle] = useState("");
    const [weather, setWeather] = useState("");
    const [location, setLocation] = useState("");
    const [computerMonster, setComputerMonster] = useState("");
    const [computerEquipment, setComputerEquipment] = useState("");   
    const [winnerReveal, setWinnerReveal] = useState("");

    const playerMonster = localStorage.getItem("playerMonster")
    const playerEquipment = localStorage.getItem("playerEquipment")
    const playerMonsterRef = useRef(null)
    const computerMonsterRef = useRef(null)

    // const getBattle = () => {
    //     fetch("http://localhost:8080/api/battle/findWinner", {
    //       method: "POST",
    //       headers: {
    //         "Content-Type": "application/json",
    //           "Accept": "application/json"
    //       },
    //       body: 
    //         JSON.stringify(playerMonster),
    //         JSON.stringify(playerEquipment),
    //       }
    //     })

        
    // }
    

    const displayWinner = () => {
        setWinnerReveal("The winner is!");
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
            <h2>{playerMonster}</h2>
            <h3>{playerEquipment}</h3>
            <h3>The weather for today's battle:</h3>
            <img id = "weather-battle" src = "https://www.publicdomainpictures.net/pictures/390000/velka/baum-nacht-schnee-winter-1613641634iKr.jpg" 
            alt = "Blizzard" height = "100" width = "200"></img>

            <h4>Today's battle will take place at: </h4>
            <img id = "location-battle" src = "https://www.publicdomainpictures.net/pictures/90000/velka/house-illustration-clipart.jpg" 
            alt = "House" height = "200" width = "200"></img>

            <p id = "computer-monster-battle">Computer monster:</p>
            <img ref = {computerMonsterRef} id = "computer-monster-battle" src = "https://app.pixelencounter.com/api/basic/monsters/random" height = "100" width = "100"></img>

            <p id = "player-monster-battle">Player monster:</p>
            <img ref = {playerMonsterRef} id = "player-monster-battle" src = "https://app.pixelencounter.com/api/basic/monsters/random" height = "100" width = "100"></img>

            <button id = "start-battle-button" onClick = {displayWinner}>START BATTLE!</button>

            <div class = "winner-reveal">
                <div>{winnerReveal}</div>
            </div>
        </div>
    )

    
}