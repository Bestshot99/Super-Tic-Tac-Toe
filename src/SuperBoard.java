/**
 * Tic Tac Toe board consisting if smaller boards
 *
 * @see Board
 */
public class SuperBoard {
	/**
	 * the winner of the board
	 */
	protected Player winner = Player.NOPLAYER;
	private Board[][] board = new Board[3][3];

	/**
	 * constructs and initializes the board
	 */
	public SuperBoard() {
		initializeBoard();
	}

	/**
	 * returns the board at the given location
	 * @param x the x coordinate relative to the array
	 * @param y the y coordinate relative to the array
	 * @return null if the board has already been won, otherwise the selected board
	 */
	public Board selectSquare(int x, int y) {
		if (board[x][y].winner != Player.NOPLAYER)
			return null;

		return board[x][y];
	}

	/**
	 * checks if the board is tied
	 * @param wipeIfTie should the board be wiped if there is a tie
	 * @return true if theres a tie, false if there is an empty board or if there is a winner
	 * Use the value of the winner field to determine which case occurs
	 */
	public boolean isTied(boolean wipeIfTie) {
		if (checkWinner())
			return false;

		for (int x = 0; x < 3; x++)
			for (int y = 0; y < 3; y++)
				if (board[x][y].winner != Player.NOPLAYER)
					return false;

		if (wipeIfTie)
			initializeBoard();

		return true;
	}

	/**
	 * sets all squares to the board to unowned
	 */
	private void initializeBoard() {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				board[i][j] = new Board();
		winner = Player.NOPLAYER;
	}

	/**
	 * checks if a player has won the board. Sets the winner field value
	 * @return true if there is a winner, false if there is not
	 */
	private boolean checkWinner() {
		// Horizontal and vertical check
		for (int i = 0; winner == Player.NOPLAYER && i < 3; i++) {
			winner = ((board[i][0].winner == board[i][1].winner) && (board[i][1].winner == board[i][2].winner)) ? board[i][0].winner : winner; // Horizontal check
			winner = ((board[0][i].winner == board[1][i].winner) && (board[1][i].winner == board[2][i].winner)) ? board[0][i].winner : winner; // Vertical check
		}

		// Diagonal search (Top-Left to Bottom-Right)
		if (winner == Player.NOPLAYER) {
			winner = board[0][0].winner == board[1][1].winner && board[1][1].winner == board[2][2].winner ? board[1][1].winner : winner;
		}

		// Diagonal search (Top-Right to Bottom-Left)
		if (winner == Player.NOPLAYER) {
			winner = board[0][2].winner == board[1][1].winner && board[1][1].winner == board[2][0].winner ? board[1][1].winner : winner;
		}

		return winner != Player.NOPLAYER;
	}
}
