import React, { useEffect, useState } from "react";
import { useHistory, useParams } from "react-router-dom";

export default function MonsterForm(props){
    const [monsterName, setMonsterName] = useState("");
    const [monsterImage, setMonsterImage] = useState("");
    const [elementId, setElementId] = useState("");
    const [power, setMonsterPower] = useState(0);

    const params = useParams();
    const history = useHistory();


    const checkForPrePopulate = () => {
        if (params.id){
            const targetMonster = props.monsterList.find((monster) => {return monster.monsterId.toString() === params.id.toString()});
            setMonsterName(targetMonster.monsterName);
            setMonsterImage(targetMonster.monsterImage);
            setElementId(targetMonster.elementId);
            setMonsterPower(targetMonster.power)
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
        const newMonster = {
            monsterName,
            monsterImage,
            elementId,
            power,

        }
        fetch ("http://localhost:8080/api/add/monster", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Accept": "application/json"
              },
            body: JSON.stringify(newMonster)
        })
        .then((result)=> {
            if (result.status === 201) {
                props.getAllMonster();
                clearForm();
                props.setErrors([]);
                history.push("/manage/monster")

            }
            else {
                result.json().then((errors) => {
                    props.setErrors(errors)
                })
            }
        })

    }

    const update = () => {
        const updatedMonster = {
            monsterId: params.id,
            monsterName,
            monsterImage,
            elementId,
            power,
        
        }
        fetch (`http://localhost:8080/api/edit/monster/${params.id}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
                "Accept": "application/json"
              },
              body : JSON.stringify(updatedMonster)
        })
        .then((result)=> {
            if (result.status === 204) {
                props.getAllMonster();
                clearForm();
                props.setErrors([]);
                history.push("/manage/monster")

            }
            else {
                result.json().then((errors) => {
                  props.setErrors(errors)
                })
              }
            })
    }

    const clearForm = () => {
        setMonsterName("")
        setMonsterImage("")
        setElementId("")
        setMonsterPower(0)
        
    }
    const cancelEdit = () => {
        history.push("/manage/monster")
        clearForm()
        props.setErrors("")
        
      }

    return (
        <>
        <h4>Enter the information for a new Monster in the form below</h4>
        
        <form onSubmit={(event)=>handleSubmit(event)} >
        <div id = "flexbox2" class ="flex-container">
            <div>
                <label htmlFor = "monsterName-input">Monster name: </label>
                <input type = "text" id = "monsterName-input" value = {monsterName} onChange = {(event) => {setMonsterName(event.target.value)}}/>
            </div>
            <div>
                <label htmlFor = "monsterImage-input">Link for Monster image: </label>
                <input type = "text" id = "monsterImage-input" value = {monsterImage} onChange = {(event) => {setMonsterImage(event.target.value)}}/>
            </div>
            <div>
                <label htmlFor = "monsterPower-input">Enter the power level of your monster: </label>
                <input type = "number" id = "monsterPower-input" value = {power} onChange = {(event) => {setMonsterPower(event.target.value)}}/>

            </div>
            <div>
                <label htmlFor = "element">Element:</label>
                <select id = "element-input" value = {elementId} onChange = {(event) => {setElementId(parseInt(event.target.value))}}>
                    <option  value = "1">Fire</option>
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