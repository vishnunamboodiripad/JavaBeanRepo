import { Switch, BrowserRouter, Route } from 'react-router-dom';
import './App.css';
import NavBar from './components/NavBar';
import LoginPage from './pages/LoginPage';
import UserContext from './context/AuthContext';
import { useState } from 'react';


function App() {

  let currentUserData = localStorage.getItem("userData");

  if (currentUserData) {
    currentUserData = JSON.parse(currentUserData);
  }

  const [loggedInUserData, setLoggedInUserData] = useState(currentUserData);


  return (
    <div className="App">
      <UserContext.Provider value = {loggedInUserData}>
      <h1>Hello from react</h1>
    

        <BrowserRouter>
          <NavBar setLoggedInUserData = {setLoggedInUserData}/>
          <Switch>
            <Route exact path = "/">
              <h1>Homepage</h1>
            </Route>
            <Route exact path = "/login">
              <LoginPage setLoggedInUserData = {setLoggedInUserData}/>
            </Route>
            <Route exact path = "/viewAll">
              <h1>Page to view all the monsters</h1>
            </Route>
          </Switch>
        </BrowserRouter>
    </UserContext.Provider>
    </div>
  );
}

export default App;
