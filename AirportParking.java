import java.util.Scanner;

public class AirportParking{
	public static void main(String[] args){
		Scanner scnr = new Scanner(System.in);

		System.out.print("Enter number of parking minutes: ");
		int minInput = scnr.nextInt();

		int numDays = minInput / 1440;      // number of days parking
		int totalMins = minInput % 1440;    // total mins left after num days are determined
		int num30Mins = totalMins / 30 ;    // blocks of 30 mins
		int numMins = totalMins % 30;       // mins left after num blocks are determined
		int amountCharged = 24 * numDays;   //initialized with day charges counted so we can focus on trivial ones.

		if (numMins > 0){
				num30Mins++;
			} 

		if  (minInput < 0){
			System.out.println("Error - Number out of bound.");
		}
		else {
			switch (num30Mins){
				case 0:
				case 1:
					break;
				case 2:
					amountCharged += 2;
					break;
				default:
					int over1HCharged = 2 + (1 * (num30Mins-2));
					if (over1HCharged >= 24) { 
						over1HCharged = 24;
					}
					amountCharged += over1HCharged;
					break;
			}
			System.out.println("Total charged: $" + amountCharged);

		}
		
	}
}