package connect4game;
import Board.Board;
import Ai.Ai;
import java.util.ArrayList;
import java.util.Scanner;

public class connect4game {

    //Initialize the objects
    Board board;
    Scanner scanner;
    Ai Ai;
    int player;

    /**
     * Initialize the game
     */
    public connect4game(){
        this.scanner = new Scanner(System.in);
        this.board = new Board();
        this.player = 1;
        this.Ai = new Ai(this.board);
    }

    /**
     * Plays the game.
     */
    public void PlayGame(){
        //This prints out the beginning
        System.out.println("Welcome to connect four!");
        System.out.println("I will print the board below.");
        System.out.println("------------------------------------------\n\n");

        while(true){
            //Print the current board
            PrintBoard();
            System.out.println("Its player " + Integer.toString(this.player) + "'s move! Enter in the column you wish to fill from 1-7: ");

            //This processes the move.
            int CurrCol = Integer.parseInt(scanner.nextLine()) - 1;
            int CurrRow = this.board.getColHeight(CurrCol);
            this.board.setPosition(CurrCol, CurrRow,this.player);

            //Check if a player won
            if (CheckWinner(CurrCol, CurrRow, this.player)){
                System.out.println("Player " + Integer.toString(this.player) + " has won the game!");
                break;
            }
            if (this.player == 1){this.player = 2;}
            else {this.player = 1;}
            System.out.println("You made your move! Now it's the Ai's move!");

            //Handle the AI
            this.Ai.updateBoard(this.board);
            int AiCol = this.Ai.makeMove(CurrCol);
            int AiRow = this.board.getColHeight(AiCol);
            this.board.setPosition(AiCol, AiRow, this.player);

            //Check if the Ai has won
            if (CheckWinner(AiCol, AiRow, this.player)){
                System.out.println("The Ai has won the game!");
                break;
            }
            if (this.player == 1){this.player = 2;}
            else {this.player = 1;}
        }
    }

    /**
     * Prints the current state of the board.
     */
    public void PrintBoard(){
        ArrayList<Object> lines = new ArrayList<>();

        for (int [] x: this.board.getBoard()){
            StringBuilder line = new StringBuilder();
            for (int y: x){
                line.append(Integer.toString(y)).append(" ");
            }
            lines.add(line);
        }

        for (int i = lines.size() - 1; i > -1; i--){
        System.out.println(lines.get(i));}
    }

    /**
     * Checks whether the win condition for a player was met where the new piece was added.
     * @param CurrCol Which Column has the new piece
     * @param CurrRow Which row has the new piece.
     * @param player Who could have one
     * @return whether a player has one.
     */
    public boolean CheckWinner(int CurrCol, int CurrRow, int player){
        //Strategy is to first check if out of bounds, then check if the position is owned by the player.
        int checker = 1;

        //First, check sideways. First left then right, then if the size >= 4
        for (int i = 1; (CurrCol - i != -1) && (this.board.getPosition(CurrCol - i, CurrRow) == player); i++){
            checker += 1;
        }
        for (int i = 1; (CurrCol + i != 8) && (this.board.getPosition(CurrCol + i, CurrRow) == player); i++){
            checker += 1;
        }
        if (checker >= 4){
            return true;
        }

        //Next, check up-down using the same strategy.
        checker = 1;
        for (int i = 1; (CurrRow - i != -1) && (this.board.getPosition(CurrCol, CurrRow - i) == player); i++){
            checker += 1;
        }
        for (int i = 1; (CurrRow + i != 8) && (this.board.getPosition(CurrCol, CurrRow + 1) == player); i++){
            checker += 1;
        }
        if (checker >= 4){
            return true;
        }

        //Now check up-right diagonal
        checker = 1;
        for (int i = 1; (CurrRow - i != -1) && (CurrCol - i != -1) && (this.board.getPosition(CurrCol - i, CurrRow - i) == player); i++){
            checker += 1;
        }
        for (int i = 1; (CurrRow + i != 8) && (CurrCol + i != 8) && (this.board.getPosition(CurrCol + i, CurrRow + i) == player); i++){
            checker += 1;
        }
        if (checker >= 4){
            return true;
        }

        //Finally check up-left diagonal
        checker = 1;
        for (int i = 1; (CurrRow + i != 8) && (CurrCol - i != -1) && (this.board.getPosition(CurrCol - i, CurrRow + i) == player); i++){
            checker += 1;
        }
        for (int i = 1; (CurrRow - i != -1) && (CurrCol + i != 8) && (this.board.getPosition(CurrCol + i, CurrRow - i) == player); i++){
            checker += 1;
        }
        return checker >= 4;
    }

}
