/**
 * Class which holds a tic-tac-toe board
 * @author Jason Tomkins
 */
public class Board {
	/**
	 * Contains the player who has won this board
	 */
	protected Player winner = Player.NOPLAYER;
	private Player[][] board = new Player[3][3];

	/**
	 * Constructs and initalizes a board
	 */
	public Board() {
		initializeBoard();
	}

	/**
	 * Sets the value of a square in the board to the current player
	 * @param currPlayer the player who selected the square
	 * @param x x coordinate relative to the array
	 * @param y y coordinate relative to the array
	 * @return true if the square is successfully played, or false if the square was occupied
	 */
	public boolean selectSquare(Player currPlayer, int x, int y) {
		if (board[x][y] != Player.NOPLAYER)
			return false;

		board[x][y] = currPlayer;
		return true;
	}

	/**
	 * checks if the board is tied
	 * @param wipeIfTied if the board should be wiped if there's a tie
	 * @return true if theres a tie, false if there is an empty space or if there is a winner
	 * Use the value of the winner field to determine which case occurs
	 */
	public boolean checkTie(boolean wipeIfTied) {
		if (checkWinner())
			return false;

		for (int x = 0; x < 3; x++)
			for (int y = 0; y < 3; y++)
				if (board[x][y] != Player.NOPLAYER)
					return false;

		if (wipeIfTied)
			initializeBoard();

		return true;
	}

	/**
	 * sets all squares in the board to unowned
	 */
	private void initializeBoard() {
		winner = Player.NOPLAYER;
		for (int x = 0; x < board.length; x++)
			for (int y = 0; y < board[x].length; y++)
				board[x][y] = Player.NOPLAYER;
	}

	/**
	 * checks if a player has won the board. Sets winner field value
	 * @return true if there is a winner, false if there is not
	 */
	private boolean checkWinner() {

		// Vertical and Vertical search
		for (int i = 0; winner == Player.NOPLAYER && i < 3; i++) {
			winner = (board[i][0] == board[i][1]) && (board[i][1] == board[i][2]) ? board[i][0] : winner; // Horizontal search
			winner = (board[0][i] == board[1][i]) && (board[1][i] == board[2][i]) ? board[0][i] : winner; // Vertical search
		}

		// Diagonal search (Top-Left to Bottom-Right)
		if (winner == Player.NOPLAYER)
			winner = ((board[0][0] == board[1][1]) && (board[1][1] == board[2][2])) ? board[0][0] : winner;

		// Diagonal search (Top-Right to Bottom-Left)
		if (winner == Player.NOPLAYER)
			winner = ((board[0][2] == board[1][1]) && (board[1][1] == board[2][0])) ? board[0][2] : winner;

		return winner != Player.NOPLAYER;
	}
}
