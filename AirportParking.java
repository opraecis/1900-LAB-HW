import java.util.Scanner;

public class AirportParking{
	public static void main(String[] args){
		Scanner scnr = new Scanner(System.in);

		//Taking Input
		System.out.print("Enter number of parking minutes: ");
		int minInput = scnr.nextInt();

		int numDays = minInput / 1440;
		int totalMins = minInput % 1440;
		int num30Mins = totalMins / 30 ;
		int numMins = totalMins % 30;
		int amountCharged = 24 * numDays; //initialized with day charges counted so we can focus on trivial ones.

		if  (minInput < 0){
			System.out.println("Error - Number out of bound.");
		}
		else {
			if (numMins > 0){
				num30Mins++;
			} 

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