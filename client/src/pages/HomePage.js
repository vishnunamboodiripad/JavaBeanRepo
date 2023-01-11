import React from "react";
import MonsterBashLogo from "../homePage_images/MonsterBashLogo.gif";
import tips from "../homePage_images/tips.png";
import rules from "../homePage_images/rules.gif";
import comic from "../homePage_images/comica1.png";
import comic1 from "../homePage_images/comics/1.png";
import comic2 from "../homePage_images/comics/2.png";
import comic3 from "../homePage_images/comics/3.png";
import comic4 from "../homePage_images/comics/4.png";

export default function HomePage() {
    return (
        <div id = "#Logo-arena">
            <div className="MonsterBashLogo">
          <img src={MonsterBashLogo} width="500" height="500" />
          </div>
          
          <body>
          <img src={rules} width="200" height="100" />
            <div class ="grid-container">
                <div class = "grid-item grid-item-1">
                <img src={comic1} width="500" height="500"/>
                </div>
                <div class = "grid-item grid-item-2">
                <img src={comic2} width="500" height="500"/>
                </div>
                <div class = "grid-item grid-item-3">
                <img src={comic3} width="500" height="500"/>
                </div>
                <div class = "grid-item grid-item-4">
                <img src={comic4} width="500" height="500" />
                </div>
                
                <div class = "grid-item grid-item-5"></div>

            </div>
          </body>
        </div>

       
       
      
        
    )
}