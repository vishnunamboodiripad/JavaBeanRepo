import React from 'react';
import { Link } from 'react-router-dom';

export default function DisplayAllLocation(props){
    const doDelete = (id) => {
               if (!window.confirm("Are you sure?")) {
 
            return
        }
        fetch (`http://localhost:8080/api/delete/location/${id}` , {
            method: "DELETE"
        })
        .then((response) => {
            if (response.status === 404) {
              props.setErrors([`Couldn't find location ${id}, maybe try refreshing`])
            }
            props.getAllLocation()
          })
    }

    return (
        <table>
            <thead key = "header">
                <tr>
                    <td>Name</td>
                    <td>Image</td>
                    <td>Element</td>
                    <td>Edit</td>
                    <td>Delete</td>
                </tr>
            </thead>
            <tbody>
                {props.locationList.length === 0 ? <tr>Please wait, table is loading</tr> : props.locationList.map((location) => {
                    return (
                        <tr key = {location.locationId}>
                            <td>{location.locationName}</td>
                            <td><img id = "locationImage"src={location.locationImage}></img></td>
                            <td>{props.getElementName(location.elementId)}</td>
                            <td><Link to= {`/manage/location/edit/${location.locationId}`}>Edit</Link></td>
                            <td><button onClick = {() => {doDelete(location.locationId)}}>Delete</button></td>
                        </tr>
                    )
                })}
            </tbody>
        </table>
    )
}