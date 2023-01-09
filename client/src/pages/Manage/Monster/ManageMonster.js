import React from "react";

export default function ManageMonster(){


    return (
        < >
        <h1>Manage the Monster database here.</h1>
        <h2>Begin by viewing the current Monsters or add a new one.</h2>
        <a href = "/manage/monster/form" className = "btn btn-primary">Add a Monster</a>
        <a href = "/manage/monster/displayAll" className = "btn btn-secondary">Display all Monsters</a>
        </>
        
    )
}