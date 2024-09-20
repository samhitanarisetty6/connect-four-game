package cs1302.gameutil;

/**
 * Represents the color/identity of a token that can be dropped into a
 * {@code ConnectFour} game's grid.
 */
public enum Token {

    BLUE,
    CYAN,
    GREEN,
    PURPLE,
    RED,
    YELLOW;

    /**
     * Returns a short, single-character label used when rendering this token
     * on a text-based grid (e.g., in the CLI).
     *
     * @return a single uppercase character representing this token
     */
    public char getShortLabel() {
        return this.name().charAt(0);
    } // getShortLabel

} // Token
