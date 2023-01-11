import React from 'react';
import { Link } from 'react-router-dom';

export default function DisplayAllWeather(props){
    const doDelete = (id) => {
        if (!window.confirm("Are you sure?")) {
            return
        }
        fetch (`http://localhost:8080/api/delete/weather/${id}` , {
            method: "DELETE"
        })
        .then((response) => {
            if (response.status === 404) {
              props.setErrors([`Couldn't find weather ${id}, maybe try refreshing`])
            }
            props.getAllWeather()
          })
    }

    return (
        <table class = "table table-hover table-dark">
            <thead key = "header">
                <tr>
                    <td>Name</td>
                    <td>Image</td>
                    <td>Affinity</td>
                    <td>Edit</td>
                    <td>Delete</td>
                </tr>
            </thead>
            <tbody>
                {props.weatherList.length === 0 ? <tr>Please wait, table is loading</tr> : props.weatherList.map((weather) => {
                    return (
                        <tr key = {weather.weatherId}>
                            <td>{weather.weatherName}</td>
                            <td><img id = "weatherImage"src={weather.weatherImage}></img></td>
                            <td>{props.getAffinityName(weather.affinityId)}</td>
                            <td><Link to= {`/manage/weather/edit/${weather.weatherId}`}>Edit</Link></td>
                            <td><button onClick = {() => {doDelete(weather.weatherId)}}>Delete</button></td>
                        </tr>
                    )
                })}
            </tbody>
        </table>
    )
}