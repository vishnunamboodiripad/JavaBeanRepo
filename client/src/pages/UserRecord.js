import React, { useEffect, useState, useContext } from "react";
import UserContext from "../context/AuthContext";

export default function UserRecord(props) {

    const [userRecord, setUserRecord] = useState({userWins: "", userLosses: ""});
    const user = useContext(UserContext);
    
    let userId = 0
    if(user !== null) {
        userId = parseInt(user.userData.userId);
    }

    const getRecord = () => {
        fetch(`http://localhost:8080/api/findRecord/${userId}`, {
          method: "GET",
          headers: {
            "Content-Type": "application/json",
              "Accept": "application/json"
          },
        })
        .then((response) => {
            if (response.status === 201) {
                return response.json()
            }
            else {
                console.log(response)
              }
        })
        .then((json) => {
            setUserRecord(json);
        })
    }


    useEffect(getRecord, [])
    return (
        <div>
            <h1>Hello {user.userData.sub}</h1>
            <h2>Player wins: {userRecord.userWins}</h2>
            <h2>Player losses: {userRecord.userLosses}</h2>
        </div>
    )
}