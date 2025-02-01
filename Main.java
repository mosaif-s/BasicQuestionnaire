package P5;

import P4.Game;

/* This file includes:
 * 	1. Solution to P3
 *  2. Questions for P4. Comments starting with REQ represent the questions.
 *  
 * Features:
 * 	- We have from 1 to 3 players
 *  - We have many questions. Each player may be asked more than one question.
 *  - User can play many rounds of the game. 
 * 
 * Focus: 
 * 	- Arrays and Methods
 * 
 * Aim:
 * 	- Organize code and avoid code redundancy by the use of methods
 */

public class Main {				
	static Game game;			
	
	//Two arrays for questions and answers (both are global, i.e., accessible by all code in the class).
	//REQ1:	Replace array values with real questions/answers
	static String[][] questions = {
		    {"What is the capital of France?", "What is the main ingredient in sushi?", "How do you say 'hello' in Spanish?"},
		    {"Which river is the longest in the world?", "What is a traditional Italian pasta dish?", "In which country is Mandarin a primary language?"},
		    {"What is the highest mountain peak in North America?", "What is a popular street food in Mexico?", "Which language is spoken in Brazil?"}
		};

		static String[][] answers = {
		    {"Paris", "Fish", "Hola"},
		    {"Nile", "Spaghetti", "China"},
		    {"Denali", "Tacos", "Portuguese"}
		};

	public static void main(String[] args) {
		String ans;
		do{								
			//Reset the game
			game = new Game();			
			
			//Get number of players (from 1 to 3)
			int numPlayers = game.askForInt("How many players", 1, 3);

			//Add up to 3 players to the game
			for (int i = 0; i < numPlayers; i++) {
				String name = game.askForText("What is player " + i + " name?");
				game.addPlayer(name);				
			}
			
			//REQ2:	Call a method to shuffle questions and answers. For that, you need to create a method with the header: void shuffleQuestions();
			int k=game.askForInt("Enter category number (0 for Geography, 1 for Food, 2 for Languages): ",0,2);
			shuffleQuestions(k);
			//REQ3:	- Calculate the maximum number of rounds (each player is asked one question per round). The maximum number of rounds should be equal to the number of available questions divided by numPlayers. Store this value in a variable maxRounds
			int maxRounds = 20;
			//	- Ask the user about the number of rounds. The value read from the user should not exceed maxRounds. Store this value in a variable numRounds.
			int numRounds = game.askForInt("How many rounds? (Maximum: " + maxRounds + ")", 1, maxRounds);

			// Validate the input to make sure it is less than or equal to maxRounds
			while (numRounds > maxRounds) {
			    //System.out.println("Invalid input. Number of rounds cannot exceed the maximum.");
			    numRounds = game.askForInt("How many rounds? (Maximum: " + maxRounds + ")", 1, maxRounds);
			}
			//		- Ask each player the next unanswered question (e.g., player 0 gets the first question. If it is answered correctly, then player1 gets the next question in the array, otherwise player1 gets the same question again, and so on). 
			    int indexer = 0;
			    for (int round = 0; round < numRounds; round++) {
			        for (int i = 0; i < numPlayers; i++) {
			            game.setCurrentPlayer(i);
			            String answer = game.askForText(questions[indexer][k]);
			            if (answers[indexer][k].equals(answer)) {
			                game.correct();
			                indexer++;
			                
			    			
			                if (indexer >= questions.length) {
			                    
			                	indexer = 0;
			                	if (numRounds-round!=1)
			                	k=game.askForInt("Enter category number (0 for Geography, 1 for Food, 2 for Languages): ",0,2);
			                }
			            } else {
			                game.incorrect();			            }
			        }
			    } 
			// 		  Assume that an incorrectly answered question will keep popping up until it is correctly answered or we finish all the rounds.
			//		  Hint: you need to create a for loop that repeats the below code block numRounds times.
			//		  Hint: you need to have a variable that keeps track of the next question to be offered. 
			  
			//for (int i = 0; i < numPlayers; i++) {
				//game.setCurrentPlayer(i);//draw rectangle around player 0, and currentPlayer = player0
				//String answer = game.askForText(questions[i]);
				//if(answers[i].equals(answer))
					//game.correct();		//display "Correct", increment score, change frame color to green
				//else
					//game.incorrect();	//display "incorrect", change frame color of player to red
			//}	
			
			//Do you want to play again? make sure you get valid input
			ans = game.askForText("Play again? (Y/N)"); 
			while(ans != null && !ans.toUpperCase().equals("Y") && !ans.toUpperCase().equals("N"))
				ans = game.askForText("Invalid input. Play again? (Y/N)");
		}while(ans.toUpperCase().equals("Y"));	//play again if the user answers "Y" or "y"

		System.exit(1); 	//This statement terminates the program
		
	}
	public static void shuffleQuestions(int category) {
        int n = questions[0].length;

        for (int i = n - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1));

            
            String tempQuestion = questions[i][category];
            questions[i][category] = questions[j][category];
            questions[j][category] = tempQuestion;

            
            String tempAnswer = answers[i][category];
            answers[i][category] = answers[j][category];
            answers[j][category] = tempAnswer;
        }
    }
}
