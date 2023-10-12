<h1>Design Information For Scrabble Game</h1>


<b>Player</b>

This is class to represent user who can play the game, view game or letter statistics and adjust the game settings.
The player class tracks user's name, scores, ranking and number of moves.
The player can start or end a game, pause or return the game, it will have UI to adjust the gamesettings class.
The player can also adjust the settings of the game by  may choose for the game to end after a certain maximum number of turns;
adjust the number of and the letter points for each letter available in the pool of letters, starting with the default matching Scrabble distribution.
The Player also can view the wordbank class, which can sort word according to the most reent played in the method
The player can choose to leave a game in progress at any time by pause the game, selecting to play a game from the menu should then return to the game in progress.
The player can use the game glass to calculate the final score using CalculateScore method.


<b>GameScoreStatistics</b>

The class contains the final game score, the number of turns in that game and the average score per turn, the user can reset the statistics.


<b>LetterStatistics</b>

The class contains the total number of times that letter has been played in a word, the total number of times that letter has been traded back into the pool and the percentage of times that the letter is used in a word, out of the total number of times it has been drawn.


<b>Game</b>

Game class uses board class and rack class to move tiles or end the game, utilize the dictionary for find the word and check the words.
Game class also applies the rules in the CalculateScore method if pool of letters is empty and rack can't be rrefiiled or if using default values.


<b>GameSettings</b>

This class is used to change letter number and adjust letter points. It also can set the defaul distribution by player.


<b>Board</b>

Board class is an interface will show player consisting 4 letters drawn from the letter pool.


<b>Rack</b>

Rack class is an interface to swap 7 letters drawn from the letter pool checked against the dictionary.
Rack class also check through the dictionary via Game class.


<b>LetterPool</b>

This class is a parent interface for Boad and Rack, which can display letters or replace letters.
It also empty and refill the letter in the rack. The letterpoll can also take the tile back and genterate random letters.


<b>WordRank</b>

This class is implement for game to display the word and the number of times the word has been played.


<b>Dictionary</b>

This is the class to validate the word that player select that can be found as a pattern.


<i>Finally, I also added a Operation system utility and date utility to get the game version and current date for player.</i>

