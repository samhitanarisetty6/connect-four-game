package cs1302.gameutil;

/**
 * Represents the phase (lifecycle state) of a {@code ConnectFour} game.
 */
public enum GamePhase {

    /** The game has just been constructed; player tokens have not been set yet. */
    NEW,

    /** Player tokens have been set, but no tokens have been dropped yet. */
    READY,

    /** At least one token has been dropped and the game is still in progress. */
    PLAYABLE,

    /** The game has ended, either because a player won or the grid is full. */
    OVER

} // GamePhase
