import java.util.Scanner;

public class PrimeFactors{
	public static void main(String[] args){
		Scanner scnr = new Scanner(System.in);

		System.out.println("Enter an integer: ");
		int currentInt = scnr.nextInt();

		int i = 1;
		while (currentInt > 1){
			i++;
			if ((currentInt % i) == 0) { 
				System.out.println(i);
				currentInt /= i;
				i = 1;
			} 
		}
	}
}