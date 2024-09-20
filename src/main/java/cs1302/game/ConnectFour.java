package cs1302.game;

import cs1302.gameutil.GamePhase;
import cs1302.gameutil.Token;
import cs1302.gameutil.TokenGrid;

/**
 * {@code ConnectFour} represents a two-player connection game involving a two-dimensional grid of
 * {@linkplain cs1302.gameutil.Token tokens}. When a {@code ConnectFour} game object is
 * constructed, several instance variables representing the game's state are initialized and
 * subsequently accessible, either directly or indirectly, via "getter" methods. Over time, the
 * values assigned to these instance variables change so that they always reflect the latest
 * information about the state of the game.
 */
public class ConnectFour {

    private int rows;           // number of grid rows
    private int cols;           // number of grid columns
    private Token[][] grid;     // 2D array of tokens in the grid
    private Token[] player;     // 1D array of player tokens (length 2)
    private int numDropped;     // number of tokens dropped so far
    private int lastDropRow;    // row index of the most recent drop
    private int lastDropCol;    // column index of the most recent drop
    private GamePhase phase;    // current game phase

    /**
     * Constructs a {@link cs1302.game.ConnectFour} game with a grid that has {@code rows}-many
     * rows and {@code cols}-many columns.
     *
     * @param rows the number of grid rows
     * @param cols the number of grid columns
     * @throws IllegalArgumentException if the value supplied for {@code rows} or {@code cols} is
     *     not supported. The following values are supported: {@code 6 <= rows <= 9} and
     *     {@code 7 <= cols <= 9}.
     */
    public ConnectFour(int rows, int cols) {
        if (rows < 6 || rows > 9 || cols < 7 || cols > 9) {
            throw new IllegalArgumentException("Unsupported grid size.");
        } // if

        this.rows = rows;
        this.cols = cols;
        this.grid = new Token[rows][cols];
        this.player = new Token[2];
        this.numDropped = 0;
        this.lastDropRow = -1;
        this.lastDropCol = -1;
        this.phase = GamePhase.NEW;
    } // ConnectFour

    /**
     * Return the number of rows in the game's grid.
     *
     * @return the number of rows
     */
    public int getRows() {
        return this.rows;
    } // getRows

    /**
     * Return the number of columns in the game's grid.
     *
     * @return the number of columns
     */
    public int getCols() {
        return this.cols;
    } // getCols

    /**
     * Return whether {@code row} and {@code col} specify a location inside this game's grid.
     *
     * @param row the position's row index
     * @param col the position's column index
     * @return {@code true} if {@code row} and {@code col} specify a location inside this game's
     *     grid and {@code false} otherwise
     */
    public boolean isInBounds(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    } // isInBounds

    /**
     * Return the grid {@linkplain cs1302.gameutil.Token token} located at the specified position
     * or {@code null} if no token has been dropped into that position.
     *
     * @param row the token's row index
     * @param col the token's column index
     * @return the grid token located in row {@code row} and column {@code col}, if it exists;
     *     otherwise, the value {@code null}
     * @throws IndexOutOfBoundsException if {@code row} and {@code col} specify a position that is
     *     not inside this game's grid.
     */
    public Token getTokenAt(int row, int col) {
        if (!isInBounds(row, col)) {
            throw new IndexOutOfBoundsException("Invalid row or column.");
        } // if
        return grid[row][col];
    } // getTokenAt

    /**
     * Set the first player token and second player token to {@code token0} and {@code token1},
     * respectively. If the current game phase is {@link cs1302.gameutil.GamePhase#NEW}, then
     * this method changes the game phase to {@link cs1302.gameutil.GamePhase#READY}, but only
     * if no exceptions are thrown.
     *
     * @param token0 token for first player
     * @param token1 token for second player
     * @throws NullPointerException if {@code token0} or {@code token1} is {@code null}.
     * @throws IllegalArgumentException if {@code token0 == token1}.
     * @throws IllegalStateException if {@link #getPhase getPhase()} does not return
     *     {@link cs1302.gameutil.GamePhase#NEW}.
     */
    public void setPlayerTokens(Token token0, Token token1) {
        if (phase != GamePhase.NEW) {
            throw new IllegalStateException("Player tokens can only be set in NEW phase.");
        } // if
        if (token0 == null || token1 == null) {
            throw new NullPointerException("token0 and token1 cannot be null.");
        } // if
        if (token0.equals(token1)) {
            throw new IllegalArgumentException("Tokens for different players can't be the same.");
        } // if
        player[0] = token0;
        player[1] = token1;
        phase = GamePhase.READY;
    } // setPlayerTokens

    /**
     * Return a player's token.
     *
     * @param player the player ({@code 0} for first player and {@code 1} for second player)
     * @return the token for the specified player
     * @throws IllegalArgumentException if {@code player} is neither {@code 0} nor {@code 1}
     * @throws IllegalStateException if {@link #getPhase getPhase()} returns
     *     {@link cs1302.gameutil.GamePhase#NEW}.
     */
    public Token getPlayerToken(int player) {
        if (player != 0 && player != 1) {
            throw new IllegalArgumentException("Invalid player index.");
        } // if
        if (this.phase == GamePhase.NEW) {
            throw new IllegalStateException("Game is in NEW phase.");
        } // if
        return this.player[player];
    } // getPlayerToken

    /**
     * Return the number of tokens that have been dropped into this game's grid so far.
     *
     * @return the number of dropped tokens
     * @throws IllegalStateException if {@link #getPhase getPhase()} returns
     *     {@link cs1302.gameutil.GamePhase#NEW} or {@link cs1302.gameutil.GamePhase#READY}.
     */
    public int getNumDropped() {
        if (phase == GamePhase.NEW || phase == GamePhase.READY) {
            throw new IllegalStateException("Game has not started yet.");
        } // if
        return numDropped;
    } // getNumDropped

    /**
     * Return the row index of the last (i.e., the most recent) token dropped into this
     * game's grid.
     *
     * @return the row index of the last drop
     * @throws IllegalStateException if {@link #getPhase getPhase()} returns
     *     {@link cs1302.gameutil.GamePhase#NEW} or {@link cs1302.gameutil.GamePhase#READY}.
     */
    public int getLastDropRow() {
        if (phase == GamePhase.NEW || phase == GamePhase.READY) {
            throw new IllegalStateException("Game has not started yet.");
        } // if
        return lastDropRow;
    } // getLastDropRow

    /**
     * Return the col index of the last (i.e., the most recent) token dropped into this
     * game's grid.
     *
     * @return the column index of the last drop
     * @throws IllegalStateException if {@link #getPhase getPhase()} returns
     *     {@link cs1302.gameutil.GamePhase#NEW} or {@link cs1302.gameutil.GamePhase#READY}.
     */
    public int getLastDropCol() {
        if (phase == GamePhase.NEW || phase == GamePhase.READY) {
            throw new IllegalStateException("Game has not started yet.");
        } // if
        return lastDropCol;
    } // getLastDropCol

    /**
     * Return the current game phase.
     *
     * @return current game phase
     */
    public GamePhase getPhase() {
        return phase;
    } // getPhase

    /**
     * Drop a player's token into a specific column in the grid. This method does not enforce
     * turn order -- that is the players' responsibility.
     *
     * <p><strong>NOTE:</strong> This method does not call {@link #isLastDropConnectFour}. If you
     * want to use {@link #isLastDropConnectFour} to determine a winner after each token is
     * dropped, you must call it separately.
     *
     * @param player the player ({@code 0} for first player and {@code 1} for second player)
     * @param col the grid column where the token will be dropped
     * @throws IndexOutOfBoundsException if {@code col} is not a valid column index
     * @throws IllegalArgumentException if {@code player} is neither {@code 0} nor {@code 1}
     * @throws IllegalStateException if {@link #getPhase getPhase()} does not return
     *     {@link cs1302.gameutil.GamePhase#READY} or {@link cs1302.gameutil.GamePhase#PLAYABLE}
     * @throws IllegalStateException if the specified column in the grid is full
     */
    public void dropToken(int player, int col) {
        if (phase != GamePhase.READY && phase != GamePhase.PLAYABLE) {
            throw new IllegalStateException("Game is not in READY or PLAYABLE phase.");
        } // if
        if (player != 0 && player != 1) {
            throw new IllegalArgumentException("Invalid player index.");
        } // if
        if (!isInBounds(0, col)) {
            throw new IndexOutOfBoundsException("Column is out of bounds.");
        } // if
        if (grid[0][col] != null) {
            throw new IllegalStateException("Column is full.");
        } // if

        int row = rows - 1;
        while (row > 0 && grid[row][col] != null) {
            row--;
        } // while

        grid[row][col] = this.player[player];
        lastDropRow = row;
        lastDropCol = col;
        numDropped++;

        if (phase == GamePhase.READY) {
            phase = GamePhase.PLAYABLE;
        } // if
    } // dropToken

    /**
     * Return {@code true} if the last token dropped via {@link #dropToken} created a
     * <em>connect four</em>. A <em>connect four</em> is a sequence of four equal tokens (i.e.,
     * they have the same color) -- this sequence can occur horizontally, vertically, or
     * diagonally. If the grid is full or the last drop created a <em>connect four</em>, then
     * this method changes the game's phase to {@link cs1302.gameutil.GamePhase#OVER}.
     *
     * <p><strong>NOTE:</strong> If you want to use this method to determine a winner, then you
     * must call it after each call to {@link #dropToken}.
     *
     * @return {@code true} if the last token dropped created a <em>connect four</em>, else
     *     {@code false}
     */
    public boolean isLastDropConnectFour() {
        if (lastDropRow == -1 || lastDropCol == -1) {
            return false;
        } // if

        Token lastToken = grid[lastDropRow][lastDropCol];

        boolean isConnectFour = checkDirection(lastToken, 0, 1)
            || checkDirection(lastToken, 1, 0)
            || checkDirection(lastToken, 1, 1)
            || checkDirection(lastToken, 1, -1);

        if (isConnectFour || numDropped == rows * cols) {
            phase = GamePhase.OVER;
        } // if

        return isConnectFour;
    } // isLastDropConnectFour

    /**
     * Check if there's a connect four in the horizontal direction for the given row.
     *
     * @param row the row to check.
     * @param token the token to check for.
     * @return true if there is a connect four, false otherwise.
     */
    public boolean checkHorizontal(int row, Token token) {
        int count = 0;
        for (int col = 0; col < cols; col++) {
            if (grid[row][col] == token) {
                count++;
                if (count >= 4) {
                    return true;
                } // if
            } else {
                count = 0;
            } // if
        } // for
        return false;
    } // checkHorizontal

    /**
     * Check if there's a connect four in the vertical direction for the given column.
     *
     * @param col the column to check.
     * @param token the token to check for.
     * @return true if there is a connect four, false otherwise.
     */
    public boolean checkVertical(int col, Token token) {
        int count = 0;
        for (int row = 0; row < rows; row++) {
            if (grid[row][col] == token) {
                count++;
                if (count >= 4) {
                    return true;
                } // if
            } else {
                count = 0;
            } // if
        } // for
        return false;
    } // checkVertical

    /**
     * Check if the grid is full.
     *
     * @return true if the grid is full, false otherwise.
     */
    public boolean isFull() {
        return numDropped == rows * cols;
    } // isFull

    /**
     * Get the next available row in a given column.
     *
     * @param col the column to check.
     * @return the next available row, or -1 if the column is full.
     */
    public int getNextAvailableRow(int col) {
        for (int row = rows - 1; row >= 0; row--) {
            if (grid[row][col] == null) {
                return row;
            } // if
        } // for
        return -1;
    } // getNextAvailableRow

    /**
     * Check if a column is full.
     *
     * @param col the column to check.
     * @return true if the column is full, false otherwise.
     */
    public boolean isColumnFull(int col) {
        return grid[0][col] != null;
    } // isColumnFull

    /**
     * Checks for a sequence of four equal tokens in a specified direction (and its opposite),
     * centered on the last dropped token.
     *
     * @param token the token to check for
     * @param rowDir the row direction (e.g., 0 for horizontal, 1 for vertical/diagonal)
     * @param colDir the column direction (e.g., 1 for right, -1 for left)
     * @return true if there is a sequence of four matching tokens, false otherwise.
     */
    private boolean checkDirection(Token token, int rowDir, int colDir) {
        int count = 0;
        int row = lastDropRow;
        int col = lastDropCol;

        while (row >= 0 && row < rows && col >= 0 && col < cols && grid[row][col] == token) {
            count++;
            row += rowDir;
            col += colDir;
        } // while

        row = lastDropRow - rowDir;
        col = lastDropCol - colDir;

        while (row >= 0 && row < rows && col >= 0 && col < cols && grid[row][col] == token) {
            count++;
            row -= rowDir;
            col -= colDir;
        } // while

        return count >= 4;
    } // checkDirection

    /**
     * Print the game grid to standard output.
     */
    public void printGrid() {
        TokenGrid.println(this.grid);
    } // printGrid

} // ConnectFour
