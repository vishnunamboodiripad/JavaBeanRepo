import React, {useContext} from 'react';
import UserContext from '../context/AuthContext';

export default function NavBar({setLoggedInUserData}) {

    const user = useContext(UserContext);

    function handleLogOut() {
        localStorage.removeItem("userData");
        setLoggedInUserData(null);
    }

    

    return (
    <nav className="navbar navbar-expand-lg navbar-light bg-light">
        <a className="navbar-brand" href="#">Navbar w/ text</a>
            <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
                <span className="navbar-toggler-icon"></span>
            </button>
        <div className="collapse navbar-collapse" id="navbarText">
            <ul className="navbar-nav mr-auto">
                <li className="nav-item active">
                    <a className="nav-link" href="/">Home <span className="sr-only">(current)</span></a>
                </li>
                <li className="nav-item">
                    <a className="nav-link" href="/viewAll">ViewAllMonsters</a>
                </li>
                <li className="nav-item">
                     <a className="nav-link" href="#">Pricing</a>
                </li>
             </ul>
            <span className="navbar-text">
                Navbar text with an inline element
            </span>
            {user ? <a className = "btn btn-danger" onClick = {handleLogOut}>Log Out {user.userData.sub}</a>  : <a className = "btn btn-primary float-right" href ="/login">Login</a>}
            
        </div>
    </nav>
    );
}