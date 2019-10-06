import java.util.Scanner;
import java.util.Random;

public class MasterCardGenerator{
	public static void main(String[] args){
		Scanner scnr = new Scanner(System.in);
		Random rand = new Random();

		System.out.print("Enter the number of cards: ");
		int numCard = scnr.nextInt();

		for (int i = 0; i < numCard; i++){
			int initType = rand.nextInt(2);
			String cardNum = "";
			int sumDigits = 0;

			if (initType == 0){
				cardNum += rand.nextInt(4) + 51;
			}
			else {
				cardNum += rand.nextInt(50000) + 222100;
			}

			int numInitDigits = cardNum.length();

			for (int j = 0; j < 15; j++) {
				int currentNum;
				
				if (j >= numInitDigits){
					currentNum = rand.nextInt(10);
					cardNum += currentNum;
				}
				else {
					currentNum = Integer.parseInt(Character.toString(cardNum.charAt(j)));
				}

				if ((j % 2) == 0) { currentNum *= 2;}
				if (currentNum > 9) { currentNum -= 9; }

				sumDigits += currentNum;
			}
			int lastDigit = (sumDigits % 10) == 0 ? 0 : (10 - (sumDigits % 10));
			cardNum += lastDigit;
			System.out.println(cardNum);
		}
	}
}