import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random; 
//Must support mutlple clients at the same time  Some version of a Player Handler
//Questions are only specify to a game: Keep track of players  Player handler 
//Authenticate players by name
//PLayer handler is how the player(Client) communicates with the server and the server communicates with the player(Client)
/**
 * Collect all the Scoreboard Server classes in this file. Note that this file
 * MUST BE called ScoreboardServer.java
 */
//middle man between client and game
public class ScoreboardServer{
        private ArrayList<ChallengeResponseGame> games;
        public void startServer(int sslPort){
            try{
            //Creates a server socket, bound to the specified port.
            ServerSocket serverSocket = new ServerSocket(sslPort);
            //listens for activity on the server
            Socket clientSocket = serverSocket.accept();
            
            while(true){
                //Output and Input stream creation
                //Output sends data to the server
                PrintWriter output = new PrintWriter(clientSocket.getOutputStream(),true);
                //Input Recieves data from the server
                BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                //Username Declariation
                //add the players 
                //output.println("Input User Name");
                String UserName = input.readLine();
                System.out.println("User Name Selected");
                String userInput = input.readLine();
                switch(userInput){
                    
                case "Help": System.out.println("Other Commands");
                    System.out.println("Expected Inputs: Help, Game, Question, Scoreboard, Random");
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
                case "ScoreBoard": System.out.println("Print Scoredboard");
                    String gameChoice3 = input.readLine();
                    //Converts the input to the index of game where
                    int canalope=games.indexOf(gameChoice3);
                    //Should get the game at the choice of the user and return the names and scores
                    games.get(canalope).getScores(); 
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
                    String userAnswerRandom = input.readLine();
                    //checking the answer for the question of the current game by this.UserName
                    currentGameRandom.answer(UserName, questionRandom, userAnswerRandom);
               default: System.out.println("Unexpected Input");
            } 
            }      
          }     
          catch (IOException ex) {
            System.err.println(String.format("Unable to connect to port %d", 
            sslPort));
          }          
        }        
        //Arraylist to hold the list of ChallengeResponseGame
        public ScoreboardServer(ArrayList<ChallengeResponseGame> games){ 
            this.games = games;
        }
        
        //setup for the ssl Connection
        //This will use the SSL with keystore and pasword
        //This will be ssl
        public void setupSSL(String s, String password){
            
        }
        //Start the server itself
    }
/*
 void Help(){//temp methods just for ease of understanding
            System.out.println("Expected Inputs: Help, Game, Question, Scoreboard, Random");
        }        
        void randomGame(){ //temp methods just for ease of understanding           
                //Random number creation for games
                Random generator = new Random();   
                //Apply the number to the list of games
                ChallengeResponseGame currentGame=games.get(generator.nextInt(1));
                //add the username to the current game
                currentGame.addPlayer(UserName);                
                //Question selection is done by random choice
                //Store the question for the current game by getting the questions
                //then index of the next random number to get the single question
                String question = currentGame.getQuestions().get(generator.nextInt(1)).getQuestion();
                //UserAnswer for the question
                String userAnswer = input.readLine();
                //checking the answer for the question of the current game by this.UserName
                currentGame.answer(UserName, question, userAnswer);
        }
        void Game(){//temp methods just for ease of understanding
            //The available games
            System.out.println("0:Crypto /n1:Networking");
            //Choosing the which game to play
            int gameChoice = input.readLine();
            //the currentGame is created with the games array at that array
            ChallengeResponseGame currentGame=games.get(gameChoice);
            //The available Questions
            System.out.println("Q1 or Q2");
            //Choosing the which question to play
            int gameChoice2 = input.readLine();
            //The question is choosen
            String question = currentGame.getQuestions().get(gameChoice2).getQuestion();
            //UserAnswer for the question
            String userAnswer = input.readLine();
            //checking the answer for the question of the current game by this.UserName
            currentGame.answer(UserName, question, userAnswer);            
        }
        void Scoreboard(){//temp methods just for ease of understanding
            int gameChoice3 = input.readLine();
            //Should get the game at the choice of the user and return the names and scores
            games.get(gameChoice).getScores();             
        }
 */    
    
    
    
