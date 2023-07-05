package Board;


public class Board {

//The board has 7 columns, 6 rows. Computer checks columns first, then rows.
//We set the board to be an int, of which we have 0,1,2. 0 means empty, 1 means player 1, 2 means player 2.

    private int [][] positions;
    private int [] ColHeights;

    /**
     * Initialize the board.
     */
    public Board(){
        this.positions = new int[6][7];
        this.ColHeights = new int[6];
    }

    /**
     * Returns the board
     * @return the total board
     */
    public int [][] getBoard(){return this.positions;}

    /**
     * Return if player 1, 2 or no one owns a column.
     * @param col the column position
     * @param row the row position.
     * @return what is at that position
     */
    public int getPosition(int col, int row){
        return this.positions[row][col];
    }

    /**
     * Sets a position in the board to a player
     * @param col the column position
     * @param row the row position
     * @param player the player to be inserted.
     */
    public void setPosition(int col, int row, int player){
        this.positions[row][col] = player;
        this.ColHeights[col] += 1;
    }

    /**
     * Gets the height of a column.
     * @param col The selected Column.
     * @return Height of a column
     */
    public int getColHeight(int col){return this.ColHeights[col];}
}
