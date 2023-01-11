import React, { useEffect, useState } from "react";
import { useHistory, useParams } from "react-router-dom";

export default function EquipmentForm(props){
    const [equipmentName, setEquipmentName] = useState("");
    const [equipmentImage, setEquipmentImage] = useState("");
    const [affinityId, setAffinityId] = useState("");
    const [equipmentStrength, setEquipmentStrength] = useState("");
    
    


    const params = useParams();
    const history = useHistory();


    const checkForPrePopulate = () => {
        if (params.id){
            const targetEquipment = props.equipmentList.find((equipment) => {return equipment.equipmentId.toString() === params.id.toString()});
            setEquipmentName(targetEquipment.equipmentName);
            setEquipmentImage(targetEquipment.equipmentImage);
            setAffinityId(targetEquipment.affinityId);
            setEquipmentStrength(targetEquipment.strength)
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
        const newEquipment = {
            equipmentName,
            equipmentImage,
            affinityId,
            equipmentStrength,
            
        }
        debugger
        fetch ("http://localhost:8080/api/add/equipment", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Accept": "application/json"
              },
            body: JSON.stringify(newEquipment)
        })
        .then((result)=> {
            if (result.status === 201) {
                props.getAll();
                clearForm();
                props.setErrors([]);
                history.push("/manage/equipment")

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
        const updatedEquipment = {
            equipmentName,
            equipmentImage,
            affinityId,
            equipmentStrength,
            equipmentId: params.id
        }
        fetch (`http://localhost:8080/api/edit/equipment/${params.id}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
                "Accept": "application/json"
              },
              body : JSON.stringify(updatedEquipment)
        })
        .then((result)=> {
            if (result.status === 204) {
                props.getAll();
                clearForm();
                props.setErrors([]);
                history.push("/manage/equipment")

            }
            else {
                result.json().then((errors) => {
                  props.setErrors(errors)
                })
              }
            })
    }

    const clearForm = () => {
        setEquipmentName("")
        setEquipmentImage("")
        setAffinityId("")
        setEquipmentStrength(0)
        
    }
    const cancelEdit = () => {
        history.push("/manage/equipment")
        clearForm()
        props.setErrors("")
        
      }

    return (
        <>
        <h4>Enter the information for a new Equipment in the form below</h4>
        <form onSubmit={(event)=>handleSubmit(event)}>
        <div id = "flexbox1" class ="flex-container">

            <div>
                <label htmlFor = "equipmentName">Equipment name: </label>
                <input type = "text" id = "equipmentName-input" value = {equipmentName} onChange = {(event) => {setEquipmentName(event.target.value)}}/>
            </div>
            <div>
                <label htmlFor = "equipmentImage">Link for equipment Image: </label>
                <input type = "text" id = "equipmentImage-input" value = {equipmentImage} onChange = {(event) => {setEquipmentImage(event.target.value)}}/>
            </div>
            <div>
                <label htmlFor = "equipmentStrength>">Set your equipment strength here: </label>
                <input type = "number" id = "equipmentStrength"  value = {equipmentStrength} onChange = {(event) => {setEquipmentStrength(event.target.value)}}/>
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