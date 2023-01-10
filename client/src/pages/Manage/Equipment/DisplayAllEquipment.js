import React from 'react';
import { Link } from 'react-router-dom';

export default function DisplayAllEquipment(props){
    const doDelete = (id) => {
        debugger
        if (!window.confirm("Are you sure?")) {
            return
        }
        fetch (`http://localhost:8080/api/delete/equipment/${id}` , {
            method: "DELETE"
        })
        .then((response) => {
            if (response.status === 404) {
              props.setErrors([`Couldn't find equipment ${id}, maybe try refreshing`])
            }
            props.getAllEquipment()
          })
    }

    const getAffinityName = (affinityId) => {
        let affinityName = "";
            switch(affinityId) {
                case 1:
                    affinityName = "Electric";
                    break;
                case 2:
                    affinityName = "Liquid";
                    break;
                case 3:
                    affinityName = "Stone";
                    break;
                case 4:
                    affinityName = "Flame";
                    break;
                case 5:
                    affinityName = "Snow";
                    break;
                case 6:
                    affinityName = "Breeze";
                    break;
            }
        return affinityName;
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
                {props.equipmentList.length === 0 ? <tr>Please wait, table is loading</tr> : props.equipmentList.map((equipment) => {
                    return (
                        <tr key = {equipment.equipmentId}>
                            <td>{equipment.equipmentName}</td>
                            <td><img id = "equipmentImage"src={equipment.equipmentImage}></img></td>
                            <td>{getAffinityName(equipment.affinityId)}</td>
                            <td><Link to= {`/manage/equipment/edit/${equipment.equipmentId}`}>Edit</Link></td>
                            <td><button onClick = {() => {doDelete(equipment.equipmentId)}}>Delete</button></td>
                        </tr>
                    )
                })}
            </tbody>
        </table>
    )
}