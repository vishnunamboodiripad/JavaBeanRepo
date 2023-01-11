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
import AffinityForm from './pages/Manage/Affinity/AffinityForm';
import DisplayAllAffinity from './pages/Manage/Affinity/DisplayAllAffinity';
import ManageAffinity from './pages/Manage/Affinity/ManageAffinity';
import ManageElement from './pages/Manage/Element/ManageElement';
import ElementForm from './pages/Manage/Element/ElementForm';
import DisplayAllElement from './pages/Manage/Element/DisplayAllElement';
function App() {

  let currentUserData = localStorage.getItem("userData");

  if (currentUserData) {
    currentUserData = JSON.parse(currentUserData);
  }

  let currentPlayerMonster = localStorage.getItem("playerMonster");

  const [playerMonsterData, setPlayerMonsterData] = useState(currentPlayerMonster);
  const [loggedInUserData, setLoggedInUserData] = useState(currentUserData);
  const [weatherList, setWeatherList] = useState([]);
  const [monsterList, setMonsterList] = useState([]);
  const [errors, setErrors] = useState([]);
  const [locationList, setLocationList] = useState([]);
  const [equipmentList, setEquipmentList] = useState([]);
  const [affinityList, setAffinityList] = useState([]);
  const [elementList, setElementList] = useState([]);

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
    const getAllAffinity = () => {
      fetch("http://localhost:8080/api/affinity/viewAll" , 
          {method: "GET"})
      .then((response) => {
          return response.json()
      })
      .then((json) => {setAffinityList(json)})      
    }
     const getAllElement = () => {
       fetch("http://localhost:8080/api/element/viewAll" , 
           {method: "GET"})
       .then((response) => {
         return response.json()
       })
       .then((json) => {setElementList(json)})      
     }

    useEffect(getAllWeather, [])
    useEffect(getAllLocation, [])
    useEffect(getAllEquipment, [])
    useEffect(getAllMonster, [])
    useEffect(getAllAffinity, [])
    useEffect(getAllElement, [])



    const getElementName = (elementId) => {
      let elementName = "";
          switch(elementId) {
              case 1:
                  elementName = "Fire";
                  break;
              case 2:
                  elementName = "Water";
                  break;
              case 3:
                  elementName = "Earth";
                  break;
              case 4:
                  elementName = "Wind";
                  break;
          }
      return elementName;
  }
  const getAffinityName = (affinityId) => {
    let affinityName = "";
        switch(affinityId) {
            case 1:
                affinityName = "Electric";
                break;
            case 2:
                affinityName = "Liquid";
                break;
            case 3:
                affinityName = "Stone";
                break;
            case 4:
                affinityName = "Flame";
                break;
            case 5:
                affinityName = "Snow";
                break;
            case 6:
                affinityName = "Breeze";
                break;
        }
    return affinityName;
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
              <StartBattle getAffinityName = {getAffinityName} getElementName = {getElementName} monsterList = {monsterList} getAllMonster = {getAllMonster} getAllEquipment={getAllEquipment} equipmentList = {equipmentList}/>
            </Route>
            <Route exact path = "/battle/arena">
              <BattleArena getAffinityName = {getAffinityName} getElementName = {getElementName}setErrors = {setErrors}></BattleArena>
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
              <DisplayAllMonster getElementName = {getElementName} monsterList = {monsterList} getAllMonster = {getAllMonster} setErrors = {setErrors}/>
            </Route>
            <Route exact path = "/manage/equipment">
              <ManageEquipment/>
            </Route>
            <Route exact path = {["/manage/equipment/form", "/manage/equipment/edit/:id"]}>
                <EquipmentForm equipmentList = {equipmentList} getAllEquipment = {getAllEquipment} setErrors = {setErrors}/>
            </Route>
            <Route exact path = "/manage/equipment/displayAll">
                <DisplayAllEquipment getAffinityName = {getAffinityName} equipmentList = {equipmentList} getAllEquipment = {getAllEquipment} setErrors = {setErrors}/>
            </Route>
            <Route exact path = "/manage/weather">
              <ManageWeatherHome/>
            </Route>
            <Route exact path = {["/manage/weather/form", "/manage/weather/edit/:id"] }>
              <WeatherForm weatherList = {weatherList} getAllWeather = {getAllWeather} setErrors = {setErrors}/>
            </Route>
            <Route exact path = "/manage/weather/displayAll">
              <DisplayAllWeather getAffinityName = {getAffinityName}getAllWeather = {getAllWeather} setErrors = {setErrors} weatherList = {weatherList}/>
            </Route>
            <Route exact path = "/manage/location">
              <ManageLocation/>
            </Route>
            <Route exact path = {["/manage/location/form", "/manage/location/edit/:id"] }>
              <LocationForm locationList = {locationList} getAllLocation = {getAllLocation} setErrors = {setErrors}/>
            </Route>
            <Route exact path = "/manage/location/displayAll">
              <DisplayAllLocation getElementName = {getElementName} locationList = {locationList} getAllLocation = {getAllLocation} setErrors = {setErrors}/>
            </Route>
            <Route exact path = "/manage/affinity">
              <ManageAffinity/>
            </Route>
            <Route exact path = {["/manage/affinity/form", "/manage/affinity/edit/:id"]}>
              <AffinityForm affinityList = {affinityList} getAllAffinity= {getAllAffinity} setErrors = {setErrors}/>
            </Route>
            <Route exact path = "/manage/affinity/displayAll">
              <DisplayAllAffinity affinityList = {affinityList} getAllAffinity = {getAllAffinity} setErrors = {setErrors}/>
            </Route>

            <Route exact path = "/manage/element">
              <ManageElement/>
            </Route>
            <Route exact path = {["/manage/element/form", "/manage/element/edit/:id"]}>
              <ElementForm elementList = {elementList} getAllElement= {getAllElement} setErrors = {setErrors}/>
            </Route>
            <Route exact path = "/manage/element/displayAll">
              <DisplayAllElement elementList = {elementList} getAllElement = {getAllElement} setErrors = {setErrors}/>
            </Route>
            
          </Switch>
        </BrowserRouter>
    </UserContext.Provider>
    </div>
  );
}

export default App;
