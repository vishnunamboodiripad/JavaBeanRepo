import React from "react";

export default function ManageElement(){
    

    return (
        < >
        <h1>Manage the Element database here.</h1>
        <h2>Begin by viewing the current Elements or add a new one.</h2>
        <a href = "/manage/element/form" className = "btn btn-primary">Add an Element</a>
        <a href = "/manage/element/displayAll" className = "btn btn-secondary">Display all Elements</a>
        </>
        
    )
}