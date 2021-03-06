/**
 * 
 * @author Cogan
 * A game board that consists of six rows and seven columns
 */
public class Board {
	private final int ROWS = 6;
	private final int COLUMNS = 7;
	private final int FOUR_IN_A_ROW = 4;
	private int movesMade = 0;
	private char[][] board = new char[ROWS][COLUMNS];
	/**
	 * Just gives the board
	 * Note the board should only
	 * return a copy of the this board
	 * @return the board
	 */
	public Board() {
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				board[i][j] = ' ';
			}
		}
	}
	
	public Board(char[][] board) {
		if (board.length != ROWS || board[0].length != COLUMNS) {
			throw new IllegalArgumentException("Invalid Board! Must be six rows by seven columns!");
		} else {
			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board[i].length; j++) {
					this.board[i][j] = board[i][j];
				}
			}
		}
	}
	
	public char[][] getBoard() {
		char[][] returnedBoard = new char[ROWS][COLUMNS];
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				returnedBoard[i][j] = board[i][j];
			}
		}
		return returnedBoard;
	}
	
	/**
	 * 
	 * @param playerChar
	 * @param row
	 * @param column
	 */
	public boolean makeMove(char playerChar, int column) {
		
		for (int i = ROWS - 1; i >= 0; i--) {
			if (board[i][column] == ' ') {
				board[i][column] = playerChar;
				movesMade++;
				return true;
			}
		}
		return false;
		
	}
	
	public int getMoves() {
		return movesMade;
	}
	
	private String highestRightDiagonalStreak() {
		String result = "";
		// Diagonals going up
		for (int row = ROWS - 1; row >= 0; row--) {
			int currRow = row; // The row of the diagonal
			for (int column = 0; currRow < ROWS && column < COLUMNS; column++)
			{
				result = result + board[currRow][column];
				currRow++;
				
				/**
				 * Example (Might help to draw): 
				 * iteration 1 (Second for loop)
				 * row = 3 I just chose this but it starts at 5 and goes to 0
				 * currRow = 3
				 * column = 0 
				 * 
				 * iteration 2
				 * row = 3
				 * currRow = 4
				 * column = 1
				 * 
				 * iteration 3
				 * row = 3
				 * currRow = 5
				 * column = 2
				 * 
				 * iteration 4 (STOP since currRow is not less than rows)
				 * row = 3
				 * currRow = 6
				 * column = 3
				 */
			}
			result = result + " ";
		}
		// Diagonals going right
		for (int column = 1; column < COLUMNS; column++) {
			int currColumn = column; // The column of the diagonal
			for (int row = 0; row < ROWS && currColumn < COLUMNS; row++) {
				result = result + board[row][currColumn];
				currColumn++;
				
				/**
				 * Example (Might help to draw): 
				 * iteration 1 (Second for loop)
				 * column = 2 I just chose this but it starts at 1 (Rows took care of 0)and goes to 6 
				 * currColumn = 2
				 * row = 0 
				 * 
				 * iteration 2
				 * column = 2
				 * currColumn = 3
				 * row = 1
				 * 
				 * iteration 3
				 * column = 2
				 * currColumn = 4
				 * row = 2
				 * 
				 * iteration 4
				 * column = 2
				 * currColumn = 5
				 * row = 3
				 * 
				 * iteration 5
				 * column = 2
				 * currColumn = 6 Doesn't stop since there are seven columns not six
				 * row = 4
				 * 
				 * iteration 6 (STOP currColumn = 7)
				 * column = 2
				 * currColumn = 7
				 * row = 5
				 */
			}
			result = result + " ";
		}
		
		return result;
	}
	
	private String highestLeftDiagonalStreak() {
		String result = "";
		// NOT DONE WITH THIS YET
		// NEEDS TO BE ALMOST OPPOSITE OF RIGHTDIAGONAL
		// Diagonals going up
		for (int row = ROWS - 1; row >= 0; row--) {
			int currRow = row;
			for (int column = COLUMNS - 1; currRow < ROWS && column >= 0; column--)
			{
				result = result + board[currRow][column];
				currRow++;
			}
			result = result + " ";
		}
		// Diagonals going right
		for (int column = COLUMNS - 2; column >= 0;  --column) {
			int currColumn = column;
			for (int row = 0; row < ROWS && currColumn < COLUMNS; row++) {
				result = result + board[row][currColumn];
				currColumn++;
			}
			result = result + " ";
		}
		
		return result;
	}
	
	private String highestHorizontalStreak() {
		String result = "";
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				result = result + board[i][j];
			}
			result = result + " ";
		}
		
		return result;
	}
	
	private String highestVerticalStreak() {
		String result = "";
		for (int i = 0; i < COLUMNS; i++) {
			for (int j = 0; j < ROWS; j++) {
				result = result + board[j][i];
			}
			result = result + " ";
		}
		return result;
	}
	
	private int streak(String boardOrder) {
		int maxStreak = 0;
		String streak = "";
		for (int i = 0; i < boardOrder.length(); i++) {
			char currentChar = boardOrder.charAt(i);
			if (currentChar != ' ' && (streak.length() == 0 || currentChar == streak.charAt(0))) {
				streak = streak + currentChar;
			} else {
				maxStreak = Math.max(streak.length(), maxStreak);
				if(currentChar == ' ')
					streak = "";
				else
					streak = "" + currentChar;
				
			}
			
		}
		return Math.max(streak.length(), maxStreak);
		
	}
	
	/**
	 * 
	 * @return
	 */
	
	
	
	public boolean isWinningBoard() {
		if 		  (streak(highestHorizontalStreak()) >= 4
				|| streak(highestVerticalStreak()) >= 4
				|| streak(highestRightDiagonalStreak()) >= 4
				|| streak(highestLeftDiagonalStreak()) >= 4) {
			return true;
		}
		return false;
	}
	
	/**
	 * @Override
	 * @return
	 */
	public String toString() {
		String returnedString = "";
		for (int i = 0; i < ROWS; i++) {
			returnedString = returnedString + "[ " + board[i][0];
			for (int j = 1; j < COLUMNS; j++) {
				returnedString = returnedString + " | " + board[i][j];
			}
			returnedString = returnedString + "]";
			if (i < ROWS - 1)
				returnedString = returnedString + "\n\n";
		}
		return returnedString;
	}
	

	public int getMovesMade() {
		return movesMade;
	}

	public void setMovesMade(int movesMade) {
		this.movesMade = movesMade;
	}

	public int getROWS() {
		return ROWS;
	}

	public int getCOLUMNS() {
		return COLUMNS;
	}

	public int getFOUR_IN_A_ROW() {
		return FOUR_IN_A_ROW;
	}

	public void setBoard(char[][] board) {
		this.board = board;
	}
	
	
}