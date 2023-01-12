import React from "react";

export default function ViewAll(props){
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
                    </tr>
                </thead>
                <tbody>
                    {props.monsterList.length === 0 ? <tr>Please wait, table is loading</tr> : props.monsterList.map((monster) => {
                        return (
                            <tr key = {monster.monsterId}>
                                <td>{monster.monsterName}</td>
                                <td><img id = "monsterImage"src={monster.monsterImage} width="100" height="100"></img></td>
                                <td>{getElementName(monster.elementId)}</td>
                            </tr>
                        )
                    })}
                </tbody>
            </table>
         
        )
    }