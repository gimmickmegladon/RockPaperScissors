import java.util.Random;
import java.util.Scanner;

import static java.lang.System.exit;


public class RockPaperScissors {

    /*    static final int ROCK = 1; Constants for readability, now unused
        static final int PAPER = 2;
        static final int SCISSORS = 3;
    */
    private static int balance;
    public static void main(String[] args) {

        //set up array for tracking win/draw/loss
        int[] gameScore = new int[3]; // {0,0,0}; no need to init as default is 0

        // ArrayList is more costly, for our purpose of incrementing 3 integers, a simple Array should be better
        // Found at: https://www.javatpoint.com/difference-between-array-and-arraylist
        //ArrayList gameScore = new ArrayList<Integer>();

        //set up scanner for console input
        Scanner scanner = new Scanner(System.in);

        //start the game
        startGame(scanner, gameScore);
    }// End of main method

    public static void startGame(Scanner s, int[] gs){
        System.out.println("Welcome to Rock, Paper, Scissors!");

        //ask player for N of rounds
        int rounds = askNumRounds(s);

        //exit program if value of rounds isn't 1-10
        if(rounds == 0){
            exit(0);
        }

        //loop for N rounds
        for (int i = 0; i < rounds; i++) {
            //play a round
            int result = playRound(s);
            //record the result in the gameScore Array
            switch (result){
                case 0: //increment PLAYER WIN
                    gs[0] = gs[0] + 1;
                    break;
                case 1: //increment DRAW
                    gs[1] = gs[1] + 1;
                    break;
                case 2: //increment COMPUTER WIN
                    gs[2] = gs[2] + 1;
                    break;
            }

        }
        displayResults(gs);

        System.out.println("Would you like to play again? (y/n)");
        s.nextLine(); //this call is necessary to consume the excess newline after calling nextInt on Scanner. Found at:
        // https://stackoverflow.com/questions/13102045/scanner-is-skipping-nextline-after-using-next-or-nextfoo
        String answer = s.nextLine();
        if(answer.equals("y")){
            gs = new int[3]; //clear the gameScore
            startGame(s,gs); //start new game
        }
    }// End of startGame method
    private static int playRound(Scanner s){
        System.out.println("---------- NEW ROUND ----------");
        String playerChoice = String.valueOf(playerThrow(s));
        String computerChoice = String.valueOf(computerThrow());
        int roundResult = 1; //0 is PLAYER WIN, 1 is DRAW, 2 is COMPUTER WIN

        // Decide result of throws, cases with the same output are grouped together with fallthrough
        switch (playerChoice + computerChoice) {
            case "11": //fallthrough
            case "22": //fallthrough
            case "33":
                roundResult = 1;
                System.out.println("DRAW");
                break;
            case "12": //fallthrough
            case "23": //fallthrough
            case "31":
                roundResult = 2;
                System.out.println("COMPUTER WINS");
                break;
            case "13": //fallthrough
            case "21": //fallthrough
            case "32":
                roundResult = 0;
                System.out.println("PLAYER WINS");
                break;
        }
        return roundResult;
/*      // Deciding the result of a round was initially done via if/else statements rather than switch. After quick
        // research, it was found that switch is usually faster than if/else in cases where there is more than one
        // condition. https://stackoverflow.com/questions/6705955/why-switch-is-faster-than-if
        //check for draw
        if(playerChoice == computerChoice){
            System.out.println("DRAW");
            result = 1;
        }
        //go through the other cases
        else if((playerChoice == ROCK) && (computerChoice == PAPER)){
            System.out.println("COMPUTER WINS");
            result = 2;
        }
        else if((playerChoice == ROCK) && (computerChoice == SCISSORS)){
            System.out.println("PLAYER WINS");
            result = 0;
        }
        else if((playerChoice == PAPER) && (computerChoice == ROCK)){
            System.out.println("PLAYER WINS");
            result = 0;
        }
        else if((playerChoice == PAPER) && (computerChoice == SCISSORS)){
            System.out.println("COMPUTER WINS");
            result = 2;
        }
        else if((playerChoice == SCISSORS) && (computerChoice == ROCK)){
            System.out.println("COMPUTER WINS");
            result = 2;
        }
        else if((playerChoice == SCISSORS) && (computerChoice == PAPER)){
            System.out.println("PLAYER WINS");
            result = 0;
        }
*/
    }// End of method
    private static int askNumRounds(Scanner s){
        System.out.println("How many rounds would you like to play? (1-10)");
        int r = s.nextInt();
        if((r<1) || (r > 10)){
            System.out.println("Provided value was not in accepted range (1-10)!");
            System.out.println("Shutting down...");
            return 0;
        }
        return r;
    }

    private static int playerThrow(Scanner s){
        System.out.println("What would you like to throw? Type:");
        System.out.println("1 - for Rock\n2 - for Paper\n3 - for Scissors");
        int choice = s.nextInt();

        //print out choice
        System.out.print("PLAYER chose ");
        switch (choice){
            case 1:
                System.out.println("ROCK");
                break;
            case 2:
                System.out.println("PAPER");
                break;
            case 3:
                System.out.println("SCISSORS");
                break;
        }

        return choice;
    }

    private static int computerThrow(){
        Random r = new Random();
        //generate computer Throw 0/1/2
        int computerChoice = r.nextInt(3);

        //add 1 to choice, to match human-friendly player choices, eg. 1/2/3
        computerChoice++;

        //print out choice
        System.out.print("COMPUTER chose ");
        switch (computerChoice){
            case 1:
                System.out.println("ROCK");
                break;
            case 2:
                System.out.println("PAPER");
                break;
            case 3:
                System.out.println("SCISSORS");
                break;
        }

        return computerChoice;
    }

    private static void displayResults(int[] gs){
        System.out.println("---------- END OF GAME ----------");
        System.out.println("------------ RESULTS ------------");
        if(gs[0] > gs[2]){      //if playerWins > computerWins
            System.out.println("PLAYER won with a score of "+gs[0]+" to "+gs[2]+". There were "+gs[1]+" drawn games.");
            System.out.println("Congratulations!");
        }
        else if(gs[0] < gs[2]){ //if playerWins < computerWins
            System.out.println("COMPUTER won with a score of "+gs[2]+" to "+gs[0]+". There were "+gs[1]+" drawn games.");
            System.out.println("Better luck next time!");
        }else{                  // if playerWins == computerWins
            System.out.println("DRAW. The score was "+gs[0]+" to "+gs[2]+" with "+gs[1]+" draws.");
            System.out.println("What are the odds!");
        }
    }

} // End of class