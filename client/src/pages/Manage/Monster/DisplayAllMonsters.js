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

    const getElementName = (elementId) => {
        let elementName = "";
            switch(elementId) {
                case 1:
                    elementName = "Fire";
                    break;
                case 2:
                    elementName = "Water";
                    break;
                case 3:
                    elementName = "Earth";
                    break;
                case 4:
                    elementName = "Wind";
                    break;
            }
        return elementName;
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
                            <td><img id = "monsterImage"src={monster.monsterImage} width="100" height="100"></img></td>
                            <td>{getElementName(monster.elementId)}</td>
                            <td><Link to= {`/manage/monster/edit/${monster.monsterId}`}>Edit</Link></td>
                            <td><button onClick = {() => {doDelete(monster.monsterId)}}>Delete</button></td>
                        </tr>
                    )
                })}
            </tbody>
        </table>
     
    )
}