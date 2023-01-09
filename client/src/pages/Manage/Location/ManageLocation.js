import React from "react";

export default function ManageLocation(){


    return (
        < >
        <h1>Manage the Location database here.</h1>
        <h2>Begin by viewing the current Locations or add a new one.</h2>
        <a href = "/manage/location/form" className = "btn btn-primary">Add a Location</a>
        <a href = "/manage/location/displayAll" className = "btn btn-secondary">Display all Locations</a>
        </>
        
    )

}