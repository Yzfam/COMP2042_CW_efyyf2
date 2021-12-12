# Brick Destroy
This is a simple arcade video game. Player's goal is to destroy a wall with a small ball. The game automatically pause if the frame loses focus

A - move the player LEFT

D - move the player RIGHT

ESC - enter/exit pause menu

SPACE - start/pause the game

ALT+SHITF+F1 - open debug console

# Refactoring 
1) Dividing classes into packagaes
- seperated the classes into Model, View and Controller patterns
- gameModel package: CementBrick, ClayBrick etc
- gameView package: HomeMenu, InfoMenu etc
- gameController package: Ball, Brick etc

2) Renaming of methods and variables
- changing "border" to "borderColour", "speedX" to "ballSpeedX"
- more readable and easier to understand

3) Simple refactoring
- deleting unsed codes and lines
- changing font styles and sizes to be more readable


# Addition
1) Adding info menu
- used to display the aim, instructions as well as how to open the debug console
- helps user to understand how to play the game

2) Adding highscore menu
- displays the permanent highscore list

3) Displaying player's score during game
- one brick destroyed equals to 1 point
- points will increase and compile along the levels

4) Adding background
- background images were added in the game menu, info menu and the score board

5) Adding new brick type
- new brick type called "GemStoneBrick" is added
- has the same strength as cement brick probability of steel brick
- hardest brick in game

6) Adding 4 new levels
- the game now has a total of 8 levels with the new brick being implemented
- level 5: all CementBrick
- level 6: all SteelBrick
- level 7: combination of ClayBrick and GemStoneBrick 
- level 8: combination of CementBrick and GemStoneBrick 

7) Adding new packages 
- new package called "resources" to store the non codes files ex: pictures

8) Javadocs
- generated javadocs for all the methods in the program