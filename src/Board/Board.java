package Board;


public class Board {

//The board has 7 collumns, 6 rows. Computer checks collumns first, then rows.
//We set the board to be an int, of which we have 0,1,2. 0 means empty, 1 means player 1, 2 means player 2.

    private int [][] positions;

    /**
     * Initialized the board.
     */
    public Board(){
        this.positions = new int[7][6];
    }

    /**
     * Return if player 1, 2 or no one owns a column.
     * @param col the column position
     * @param row the row position.
     * @return what is at that position
     */
    public int GetPosition(int col, int row){
        return this.positions[col][row];
    }

    /**
     * Sets a position to a player
     * @param col the column position
     * @param row the row position
     * @param player the player to be inserted.
     */
    public void SetPosition(int col, int row, int player){
        this.positions[col][row] = player;
    }


}
