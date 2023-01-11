import React, { useEffect, useState } from "react";
import { useHistory, useParams } from "react-router-dom";

export default function ManageWeather(props){
    const [weatherName, setWeatherName] = useState("");
    const [weatherImage, setWeatherImage] = useState("");
    const [affinityId, setAffinityId] = useState("");
    
    


    const params = useParams();
    const history = useHistory();


    const checkForPrePopulate = () => {
        if (params.id){
            const targetWeather = props.weatherList.find((weather) => {return weather.weatherId.toString() === params.id.toString()});
            setWeatherName(targetWeather.weatherName);
            setWeatherImage(targetWeather.weatherImage);
            setAffinityId(targetWeather.affinityId);
        }
    }

    useEffect(checkForPrePopulate, [])

    const handleSubmit = (event) => {{
        event.preventDefault()
        if (params.id !== undefined) {
            update()
        }
        else {
            create()
        }
    }}

    const create = () => {
        const newWeather = {
            weatherName,
            weatherImage,
            affinityId,
            
        }
        debugger
        fetch ("http://localhost:8080/api/add/weather", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Accept": "application/json"
              },
            body: JSON.stringify(newWeather)
        })
        .then((result)=> {
            if (result.status === 201) {
                props.getAll();
                clearForm();
                props.setErrors([]);
                history.push("/manage/weather")

            }
            else {
                result.json().then((errors) => {
                    props.setErrors(errors)
                })
            }
        })

    }

    const update = () => {
        debugger
        const updatedWeather = {
            weatherName,
            weatherImage,
            affinityId,
            weatherId: params.id
        }
        fetch (`http://localhost:8080/api/edit/weather/${params.id}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
                "Accept": "application/json"
              },
              body : JSON.stringify(updatedWeather)
        })
        .then((result)=> {
            if (result.status === 204) {
                props.getAll();
                clearForm();
                props.setErrors([]);
                history.push("/manage/weather")

            }
            else {
                result.json().then((errors) => {
                  props.setErrors(errors)
                })
              }
            })
    }

    const clearForm = () => {
        setWeatherName("")
        setWeatherImage("")
        setAffinityId("")
        
    }
    const cancelEdit = () => {
        history.push("/manage/weather")
        clearForm()
        props.setErrors("")
        
      }

    return (
        <>
        <h4>Enter the information for a new Weather event in the form below</h4>
        <form onSubmit={(event)=>handleSubmit(event)}>
        <div id = "flexbox1" class ="flex-container">
            <div class="form-group">
                <label htmlFor = "weatherName">Weather name: </label>
                <input type = "text" id = "weatherName-input" value = {weatherName} onChange = {(event) => {setWeatherName(event.target.value)}}/>
            </div>
            <div>
                <label htmlFor = "weatherImage">Link for weather Image: </label>
                <input type = "text" id = "weatherImage-input" value = {weatherImage} onChange = {(event) => {setWeatherImage(event.target.value)}}/>
            </div>
            <div>
                <label htmlFor = "affinityId">Affinity:</label>
                <select id = "affinityId-input" value = {affinityId} onChange = {(event) => {setAffinityId(parseInt(event.target.value))}}>
                    <option  value = '1'>Electric</option>
                    <option  value = "2">Liquid</option>
                    <option  value = "3">Stone</option>
                    <option  value = "4">Flame</option>
                    <option  value = "5">Snow</option>
                    <option  value = "6">Breeze</option>
                </select>
            </div>
            <input type = "submit" value = {params.id ? "Update!" : "Create!"}/>
            {params.id !== undefined ? <button onClick = {() => {cancelEdit()}}>Cancel Edit</button>
            : null}
        </div>
        </form>
        </>
    )
}