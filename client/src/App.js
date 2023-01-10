import { Switch, BrowserRouter, Route } from 'react-router-dom';
import './App.css';
import NavBar from './components/NavBar';
import LoginPage from './pages/LoginPage';
import UserContext from './context/AuthContext';
import { useState, useEffect } from 'react';
import HomePage from './pages/HomePage';
import ViewAll from './pages/ViewAll';
import StartBattle from './pages/BattleArena/StartBattle';
import BattleArena from './pages/BattleArena/BattleArena';
import ManageArena from './pages/Manage/ManageArena';
import ManageMonster from './pages/Manage/Monster/ManageMonster';
import ManageEquipment from './pages/Manage/Equipment/ManageEquipment';
import ManageLocation from './pages/Manage/Location/ManageLocation';
import ManageWeatherHome from './pages/Manage/Weather/ManageWeatherHome';
import WeatherForm from './pages/Manage/Weather/WeatherForm';
import DisplayAllWeather from './pages/Manage/Weather/DisplayAllWeather';
import LocationForm from './pages/Manage/Location/LocationForm';
import DisplayAllLocation from './pages/Manage/Location/DisplayAllLocations';
import EquipmentForm from './pages/Manage/Equipment/EquipmentForm';
import DisplayAllEquipment from './pages/Manage/Equipment/DisplayAllEquipment';
import MonsterForm from './pages/Manage/Monster/MonsterForm';
import DisplayAllMonster from './pages/Manage/Monster/DisplayAllMonsters';
import Animationdummy from './pages/BattleArena/Animationdummy';



function App() {

  let currentUserData = localStorage.getItem("userData");

  if (currentUserData) {
    currentUserData = JSON.parse(currentUserData);
  }

  const [loggedInUserData, setLoggedInUserData] = useState(currentUserData);
  const [weatherList, setWeatherList] = useState("");
  const [monsterList, setMonsterList] = useState([]);
  const [errors, setErrors] = useState([]);
  const [locationList, setLocationList] = useState("");
  const [equipmentList, setEquipmentList] = useState("");

  const getAllMonster = () => {
    fetch("http://localhost:8080/api/monster/viewAll",
      {method: "GET"})
    .then((response) => {
      return response.json()
    })
    .then((json) => {setMonsterList(json)})
    }

  const getAllWeather = () => {
      fetch("http://localhost:8080/api/weather/viewAll" , 
          {method: "GET"})
      .then((response) => {
          return response.json()
      })
      .then((json) => {setWeatherList(json)})      
    }

  const getAllLocation = () => {
      fetch("http://localhost:8080/api/location/viewAll" , 
        {method: "GET"})
      .then((response) => {
        return response.json()
      })
      .then((json) => {setLocationList(json)})
    }

    const getAllEquipment = () => {
      fetch("http://localhost:8080/api/equipment/viewAll" , 
        {method: "GET"})
      .then((response) => {
        return response.json()
      })
      .then((json) => {setEquipmentList(json)})
    }

    useEffect(getAllWeather, [])
    useEffect(getAllLocation, [])
    useEffect(getAllEquipment, [])
    useEffect(getAllMonster, [])

  let monsterImages = [];

  const getImagesFromApi = () =>{
    fetch("")
  }
    
  return (
    <div className="App">
      <UserContext.Provider value = {loggedInUserData}>
        <BrowserRouter>
          <NavBar setLoggedInUserData = {setLoggedInUserData}/>
          <section id = "errors">
                 {
                    errors.length > 0 ? <ul>
                    {errors.map((error) => {return <li key ={error}>{error}</li>})}
                 </ul>
                    : ""
                    }

            </section>
          <Switch>
            <Route exact path = "/">
              <HomePage/>
            </Route>
            <Route exact path = "/login">
              <LoginPage setLoggedInUserData = {setLoggedInUserData}/>
            </Route>
            <Route exact path = "/viewAll">
              <ViewAll monsterList = {monsterList} getAllMonster = {getAllMonster}/>
            </Route>
            <Route exact path ="/battle">
              <StartBattle monsterList = {monsterList} getAllMonster = {getAllMonster} getAllEquipment={getAllEquipment} equipmentList = {equipmentList}/>
            </Route>
            <Route exact path = "/battle/arena">
              <BattleArena></BattleArena>
            </Route>
            <Route exact path = "/manage">
              <ManageArena/>
            </Route>
            <Route exact path = "/manage/monster">
              <ManageMonster/>
            </Route>
            <Route exact path = {["/manage/monster/form", "/manage/monster/edit/:id"]}>
              <MonsterForm monsterList = {monsterList} getAllMonster = {getAllMonster} setErrors = {setErrors}/>
            </Route>
            <Route exact path = "/manage/monster/displayAll">
              <DisplayAllMonster monsterList = {monsterList} getAllMonster = {getAllMonster} setErrors = {setErrors}/>
            </Route>
            <Route exact path = "/manage/equipment">
              <ManageEquipment/>
            </Route>
            <Route exact path = {["/manage/equipment/form", "/manage/equipment/edit/:id"]}>
                <EquipmentForm equipmentList = {equipmentList} getAllEquipment = {getAllEquipment} setErrors = {setErrors}/>
            </Route>
            <Route exact path = "/manage/equipment/displayAll">
                <DisplayAllEquipment equipmentList = {equipmentList} getAllEquipment = {getAllEquipment} setErrors = {setErrors}/>
            </Route>
            <Route exact path = "/manage/weather">
              <ManageWeatherHome/>
            </Route>
            <Route exact path = {["/manage/weather/form", "/manage/weather/edit/:id"] }>
              <WeatherForm weatherList = {weatherList} getAllWeather = {getAllWeather} setErrors = {setErrors}/>
            </Route>
            <Route exact path = "/manage/weather/displayAll">
              <DisplayAllWeather getAllWeather = {getAllWeather} setErrors = {setErrors} weatherList = {weatherList}/>
            </Route>
            <Route exact path = "/manage/location">
              <ManageLocation/>
            </Route>
            <Route exact path = {["/manage/location/form", "/manage/location/edit/:id"] }>
              <LocationForm locationList = {locationList} getAllLocation = {getAllLocation} setErrors = {setErrors}/>
            </Route>
            <Route exact path = "/manage/location/displayAll">
              <DisplayAllLocation locationList = {locationList} getAllLocation = {getAllLocation} setErrors = {setErrors}/>
            </Route>
            <Route exact path = "/animation">
              <Animationdummy/>
            </Route>
          </Switch>
        </BrowserRouter>
    </UserContext.Provider>
    </div>
  );
}

export default App;
