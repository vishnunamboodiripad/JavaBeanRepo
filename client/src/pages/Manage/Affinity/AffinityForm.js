import React, { useEffect, useState } from "react";
import { useHistory, useParams } from "react-router-dom";

export default function AffinityForm(props){
    const [affinityName, setAffinityName] = useState("");
    const [affinityImage, setAffinityImage] = useState("");
    
    


    const params = useParams();
    const history = useHistory();


    const checkForPrePopulate = () => {
        if (params.id){
            const targetAffinity = props.affinityList.find((affinity) => {return affinity.affinityId.toString() === params.id.toString()});
            setAffinityName(targetAffinity.affinityName);
            setAffinityImage(targetAffinity.affinityImage);
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
        const newAffinity = {
            affinityName,
            affinityImage,
            
        }
        
        fetch ("http://localhost:8080/api/add/affinity", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Accept": "application/json"
              },
            body: JSON.stringify(newAffinity)
        })
        .then((result)=> {
            if (result.status === 201) {
                props.getAllAffinity();
                clearForm();
                props.setErrors([]);
                history.push("/manage/affinity")

            }
            else {
                result.json().then((errors) => {
                    props.setErrors(errors)
                })
            }
        })

    }

    const update = () => {
        
        const updatedAffinity = {
            affinityName,
            affinityImage,
            affinityId: params.id
        }
        fetch (`http://localhost:8080/api/edit/affinity/${params.id}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
                "Accept": "application/json"
              },
              body : JSON.stringify(updatedAffinity)
        })
        .then((result)=> {
            if (result.status === 204) {
                props.getAllAffinity();
                clearForm();
                props.setErrors([]);
                history.push("/manage/affinity")

            }
            else {
                result.json().then((errors) => {
                  props.setErrors(errors)
                })
              }
            })
    }

    const clearForm = () => {
        setAffinityName("")
        setAffinityImage("")        
    }
    const cancelEdit = () => {
        history.push("/manage/affinity")
        clearForm()
        props.setErrors("")
        
      }

    return (
        <>
        <h4>Enter the information for a new Affinity in the form below</h4>
        <form onSubmit={(event)=>handleSubmit(event)}>
        <div id = "flexbox1" class ="flex-container">

            <div>
                <label htmlFor = "affinityName">Affinity name: </label>
                <input type = "text" id = "afinityName-input" value = {affinityName} onChange = {(event) => {setAffinityName(event.target.value)}}/>
            </div>
            <div>
                <label htmlFor = "affinityImage">Link for affinity Image: </label>
                <input type = "text" id = "affinityImage-input" value = {affinityImage} onChange = {(event) => {setAffinityImage(event.target.value)}}/>
            </div>
            <input type = "submit" value = {params.id ? "Update!" : "Create!"}/>
            {params.id !== undefined ? <button onClick = {() => {cancelEdit()}}>Cancel Edit</button>
            : null}
        </div>
        </form>
        </>
    )
}