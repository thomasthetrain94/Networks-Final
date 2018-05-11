import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

/**
 * Collect all the Scoreboard Client classes in this file. Note that this file
 * MUST BE called ScoreboardClient.java
 */
public class ScoreboardClient implements Runnable {    
    protected BufferedReader input;
    protected PrintWriter output;
    private ScoreboardClient master;
    //Interact with the questions from the challenge Response
    private ArrayList<ChallengeResponseGame> games;
    protected void registerCallback(ScoreboardClient c) {
        this.master = c;
    }
    public ScoreboardClient(BufferedReader input, PrintWriter output) {
        this.input = input;
        this.output = output;
    }
    public void run(){
        String player = "";
        int port = 4001;
        try{
            //Creates a server socket, bound to the specified port.
            //should connect to the localhost on port 4001
            Socket clientSocket = new Socket("localhost",port); 
            //Output and Input stream creation
            //Output sends data to the server
            PrintWriter output = new PrintWriter(clientSocket.getOutputStream(),true);
            //Input Recieves data from the server
            BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            System.out.println("User Name Selected");
            //Username Declariation
            String UserName = input.readLine();
            //Add to array of people
            System.out.println("User Name Selected");
            while(true){                
                String userInput = input.readLine();
                switch(userInput){
                    //Or this may have to be done with an IF Else statements which might
                    //be a cleaner solution
                    //I moved most of this code to Client since it is interactive while
                    //Server is not
                    case "Help": System.out.println("Other Commands");
                    System.out.println("Expected Inputs: Help, Game, Question, Scoreboard, Random");
                    break;
                    case "Game": System.out.println("Choose game Crypto or Networking");
                    //The available games
                    System.out.println("0:Crypto /n1:Networking");
                    //Choosing the which game to play
                    String gameChoice = input.readLine();
                    //Converts the input to the index of game where
                    int apple=games.indexOf(gameChoice);
                    //the currentGame is created with the games array at that array
                    ChallengeResponseGame currentGame=games.get(apple);                    
                    //The available Questions
                    System.out.println("Question 1 or Question 2");
                    //Choosing the which question to play                    
                    String gameChoice2 = input.readLine();
                    //Converts the input to the index of game where
                    int banana=games.indexOf(gameChoice2);
                    //The question is choosen
                    String question = currentGame.getQuestions().get(banana).getQuestion();
                    //UserAnswer for the question
                    String userAnswer = input.readLine();
                    //checking the answer for the question of the current game by this.UserName
                    currentGame.answer(UserName, question, userAnswer);
                    break;
                    case "ScoreBoard": System.out.println("Print Scoredboard");
                    String gameChoice3 = input.readLine();
                    //Converts the input to the index of game where
                    int canalope=games.indexOf(gameChoice3);
                    //Should get the game at the choice of the user and return the names and scores
                    games.get(canalope).getScores();
                    break;
                    case "Random": System.out.println("Choosing Random Game");
                    //Random number creation for games
                    Random generator = new Random();   
                    //Apply the number to the list of games
                    ChallengeResponseGame currentGameRandom=games.get(generator.nextInt(1));
                    //add the username to the current game
                    currentGameRandom.addPlayer(UserName);                
                    //Question selection is done by random choice
                    //Store the question for the current game by getting the questions
                    //then index of the next random number to get the single question
                    String questionRandom = currentGameRandom.getQuestions().get(generator.nextInt(1)).getQuestion();
                    //UserAnswer for the question
                    System.out.println(questionRandom);
                    String userAnswerRandom = input.readLine();
                    //checking the answer for the question of the current game by this.UserName
                    System.out.println(currentGameRandom.answer(UserName, questionRandom, userAnswerRandom));
                    break;
                    default: System.out.println("Unexpected Input");
                } 
            }    
            }catch(IOException ex) {
            System.err.println(String.format("Unable to connect to port %d",port));
            }catch(java.lang.ArrayIndexOutOfBoundsException ex){
               System.err.println("Shit went to far");
            }catch(java.lang.NullPointerException ex){
               System.err.println("UserInput for switch")  ;
        }
    }
}
