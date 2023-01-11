import React, { useEffect, useState } from "react";
import { useHistory, useParams } from "react-router-dom";

export default function LocationForm(props){
    const [locationName, setLocationName] = useState("");
    const [locationImage, setLocationImage] = useState("");
    const [elementId, setElementId] = useState("");
    
    


    const params = useParams();
    const history = useHistory();


    const checkForPrePopulate = () => {
        if (params.id){
            const targetLocation = props.locationList.find((location) => {return location.locationId.toString() === params.id.toString()});
            setLocationName(targetLocation.locationName);
            setLocationImage(targetLocation.locationImage);
            setElementId(targetLocation.elementId);
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
        const newLocation = {
            locationName,
            locationImage,
            elementId,
            
        }
        fetch ("http://localhost:8080/api/add/location", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Accept": "application/json"
              },
            body: JSON.stringify(newLocation)
        })
        .then((result)=> {
            if (result.status === 201) {
                props.getAllLocation();
                clearForm();
                props.setErrors([]);
                history.push("/manage/location")

            }
            else {
                result.json().then((errors) => {
                    props.setErrors(errors)
                })
            }
        })

    }

    const update = () => {
        const updatedLocation = {
            locationName,
            locationImage,
            elementId,
            locationId: params.id
        }
        fetch (`http://localhost:8080/api/edit/location/${params.id}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
                "Accept": "application/json"
              },
              body : JSON.stringify(updatedLocation)
        })
        .then((result)=> {
            if (result.status === 204) {
                props.getAllLocation();
                clearForm();
                props.setErrors([]);
                history.push("/manage/location")

            }
            else {
                result.json().then((errors) => {
                  props.setErrors(errors)
                })
              }
            })
    }

    const clearForm = () => {
        setLocationName("")
        setLocationImage("")
        setElementId("")
        
    }
    const cancelEdit = () => {
        history.push("/manage/location")
        clearForm()
        props.setErrors("")
        
      }

    return (
        <>
        <h4>Enter the information for a new Location event in the form below</h4>
        <form onSubmit={(event)=>handleSubmit(event)}>
        <div id = "flexbox1" class ="flex-container">
            <div>
                <label htmlFor = "locationName">Location name: </label>
                <input type = "text" id = "locationName-input" value = {locationName} onChange = {(event) => {setLocationName(event.target.value)}}/>
            </div>
            <div>
                <label htmlFor = "locationImage">Link for Location image: </label>
                <input type = "text" id = "locationImage-input" value = {locationImage} onChange = {(event) => {setLocationImage(event.target.value)}}/>
            </div>
            <div>
                <label htmlFor = "elementId">Element:</label>
                <select id = "elementId-input" value = {elementId} onChange = {(event) => {setElementId(parseInt(event.target.value))}}>
                    <option selected value = "1">Fire</option>
                    <option  value = "2">Water</option>
                    <option  value = "3">Earth</option>
                    <option  value = "4">Wind</option>
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