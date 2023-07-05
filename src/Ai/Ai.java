package Ai;
import Board.Board;
import java.util.ArrayList;
import java.util.Arrays;

public class Ai {

    private Board board;
    private ArrayList<int []> winningPositions;
    private ArrayList<int []> losingPositions;


    /**
     * Initializes the AI class
     * @param newBoard the board of the game
     */
    public Ai(Board newBoard){
        this.board = newBoard;
        this.winningPositions = new ArrayList<>();
        this.losingPositions = new ArrayList<>();
    }

    /**
     * Updates the board
     * @param newBoard The current state of the board
     */
    public void updateBoard(Board newBoard){
        this.board = newBoard;
    }

    /**
     * Determines the best column to insert a piece given where the last piece was placed.
     * @return the column where a piece is inserted.
     */
    public int makeMove(int lastCol){
        checkWinningPositions(lastCol);
        return 5;
    }

    /**
     * Figure out whether the player can win by going in a specific position.
     * @param col the column
     */
    private void checkWinningPositions(int col){
        int [] place = new int[]{col, this.board.getColHeight(col)};

        //First, check downward.
        int checker = 0;
        for (int i = place[1]; (i - 1 != -1) && (this.board.getPosition(place[0], i - 1) == 1); i--){
            checker += 1;
            if (checker >= 3){
                this.losingPositions.add(place);
                break;
            }
        }


        //Then, check sideways.
        ArrayList<Integer> Xs = new ArrayList<>();
        int position = 0;
        for (int x : this.board.getBoard()[this.board.getColHeight(col) - 1]){
            if (x == 1){Xs.add(position);}
            position += 1;
        }
        if (Xs.size() > 2){
            for (int i = 0; i < Xs.size() - 2; i++){
                int first = Xs.get(i);
                int second = Xs.get(i+1);
                int third = Xs.get(i+2);
                /*Check for gaps. There are 4 positions where the gap may appear. They are, in order of coded
                OXXX, XXXO, XXOX, XOXX. The first 2 are done in a single if statement*/
                if ((first + 1 == second) && (second + 1 == third)){
                    if ((first > 0) && !(Xs.contains(first - 1))){
                        place = new int[]{first - 1, this.board.getColHeight(first - 1)};
                        this.losingPositions.add(place);
                    }
                    if (!(Xs.contains(third + 1)) && (third + 1 != 7)){
                        place = new int[]{third + 1, this.board.getColHeight(third + 1)};
                        this.losingPositions.add(place);
                    }
                }
                else if ((first + 2 == second) && (second + 1 == third)){
                    if (!Xs.contains(first + 1)){
                        place = new int[]{first + 1, this.board.getColHeight(first + 1)};
                        this.losingPositions.add(place);
                    }
                }
                else if ((first + 1 == second) && (second + 2 == third)){
                    if (!Xs.contains(second + 1)){
                        place = new int[]{second + 1, this.board.getColHeight(second + 1)};
                        this.losingPositions.add(place);
                    }
                }
            }
        }

        //Next, check the up-right diagonal
        Xs = new ArrayList<>();
        int row = this.board.getColHeight(col);
        for (int i = 0; (row - i != -1) && (col - i != -1) && (this.board.getPosition(col - i, row - i) == 1); i++){
            place = new int[]{col - 1, row - i};

        }

        //Finally, check the up-left diagonal.

    }

}
