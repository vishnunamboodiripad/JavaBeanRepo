import React, { useEffect, useState } from "react";
import { useHistory, useParams } from "react-router-dom";

export default function ElementForm(props){
    const [elementName, setElementName] = useState("");
    const [elementImage, setElementImage] = useState("");
    
    


    const params = useParams();
    const history = useHistory();


    const checkForPrePopulate = () => {
        if (params.id){
            const targetElement = props.elementList.find((element) => {return element.elementId.toString() === params.id.toString()});
            setElementName(targetElement.elementName);
            setElementImage(targetElement.elementImage);
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
        const newElement = {
            elementName,
            elementImage,
            
        }
        
        fetch ("http://localhost:8080/api/add/element", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Accept": "application/json"
              },
            body: JSON.stringify(newElement)
        })
        .then((result)=> {
            if (result.status === 201) {
                props.getAllElement();
                clearForm();
                props.setErrors([]);
                history.push("/manage/element")

            }
            else {
                result.json().then((errors) => {
                    props.setErrors(errors)
                })
            }
        })

    }

    const update = () => {
        
        const updatedElement = {
            elementName,
            elementImage,
            elementId: params.id
        }
        fetch (`http://localhost:8080/api/edit/element/${params.id}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
                "Accept": "application/json"
              },
              body : JSON.stringify(updatedElement)
        })
        .then((result)=> {
            if (result.status === 204) {
                props.getAllElement();
                clearForm();
                props.setErrors([]);
                history.push("/manage/element")

            }
            else {
                result.json().then((errors) => {
                  props.setErrors(errors)
                })
              }
            })
    }

    const clearForm = () => {
        setElementName("")
        setElementImage("")        
    }
    const cancelEdit = () => {
        history.push("/manage/element")
        clearForm()
        props.setErrors("")
        
      }

    return (
        <>
        <h4>Enter the information for a new Element in the form below</h4>
        <form onSubmit={(event)=>handleSubmit(event)}>
        <div id = "flexbox1" class ="flex-container">

            <div>
                <label htmlFor = "elementName">Element name: </label>
                <input type = "text" id = "elementName-input" value = {elementName} onChange = {(event) => {setElementName(event.target.value)}}/>
            </div>
            <div>
                <label htmlFor = "elementImage">Link for element Image: </label>
                <input type = "text" id = "elementImage-input" value = {elementImage} onChange = {(event) => {setElementImage(event.target.value)}}/>
            </div>
            <input type = "submit" value = {params.id ? "Update!" : "Create!"}/>
            {params.id !== undefined ? <button onClick = {() => {cancelEdit()}}>Cancel Edit</button>
            : null}
        </div>
        </form>
        </>
    )
}