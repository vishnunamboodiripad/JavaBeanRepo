import React from 'react';

export default function ManageWeatherHome() {

    
    return (
        <main>
        <h1>Manage the weather database here.</h1>
        <h2>Begin by viewing the current weathers or add a new one.</h2>

        <div id = "flexbox1" class ="flex-container">
            <div>
        <a href = "/manage/weather/form" className = "btn btn-primary">Add a Weather</a>
            </div>
            <div>
        <a href = "/manage/weather/displayAll" className = "btn btn-secondary">Display all Weather</a>
            </div>
        </div>
        </main>
        
    )

}