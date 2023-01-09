import React, { useEffect, useState } from "react";
import { useHistory, useParams } from "react-router-dom";

export default function MonsterForm(props){
    const [monsterName, setMonsterName] = useState("");
    const [monsterImage, setMonsterImage] = useState("");
    const [element, setElement] = useState("");
    
    


    const params = useParams();
    const history = useHistory();


    const checkForPrePopulate = () => {
        if (params.id){
            const targetMonster = props.monsterList.find((monster) => {return monster.monsterId.toString() === params.id.toString()});
            setMonsterName(targetMonster.monsterName);
            setMonsterImage(targetMonster.monsterImage);
            setElement(targetMonster.element);
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
            element,
            
        }
        debugger
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
            monsterName,
            monsterImage,
            element,
            monsterId: params.id
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
        setElement("")
        
    }
    const cancelEdit = () => {
        history.push("/manage/monster")
        clearForm()
        props.setErrors("")
        
      }

    return (
        <>
        <h4>Enter the information for a new Monster in the form below</h4>
        <form onSubmit={(event)=>handleSubmit(event)}>
            <div>
                <label htmlFor = "monsterName-input">Monster name: </label>
                <input type = "text" id = "monsterName-input" value = {monsterName} onChange = {(event) => {setMonsterName(event.target.value)}}/>
            </div>
            <div>
                <label htmlFor = "monsterImage-input">Link for Monster image: </label>
                <input type = "text" id = "monsterImage-input" value = {monsterImage} onChange = {(event) => {setMonsterImage(event.target.value)}}/>
            </div>
            <div>
                <label htmlFor = "element">Element:</label>
                <select id = "element-input" value = {element} onChange = {(event) => {setElement(event.target.value)}}>
                    <option  value = "Fire">Fire</option>
                    <option  value = "Water">Water</option>
                    <option  value = "Earth">Earth</option>
                    <option  value = "Wind">Wind</option>
                </select>
            </div>
            <input type = "submit" value = {params.id ? "Update!" : "Create!"}/>
            {params.id !== undefined ? <button onClick = {() => {cancelEdit()}}>Cancel Edit</button>
            : null}

        </form>
        </>
    )
}