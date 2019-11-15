import java.util.Random;

public class Sudoku{
	public static void main(String[] args){
		int[][] board = new int[][] {{5,3,4,6,7,8,9,1,2},
									 {6,7,2,1,9,5,3,4,8},
									 {1,9,8,3,4,2,5,6,7},
									 {8,5,9,7,6,1,4,2,3},
									 {4,2,6,8,5,3,7,9,1},
									 {7,1,3,9,2,4,8,5,6},
									 {9,6,1,5,3,7,2,8,4},
									 {2,8,7,4,1,9,6,3,5},
									 {3,4,5,2,8,6,1,7,9}};
	
		System.out.println(isValidSolution(board));

	}	

	public static boolean isValidSolution(int[][] board){

		// Step 1: Check if entries are valid and non duplicate across rows and columns
		
		for ( int i = 0; i < board.length; i++ ){
			for ( int j = 0; j < board[i].length; j++ ){

				// for each entry, check for duplicates or out-of-range entries thru the rest of its row and column
				// note that only board[i][j] needs to be checked because it eventually will take on values of every entries on the board.

				for ( int k = j + 1; k < board[i].length; k++){ // through the rest of the row
				
					if (board[i][j] == board[i][k] || board[i][j] > 9 || board[i][j] < 1) return false;

				}

				for ( int k = i + 1; k < board.length; k++){ // through the rest of the column

					if (board[i][j] == board[k][j] || board[i][j] > 9 || board[i][j] < 1) return false; 

				}
			}
		}

		// Step 2: Check if each subgrid is valid

		for (int i = 0; i < 9; i+= 3){
			for (int j = 0; j < 9; j+=3){

				if (isValidSubGrid(getSubGrid(board,i,j)) == false) return false;

			}
		}

		return true;

	}

	public static int[][] getSubGrid(int[][] board, int startRow, int startColumn){

		// Get 3x3 subgid given the starting row and column position on the board

		int[][] sub = new int[3][3];

		for (int i = 0, r = startRow ; i < 3; i++, r++){
			for (int j = 0, c = startColumn ; j < 3; j++, c++){
				sub[i][j] =  board[r][c];
			}
		}

		return sub;
	}

	public static boolean isValidSubGrid(int[][] sub){

		// Check if a subgrid is valid

		int[] counter = new int[9];

			for(int i = 0; i < sub.length; i++){
				for (int j = 0; j < sub[i].length; j++){
					switch (sub[i][j]){
						case 1:
							counter[0]++;
							break;
						case 2:
							counter[1]++;
							break;
						case 3:
							counter[2]++;
							break;
						case 4:
							counter[3]++;
							break;
						case 5:
							counter[4]++;
							break;
						case 6:
							counter[5]++;
							break;
						case 7:
							counter[6]++;
							break;
						case 8:
							counter[7]++;
							break;
						case 9:
							counter[8]++;
							break;
					}
				}
			}

			for (int i = 0; i < 9; i++){
				if (counter[i] > 1) return false; // return false if an integer appears twice or more in the subgrid
			}

			return true;		
	}
}