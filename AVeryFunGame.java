import java.util.Scanner;
import java.util.Random;

public class AVeryFunGame{
	public static void main(String[] args){
		Scanner scnr = new Scanner(System.in);
		
		// Getting user's input
		System.out.print("How many games would you like to play? ");
		int numGame = scnr.nextInt();

		for (int i = 1; i <= numGame; i++){
			// Generate a randomly preconfigured array
			int[] a = generatePiles();

			System.out.println("Game " + i + ":");

			int j = 0;
			while (!gameOver(a)){

				System.out.print("  Turn " + j + ": \t");
				takeTurn(a);
				printArray(a);
				j++;

			}
		}
		
	}

	public static boolean gameOver(int[] piles){

		int i = 0;
		
		/* The sum of the elements in the array always remain 45 regardless of how we modify them.
		   Since any permutation of the set {1,2,3,4,5,6,7,8,9} yields the sum of 45. The necessary condition for the game to be over is
		   1/ All elements < 9 
	       2/ There are no non-zero duplicates */

		// Parsing through the non-zero elements of the array
		while (piles[i] > 0){

			// Check for elements that are > 9
			if (piles[i] > 9){
				return false;
			}
			else {

				// Check for duplicates
				for (int j = i + 1; piles[j] > 0; j++){
					if (piles[i] == piles[j]) return false; 
				}

			}

			i++;
		}

		return true;
	}

	public static void takeTurn(int[] piles){

		int i = 0;
		int sum = 0;

		// Parsing through the non-zero elements of the array
		while (piles[i] > 0){

			// Encounting element of 1 is an edge case that requires left shifting
			// We need to use the while loop in case there's a sequence of 1
			while (piles[i] == 1){
				sum++;
				shiftLeft(piles,i); 
			}

			// Making sure we're still in the non-zero elements' territory after all the posible shifts above.
			// Decrement the element and increment the sum by 1, then move on to the next element.
			// if the left shift pushes us into the 0's part of the array, do nothing and the loop won't run again
			if (piles[i] > 0){
				piles[i] --;
				sum ++;
				i++;
			}
		}

		// after the loop, we are now in the 0's territory. Set the first 0 element to sum, according to the rule.
		piles[i] = sum;

	}
	public static void shiftLeft(int[] x, int index){

		// Keep shifting left until encounting the first 0, starting at index + 1
		for (int i = index; i < x.length; i++){

			x[i] = x[i+1];
			if (x[i] == 0) break;	
			
		}


	}

	public static void printArray(int[] x){
		int i = 0;

		while (x[i] > 0){
			System.out.print(x[i] + " ");
			i++;
		}

		System.out.println();
	}

	public static int[] generatePiles(){
		//Create and initialize a zero filled array of 45 elements
		int [] arr = new int[45];
		Random rnd = new Random();

		int i = 0;
		int sum = 0;

		// Create random configuration for the array

		while ((sum < 45)){

			int rndNum = rnd.nextInt(45) + 1;
			
			if(sum + rndNum <= 45){
				arr[i] = rndNum;
				sum += arr[i];
			}
			else {
				arr[i] = 45 - sum;
				sum = 45;
			}

			i++;
		}

		return arr;
	}
}