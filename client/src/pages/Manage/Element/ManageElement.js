import React from "react";

export default function ManageElement(){
    

    return (
        <>
        <h1>Manage the Element database here.</h1>
        <h2>Begin by viewing the current Element or add a new one.</h2>

        <div id = "flexbox1" class ="flex-container">
            <div>
        <a href = "/manage/element/form" className = "btn btn-primary">Add an Element</a>
            </div>
            <div>
        <a href = "/manage/element/displayAll" className = "btn btn-secondary">Display all Elements</a>
            </div>
        </div>
        
        </>
        
    )
}