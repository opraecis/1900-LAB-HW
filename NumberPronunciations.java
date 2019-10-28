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
		String[] tensDigit = new String[]{"twenty","thirty","forty","fifty","sixty","seventy","eighty","ninety"};
		String[] teensDigit = new String[]{"ten","eleven","twelve","thirteen","fourteen","fifteen","sixteen","seventeen","eighteen","nineteen"};
		
		if (n >= 20){ // If n >= 20 and the singles digit is not 0 (like 30 40 ..)then consider printing it in words, otherwise ignore the singles digit because it would be "zero".
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
			// if n != 100, 200, 300... then print the tens and singles digits in words, otherwise ignore them because they would be "zero"s
			return  ((n%100)>0) ? (twoDigitToStr(n / 100) + " hundred " + twoDigitToStr(n % 100)) : (twoDigitToStr(n / 100) + " hundred");
	
		} 
		else {
			return twoDigitToStr(n);
		}
	}

	public static String numToStr(int n){
		String numResult = "";

		if (n == 0) return "zero"; //special case when the number is 0

		if (n >= 1000000000){ //when the number is in the billions or less
			numResult += threeDigitToStr(n / 1000000000) + " billion ";
			n = (n % 1000000000);
		}
		if (n >= 1000000){ // when the number is in the millions or less
			numResult += threeDigitToStr(n / 1000000) + " million ";
			n = (n % 1000000);
		}
		if (n >= 1000){ // when the number is in the thousands or less
			numResult += threeDigitToStr(n / 1000) + " thousand ";
			n = (n % 1000);
		}
		if ((n < 1000) && (n != 0)){ //when the number is in the hundreds or less
			numResult += threeDigitToStr(n);
		}
		return numResult;
	}
}
