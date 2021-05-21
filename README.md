# Premier-League-Manager-System
A simple premier league manager 2020/2021 system developed with the AngularJs framework along with Typescript as the Front-End and Java Spring Boot framework as the Back-end with the use of OOP principles
-------------------------------------------------------------------------
SPRINGBOOT
-------------------------------------------------------------------------
Access the folder named as "SPRINGBOOT" situated in the current folder
and open the folder called "spring-boot" in an IDE.
-------------------------------------------------------------------------
-------------------------------------------------------------------------
ANGULARJS
-------------------------------------------------------------------------
Open the "Angular-CW-OOP" in an IDE (node modules folder has been removed)
-------------------------------------------------------------------------
-------------------------------------------------------------------------
HOW THE SYSTEM WORKS
-------------------------------------------------------------------------
It basically acts up as a normal league manager system, you can:
----------------------
-add teams (CONSOLE)
----------------------
-delete teams (CONSOLE)
----------------------
-show statitics of a specific team (CONSOLE)
----------------------
-show league table in GUI (GUI) (There is a method implemented for the league table to print in console as well) 
----------------------
-save teams and matches played to a file(normal text file) 
----------------------
-retrieve teams and matches from a text file(happens automatically when you first run the program) 
----------------------
-add matches manually (CONSOLE)
----------------------
-add random matches(through a button in the GUI)
----------------------

When the season finishes(Every team should have played ((noOfTeams*2)-2) ) the system will automatically remove the last 3 teams(it will ask before doing so).
If the number of clubs is 4 or less i have made it to relagate only the last 2 teams.(You can change this in the method called relegateTeams())
