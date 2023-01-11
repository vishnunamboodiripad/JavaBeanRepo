import React from "react";

export default function ManageAffinity(){
    

    return (
        <>
        <h1>Manage the Affinity database here.</h1>
        <h2>Begin by viewing the current Affinity or add a new one.</h2>

        <div id = "flexbox1" class ="flex-container">
            <div>
        <a href = "/manage/affinity/form" className = "btn btn-primary">Add an Affinity</a>
            </div>
            <div>
        <a href = "/manage/affinity/displayAll" className = "btn btn-secondary">Display all Affinities</a>
            </div>
        </div>
        
        </>
        
    )
}