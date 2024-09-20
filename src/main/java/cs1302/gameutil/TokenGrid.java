package cs1302.gameutil;

/**
 * Utility class for printing a two-dimensional grid of {@link Token} objects
 * to standard output in a simple, readable, text-based format.
 */
public class TokenGrid {

    /**
     * Prints the given grid of tokens to standard output. Empty cells (i.e.,
     * {@code null} entries) are rendered as a blank space, and occupied cells
     * are rendered using the token's {@linkplain Token#getShortLabel() short label}.
     *
     * @param grid the two-dimensional array of tokens to print
     */
    public static void println(Token[][] grid) {
        for (Token[] row : grid) {
            StringBuilder line = new StringBuilder("|");
            for (Token token : row) {
                char label = (token == null) ? ' ' : token.getShortLabel();
                line.append(" ").append(label).append(" |");
            } // for
            System.out.println(line);
        } // for

        // bottom border, sized to the number of columns in the grid
        if (grid.length > 0) {
            StringBuilder border = new StringBuilder();
            for (int c = 0; c < grid[0].length; c++) {
                border.append("----");
            } // for
            border.append("-");
            System.out.println(border);
        } // if
    } // println

} // TokenGrid
