import React, { useEffect, useState, useContext, useRef } from "react";
import UserContext from "../context/AuthContext";
import {gsap} from "gsap";


export default function UserRecord(props) {

    const [userRecord, setUserRecord] = useState({userWins: "", userLosses: ""});
    const user = useContext(UserContext);
    
    let userId = 0
    if(user !== null) {
        userId = parseInt(user.userData.userId);
    }

    let leftMonsterRef1 = useRef(null);
    let rightMonsterRef1 = useRef(null);
    let leftMonsterRef2 = useRef(null);
    let rightMonsterRef2 = useRef(null);
    let leftMonsterRef3 = useRef(null);
    let rightMonsterRef3 = useRef(null);


    const getRecord = () => {
        fetch(`http://localhost:8080/api/findRecord/${userId}`, {
          method: "GET",
          headers: {
            "Content-Type": "application/json",
              "Accept": "application/json",
              Authorization: "Bearer " + user.token
          },
          
        })
        .then((response) => {
            if (response.status === 201) {
                return response.json()
            }
            else {
              }
        })
        .then((json) => {
            setUserRecord(json);
        })
    }

    const monsterMovement = () => {
        gsap.fromTo(
            [leftMonsterRef1.current],
            8,
            {x: -300, repeat: -1, yoyo: true},
            {x: 300, repeat: -1, yoyo: true}
        );
        gsap.fromTo(
            [leftMonsterRef2.current],
            8,
            {x: -300, repeat: -1, yoyo: true},
            {x: 300, repeat: -1, yoyo: true}
        );
        gsap.fromTo(
            [leftMonsterRef3.current],
            8,
            {x: -300, repeat: -1, yoyo: true},
            {x: 300, repeat: -1, yoyo: true}
        );
        gsap.fromTo(
            [rightMonsterRef1.current],
            8,
            {x: 300, repeat: -1, yoyo: true},
            {x: -300, repeat: -1, yoyo: true}
        );
        gsap.fromTo(
            [rightMonsterRef2.current],
            8,
            {x: 300, repeat: -1, yoyo: true},
            {x: -300, repeat: -1, yoyo: true}
        );
        gsap.fromTo(
            [rightMonsterRef3.current],
            8,
            {x: 300, repeat: -1, yoyo: true},
            {x: -300, repeat: -1, yoyo: true}
        );
    }
    useEffect(getRecord, [])
    useEffect(monsterMovement, [])
    return (
        <div>
            <h1>Hello {user.userData.sub}</h1>
            <h2>Player wins: {userRecord.userWins}</h2>
            <h2>Player losses: {userRecord.userLosses}</h2>
            
        <div id = "userRecordFlexBox" class ="flex-container">    
        <div>
        <img ref = {leftMonsterRef1} id = "userRecord-monster1" src = "https://app.pixelencounter.com/api/basic/monsters/30" height = "50" width = "50"></img>
        </div>
        <div>
        <img ref = {rightMonsterRef1} id = "userRecord-monster" src = "https://app.pixelencounter.com/api/basic/monsters/32" height = "50" width = "50"></img>
        </div>
        <div>
        <img ref = {leftMonsterRef2} id = "userRecord-monster" src = "https://app.pixelencounter.com/api/basic/monsters/35" height = "50" width = "50"></img>
        </div>
        <div>
        <img ref = {rightMonsterRef2} id = "userRecord-monster" src = "https://app.pixelencounter.com/api/basic/monsters/41" height = "50" width = "50"></img>
        </div>
        <div>
        <img ref = {leftMonsterRef3} id = "userRecord-monster" src = "https://app.pixelencounter.com/api/basic/monsters/48" height = "50" width = "50"></img>
        </div>
        <div>
        <img ref = {rightMonsterRef3} id = "userRecord-monster" src = "https://app.pixelencounter.com/api/basic/monsters/101" height = "50" width = "50"></img>
        </div>
        </div>
        </div>
    )
}