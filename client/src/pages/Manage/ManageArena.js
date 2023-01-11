import React from "react";

export default function HomePage() {
    return (
        <div>
        <h1>Manage your Databases</h1>
        <div id = "flexbox1" class ="flex-container">
            <div>
        <a className = "btn btn-success" href = "/manage/monster" >Manage monster database</a>
        </div>
       <div>
        <a className = "btn btn-success" href = "/manage/equipment" >Manage equipment database</a>
        </div>
        <div>
        <a className = "btn btn-success" href = "/manage/weather" >Manage weather database</a>
        </div>
        <div>
        <a className = "btn btn-success" href = "/manage/location" >Manage location database</a>
        </div>
        </div>
        </div>
    

        
    )
}