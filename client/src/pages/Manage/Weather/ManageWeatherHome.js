import React from 'react';

export default function ManageWeatherHome() {

    
    return (
        < >
        <h1>Manage the weather database here.</h1>
        <h2>Begin by viewing the current weathers or add a new one.</h2>
        <a href = "/manage/weather/form" className = "btn btn-primary">Add a Weather</a>
        <a href = "/manage/weather/displayAll" className = "btn btn-secondary">Display all Weather</a>
        </>
        
    )

}