# JavaBeanRepo

Brainstorming ideas: 

Brief description: 

Our goal is to create a database of "monsters" that a user will be able to pit against each other to find a winner. We are using an API for a set of pre-generated monsters. On arrival to the site, any anonymous user can choose two monsters and equip each with a piece equipment. The site randomly generates the weather and the environment (location) that the monsters are fighting on. This impacts the "battle stats" for each monster and a winner is determined. 

Each character has a base number for their strength. That strength goes up or down depending on the environment they are in. Each piece of equipment has a base number for its strength. That strength goes up or down depending on the weather they are in. Adding the character's strength and equipment together gives us a number. The character with the highest total number wins. 

We will have one 'admin' role which gives you the ability to add/edit/delete any of the information. Goal is to use both SVG-enhanced UI and using some kind of Animations (greensock possibly). If we have to choose one due to time constraints, we will do the enhanced UI. 

Entities: 
--monster (user chooses)
--equipment (user chooses)
--weather (randomly generated)
--environment (randomly generated)

Learning objective: 
--Goal is to use both SVG-enhanced UI and using some kind of Animations (greensock possibly). If we have to choose one due to time constraints, we will do the enhanced UI. 


Stretch goal for ourselves:
--Give the monster a personalized name 
