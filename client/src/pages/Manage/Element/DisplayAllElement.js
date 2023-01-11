import React from 'react';
import { Link } from 'react-router-dom';

export default function DisplayAllElement(props){
    const doDelete = (id) => {
        
        if (!window.confirm("Are you sure?")) {
            return
        }
        fetch (`http://localhost:8080/api/delete/element/${id}` , {
            method: "DELETE"
        })
        .then((response) => {
            if (response.status === 404) {
              props.setErrors([`Couldn't find element ${id}, maybe try refreshing`])
            }
            props.getAllElement()
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
                {props.elementList.length === 0 ? <tr>Please wait, table is loading</tr> : props.elementList.map((element) => {
                    return (
                        <tr key = {element.elementId}>
                            <td>{element.elementName}</td>
                            <td><img id = "elementImage"src={element.elementImage}></img></td>
                            <td><Link to= {`/manage/element/edit/${element.elementId}`}>Edit</Link></td>
                            <td><button onClick = {() => {doDelete(element.elementId)}}>Delete</button></td>
                        </tr>
                    )
                })}
            </tbody>
        </table>
    )
}