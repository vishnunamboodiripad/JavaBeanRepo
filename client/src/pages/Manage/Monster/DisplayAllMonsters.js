import React from 'react';
import { Link } from 'react-router-dom';

export default function DisplayAllMonster(props){
    const doDelete = (id) => {
               if (!window.confirm("Are you sure?")) {
            return
        }
        fetch (`http://localhost:8080/api/delete/monster/${id}` , {
            method: "DELETE"
        })
        .then((response) => {
            if (response.status === 404) {
              props.setErrors([`Couldn't find monster ${id}, maybe try refreshing`])
            }
            props.getAllMonster()
          })
    }
    
    return (

        
        <table id = "#displayMonster"class = "table table-hover table-dark">
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
                {props.monsterList.length === 0 ? <tr>Please wait, table is loading</tr> : props.monsterList.map((monster) => {
                    return (
                        <tr key = {monster.monsterId}>
                            <td>{monster.monsterName}</td>

                            <td><img id = "monsterImage"src={monster.monsterImage} height = "100" width = "100"></img></td>
                            <td>{props.getElementName(monster.elementId)}</td>

                            <td><Link to= {`/manage/monster/edit/${monster.monsterId}`}>Edit</Link></td>
                            <td><button onClick = {() => {doDelete(monster.monsterId)}}>Delete</button></td>
                        </tr>
                    )
                })}
            </tbody>
        </table>
     
    )
}