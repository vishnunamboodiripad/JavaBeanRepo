import React from "react";

export default function HomePage() {
    return (
        <div>
        <h1>Here's the page for starting a battle</h1>
        <a className = "btn btn-success" href = "/manage/monster" >Manage monster database</a>
        <a className = "btn btn-success" href = "/manage/equipment" >Manage equipment database</a>
        <a className = "btn btn-success" href = "/manage/weather" >Manage weather database</a>
        <a className = "btn btn-success" href = "/manage/environment" >Manage environment database</a>
        </div>
        
    )
}