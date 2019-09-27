import java.util.Scanner;

public class DaysInMonth{
	public static void main(String[] args){
		Scanner scnr = new Scanner(System.in);
		int numDays = 0;

		//Taking Input
		System.out.print("Enter month (1-12): ");
		int numMonth = scnr.nextInt();
		System.out.print("Enter year (1000-3000): ");
		int numYear = scnr.nextInt();

		//Computations
		if ((numMonth < 1) || (numMonth > 12) || (numYear < 1000) || (numYear > 3000)){
			System.out.println("Error - month and/or year out of bounds.");
		}
		else {
			switch (numMonth){
				case 2:
					numDays = 28;
					if ((numYear % 400) == 0){
						numDays = 29;
					}
					else if (((numYear % 4) == 0) && ((numYear % 100) != 0)) {
						numDays = 29;
					}
					break;
				case 4:
				case 6:
				case 9:
				case 11:
					numDays = 30;
					break;
				default:
					numDays = 31;
					break;
				}
				System.out.println(numMonth + "/" + numYear + " contains " + numDays + " days.");
				
		}


	}
}