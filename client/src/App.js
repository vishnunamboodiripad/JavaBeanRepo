import { Switch, BrowserRouter, Route } from 'react-router-dom';
import './App.css';
import NavBar from './components/NavBar';
import LoginPage from './pages/LoginPage';
import UserContext from './context/AuthContext';
import { useState } from 'react';
import HomePage from './pages/HomePage';
import ViewAll from './pages/ViewAll';
import StartBattle from './pages/BattleArena/StartBattle';
import ManageArena from './pages/Manage/ManageArena';
import ManageMonster from './pages/Manage/ManageMonster';
import ManageEquipment from './pages/Manage/ManageEquipment';
import ManageWeather from './pages/Manage/ManageWeather';
import ManageEnvironment from './pages/Manage/ManageEnvironment';


function App() {

  let currentUserData = localStorage.getItem("userData");

  if (currentUserData) {
    currentUserData = JSON.parse(currentUserData);
  }

  const [loggedInUserData, setLoggedInUserData] = useState(currentUserData);


  return (
    <div className="App">
      <UserContext.Provider value = {loggedInUserData}>
        <BrowserRouter>
          <NavBar setLoggedInUserData = {setLoggedInUserData}/>
          <Switch>
            <Route exact path = "/">
              <HomePage/>
            </Route>
            <Route exact path = "/login">
              <LoginPage setLoggedInUserData = {setLoggedInUserData}/>
            </Route>
            <Route exact path = "/viewAll">
              <ViewAll/>
            </Route>
            <Route exact path ="/battle">
              <StartBattle/>
            </Route>
            <Route exact path = "/manage">
              <ManageArena/>
            </Route>
            <Route exact path = "/manage/monster">
              <ManageMonster/>
            </Route>
            <Route exact path = "/manage/equipment">
              <ManageEquipment/>
            </Route>
            <Route exact path = "/manage/weather">
              <ManageWeather/>
            </Route>
            <Route exact path = "/manage/environment">
              <ManageEnvironment/>
            </Route>
          </Switch>
        </BrowserRouter>
    </UserContext.Provider>
    </div>
  );
}

export default App;
