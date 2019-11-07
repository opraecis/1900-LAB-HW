import java.util.Scanner;

public class NumberPronunciations{
	public static void main(String[] args){
		Scanner scnr = new Scanner(System.in);

		int numIn = 0;
		while (numIn >= 0){
			System.out.print("Enter an integer to pronounce (any negative value to exit): ");
			numIn = scnr.nextInt();
			if (numIn >= 0){
				System.out.println(numToStr(numIn));
			}
		}
	}

	public static String oneDigitToStr(int n){

		String[] toStr = new String[]{"zero","one","two","three","four","five","six","seven","eight","nine"};
		return toStr[n];

	}

	public static String twoDigitToStr(int n){
		//When dealing with two digits, we have numbers that are in the range 10-19 and need have a category of their own.

		String[] tensDigit = new String[]{"twenty","thirty","forty","fifty","sixty","seventy","eighty","ninety"};
		String[] teensDigit = new String[]{"ten","eleven","twelve","thirteen","fourteen","fifteen","sixteen","seventeen","eighteen","nineteen"};
		
		if (n >= 20){ 

		// If n >= 20 and the singles digit is not 0 (like 31 42 ad not 30, 40 ..)then consider printing everything in words
		// otherwise ignore the '0' singles digit because it would be "zero", which we don't need pronouced (e.g twenty zero, fifty zero)

			return ((n % 10) != 0) ? (tensDigit[(n / 10) - 2] + " " + oneDigitToStr(n % 10)) : (tensDigit[(n / 10) - 2]);
		
		}
		else if ((n / 10) == 1){ // If 10<=n<20
			
			return teensDigit[n % 10];
		
		}
		else { // If n < 10
		
			return oneDigitToStr(n);
		
		}
	}

	public static String threeDigitToStr(int n){

		if (n >= 100){

			// if n != 100, 200, 300... then print everything in words
			// otherwise ignore pronouncing the tens and singles digits, which would be "zero"s
			return  ((n % 100)>0) ? (twoDigitToStr(n / 100) + " hundred " + twoDigitToStr(n % 100)) : (twoDigitToStr(n / 100) + " hundred");
	
		} 
		else {
			return twoDigitToStr(n);
		}
	}

	public static String numToStr(int n){

		String numResult = "";

		if (n == 0) return "zero"; //special case when the number is 0

		/* Pronounce the number by filtering it through different layers that process different orders of magnitude. 
		That can be achieved through removing the pronounced digits after each layer and send it down to the lower layers */

		//when the number is in the billions
		//pronounce the billions digit
		if (n >= 1000000000){ 
			numResult += threeDigitToStr(n / 1000000000) + " billion ";
			// Return n with pronounced billions digits removed, for further processing
			n = (n % 1000000000);
		}

		// when the number is in the millions or less
		// pronounce the millions, tens of millions, and hundreds of millions digits in a group of three
		if (n >= 1000000){ 
			numResult += threeDigitToStr(n / 1000000) + " million ";
			// Return n with pronounced digits removed, for further processing
			n = (n % 1000000);
		}

		// when the number is in the thousands or less
		// pronounce the thousands, tends of thousands, and hundreds of thousands digits in a group of three
		if (n >= 1000){ 
			numResult += threeDigitToStr(n / 1000) + " thousand ";
			// Return n with pronounced digits removed, for further processing
			n = (n % 1000);
		}

		//when the number is in the hundreds or less and not 0
		if ((n < 1000) && (n != 0)){ 
			numResult += threeDigitToStr(n);
		}
		return numResult;
	}
}
