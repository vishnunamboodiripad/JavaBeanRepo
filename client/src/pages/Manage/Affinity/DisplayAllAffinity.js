import React from 'react';
import { Link } from 'react-router-dom';

export default function DisplayAllAffinity(props){
    const doDelete = (id) => {
        
        if (!window.confirm("Are you sure?")) {
            return
        }
        fetch (`http://localhost:8080/api/delete/affinity/${id}` , {
            method: "DELETE"
        })
        .then((response) => {
            if (response.status === 404) {
              props.setErrors([`Couldn't find affinity ${id}, maybe try refreshing`])
            }
            props.getAllAffinity()
          })
    }

    return (
        <table class = "table table-hover table-dark">
            <thead key = "header">
                <tr>
                    <td>Name</td>
                    <td>Image</td>
                    <td>Edit</td>
                    <td>Delete</td>
                </tr>
            </thead>
            <tbody>
                {props.affinityList.length === 0 ? <tr>Please wait, table is loading</tr> : props.affinityList.map((affinity) => {
                    return (
                        <tr key = {affinity.affinityId}>
                            <td>{affinity.affinityName}</td>
                            <td><img id = "affinityImage"src={affinity.affinityImage} height = "100" width = "100"></img></td>
                            <td><Link to= {`/manage/affinity/edit/${affinity.affinityId}`}>Edit</Link></td>
                            <td><button onClick = {() => {doDelete(affinity.affinityId)}}>Delete</button></td>
                        </tr>
                    )
                })}
            </tbody>
        </table>
    )
}