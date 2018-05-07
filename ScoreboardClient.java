import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Random;

/**
 * Collect all the Scoreboard Client classes in this file. Note that this file
 * MUST BE called ScoreboardClient.java
 */
public class ScoreboardClient implements Runnable {
    
    //Interact with the questions from the challenge Response
    private ArrayList<ChallengeResponseGame> games;
    public void ClientSomething(){
        String player = "";
        try{
            //Creates a server socket, bound to the specified port.
            //should connect to the localhost on port 4001
            Socket clientSocket = new Socket("localhost",4001);
            while(true){
                //Output and Input stream creation
                //Output sends data to the server
                PrintWriter output = new PrintWriter(clientSocket.getOutputStream(),true);
                //Input Recieves data from the server
                BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                //Username Declariation
                //add the players 
                //output.println("Input User Name");
                String userInput = input.readLine();                
            }
        }     catch (IOException ex) {
            System.err.println(String.format("Unable to connect to port"));
        }          
    }
    public void run(){
        
    }
}
