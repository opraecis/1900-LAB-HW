import java.util.Scanner;
/*

This program contains some extra code that enables the following:

- Converting the board to one that's filled with 0s (our team) and 1s (the opponent's). Any empty cells are filled with '-'.
- Printing the board.
- Letting user pick a position of the queen to test the program.


*/
public class Chess{
	public static void main(String[] args){
		Scanner scnr = new Scanner(System.in);

		String[][] chessBoard =	 { {"bR", "bN", "bB", "", "", "bR", "bK", ""},
					   {"bP", "bP", "", "", "bP", "bP", "bB", "bP"},
					   {"", "", "bP", "bP", "", "bN", "bP", ""},
					   {"", "", "", "", "", "", "", ""},
					   {"", "", "wP", "bP", "", "", "", ""},
					   {"", "", "wN", "", "", "wN", "wP", ""},
					   {"wP", "wP", "", "", "wP", "wP", "wB", "wP"},
					   {"wR", "", "wB", "", "", "wR", "wK", ""} };

		System.out.print("Please enter the position and color of our Queen (row column color): ");

		int row = scnr.nextInt();
		int col = scnr.nextInt();
		char color = scnr.next().charAt(0);

		chessBoard[row - 1][col - 1] = color + "Q";
		
		System.out.println("Number of threats: " + countQueenThreats(chessBoard, color));
	}

	public static int[] findQueen(String[][] board, char color){
		int[] queenPos = new int[] {-1,-1};
		String queen = color + "Q";
		//Finding the queen

		// Going vertically down the chess board
		for (int i = 0; i < board.length; i++){

			// Going horizontally across the chess board
			for (int j = 0; j < board[i].length; j++){

				//Storing the queen's indice
				if (board[i][j].equals(queen)){

					queenPos[0] = i; // Queen's row
					queenPos[1] = j; // Queen's column
					return queenPos;
					
				}
			}
		}

		return queenPos;

	}

	public static void reBoard(String[][] board, char color){

		// This method converts the board of chess pieces into that of 0s and 1s
		// 0s are our team's , 1s are our opponent's
		// - is an empty square , Q is our Queen
		char opponent = (color =='b') ? 'w' : 'b';

		for (int i = 0; i < board.length; i++){
			for (int j = 0; j < board[i].length; j++){
				if (board[i][j] != ""){
					if (board[i][j].equals(color + "Q")){
						board[i][j] = "Q";
					}
					else if (board[i][j].charAt(0) == color){
						board[i][j] = "0";
					}
					else if (board[i][j].charAt(0) == opponent){
						board[i][j] = "1";
					}
				} 
				else board[i][j] = "-";
			}
		}

	}

	public static void printBoard(String[][] board){

		System.out.println("Current state of the board (0 is our team's, 1 is the opponent's, Q is our Queen: \n" +
						   "-----------------------------------------------" );

		for (int i = 0; i < board.length; i++){
			for (int j = 0; j < board[i].length; j++){

					System.out.print(board[i][j] + "|");

			}
			System.out.println();
		}
		System.out.println("-----------------------------------------------");
		
	}

	public static int countQueenThreats(String[][] board, char color){
		int qRow = findQueen(board, color)[0];
		int qColumn = findQueen(board, color)[1];

		//boolean variables to check if a chess piece is found in each direction.
		boolean left = false, right = false , up = false, down = false; 
		boolean upleft = false, downleft = false, upright = false, downright = false;
		int numThreats = 0;		

		if (findQueen(board,color)[0] == -1) return -1; //Return -1 if queen is not found.

		reBoard(board, color); // convert to a board of 0s and 1s just for visual aid.

		printBoard(board);

		int i = qColumn - 1;  
		while (left == false && i >= 0){ // Checking LEFT
			if (board[qRow][i] != "-"){
				left = true;
				if (board[qRow][i].equals("1")) numThreats ++;
			} 
			i--;
		}

		i = qColumn + 1; 
		while (right == false && i < 8){ // Checking RIGHT
			if (board[qRow][i] != "-"){
				right = true;
				if (board[qRow][i].equals("1")) numThreats ++;
			}
			i++;
		}

		i = qRow - 1;
		while (up == false && i >= 0){ // Checking UP
			if (board[i][qColumn] != "-"){
				up = true;
				if (board[i][qColumn].equals("1")) numThreats ++;
			}
			i--;
		}

		i = qRow + 1;
		while (down == false && i < 8){ // Checking DOWN
			if (board[i][qColumn] != "-"){
				down = true;
				if (board[i][qColumn].equals("1")) numThreats ++;
			}
			i++;
		}

		i = qColumn - 1;
		int j = qRow - 1;
		while (upleft == false && i >= 0 && j >= 0){ // Checking UP LEFT
			if (board[j][i] != "-"){
				upleft = true;
				if (board[j][i].equals("1")) numThreats ++;
			}
			i--;
			j--;
		}

		i = qColumn - 1;
		j = qRow + 1;
		while (downleft == false && i >= 0 && j < 8){ // Checking DOWN LEFT
			if (board[j][i] != "-"){
				downleft = true;
				if (board[j][i].equals("1")) numThreats ++;
			}
			i--;
			j++;
		}

		i = qColumn + 1;
		j = qRow - 1;
		while (upright == false && i < 8 && j >= 0){ // Checking UP RIGHT
			if (board[j][i] != "-"){
				upright = true;
				if (board[j][i].equals("1")) numThreats ++;
			}
			i++;
			j--;
		}

		i = qColumn + 1;
		j = qRow + 1;
		while (downright == false && i < 8 && j < 8){ // Checking DOWN RIGHT
			if (board[j][i] != "-"){
				downright = true;
				if (board[j][i].equals("1")) numThreats ++;
			}
			i++;
			j++;
		}
		
		return numThreats;
	}
}
