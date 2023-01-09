import React, {useState} from 'react';
import jwtDecode from 'jwt-decode';
import { useHistory } from 'react-router-dom';

export default function LoginPage({setLoggedInUserData}) {

    const[loginData, setLoginData] = useState({username: "", password: ""});

    const history = useHistory();


    function handleSubmit(evt) {
        evt.preventDefault();

        fetch("http://localhost:8080/api/security/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(loginData)            
        })
        .then((response) => {
            if (response.status === 200) {
                return response.json();
            }
            else if (response.status === 403) {
                alert("Invalid username/password combo");
            }
            else {
                //errors happened. handle it later
                
        }  

            
        })
        .then((jwtContainer) => {
            const jwt = jwtContainer.jwt;
            const decodedJwt = jwtDecode(jwt);
            console.log(decodedJwt);

            const fullLoginData = {
                token: jwt,
                userData: decodedJwt
            };

            localStorage.setItem("userData", JSON.stringify(fullLoginData));
            setLoggedInUserData(fullLoginData);
            history.push("/");
        })


    }

    function handleInputChange(evt) {
        const changedInput = evt.target;
        const loginDataCopy = {...loginData};

        loginDataCopy[changedInput.id] = changedInput.value;

        setLoginData(loginDataCopy);

    }
    return (
        <div>
        <h1>Monster Bash Login page</h1>
        <h2>Please enter your login information below</h2>
        <form onSubmit={handleSubmit}>
            <label htmlFor = "username">Username: </label>
                <input id = "username" value ={loginData.username} onChange ={handleInputChange}/>
            <label htmlFor = "password">Password:</label>
                <input type = "password" id = "password" value ={loginData.password} onChange ={handleInputChange}/>
            <button >Log in </button>
        </form>
        </div>
    )
}