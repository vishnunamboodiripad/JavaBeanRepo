import React, { useEffect, useState } from "react";

export default function BattleArena(){

    const [battle, setBattle] = useState("");
    const [weather, setWeather] = useState("");
    const [location, setLocation] = useState("");
    const [computerMonster, setComputerMonster] = useState("");
    const [computerEquipment, setComputerEquipment] = useState("");   
    const [winnerReveal, setWinnerReveal] = useState("");

   

    const displayWinner = () => {
        setWinnerReveal("The winner is!");
    }
    return (
        <div id = "#battle-arena">
            <h1>Welcome to the Arena!</h1>
            <h3>The weather for today's battle:</h3>
            <img id = "weather-battle" src = "https://www.publicdomainpictures.net/pictures/390000/velka/baum-nacht-schnee-winter-1613641634iKr.jpg" 
            alt = "Blizzard" height = "100" width = "200"></img>

            <h4>Today's battle will take place at: </h4>
            <img id = "location-battle" src = "https://www.publicdomainpictures.net/pictures/90000/velka/house-illustration-clipart.jpg" 
            alt = "House" height = "200" width = "200"></img>

            <p id = "computer-monster-battle">Computer monster:</p>
            <img id = "computer-monster-battle" src = "https://app.pixelencounter.com/api/basic/monsters/random" height = "100" width = "100"></img>

            <p id = "player-monster-battle">Player monster:</p>
            <img id = "player-monster-battle" src = "https://app.pixelencounter.com/api/basic/monsters/random" height = "100" width = "100"></img>

            <button id = "start-battle-button" onClick = {displayWinner}>START BATTLE!</button>

            <p>{winnerReveal}</p>
        </div>
    )
}