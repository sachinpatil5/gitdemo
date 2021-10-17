package session_14;

import java.util.ArrayList;
import java.util.List;

public class StringCalculator {
	
	private static int add(final String numbers, final String delimiter) {
        int returnValue = 0;
        String[] numbersArray = numbers.split(delimiter);
        List negativeNumbers = new ArrayList();
        for (String number : numbersArray) {
                if (!number.trim().isEmpty()) {
                        int numberInt = Integer.parseInt(number.trim());
                        if (numberInt < 0) {
                                negativeNumbers.add(numberInt);
                        } else if (numberInt <= 1000) {
                                returnValue += numberInt;
                        }
                }
        }
        if (negativeNumbers.size() > 0) {
                throw new RuntimeException("Negatives not allowed: " + negativeNumbers.toString());
        }
        return returnValue;                
}
	
	public static int add(final String numbers) {
	    String delimiter = ",|n";
	    String numbersWithoutDelimiter = numbers;
	    if (numbers.startsWith("//")) {
	        int delimiterIndex = numbers.indexOf("//") + 2;
	        delimiter = numbers.substring(delimiterIndex, delimiterIndex + 1);
	        numbersWithoutDelimiter = numbers.substring(numbers.indexOf("n") + 1);
	    }
	    return add(numbersWithoutDelimiter, delimiter);
	}
	 
	public static void main(String args[]) {
	
//	Requirement 1: The method can take 0, 1 or 2 numbers separated by comma (,).
		System.out.println(add("10,20",","));
		
	
//	Requirement 2: For an empty string the method will return 0
		System.out.println(add("",","));
		
//	Requirement 3: Method will return their sum of numbers
		System.out.println(add("20,20",","));
		
//	Requirement 4: Allow the Add method to handle an unknown amount of numbers
		System.out.println(add("10,20,30,40",","));
	
//	Requirement 5: Allow the Add method to handle new lines between numbers (instead of commas).
		System.out.println(add("3,6n15"));
		
//	Requirement 6: Support different delimiters
		System.out.println(add("//;n3;6;15"));
		
//		Requirement 8: Numbers bigger than 1000 should be ignored
			System.out.println(add("5,2000"));
		
//	Requirement 7: Negative numbers will throw an exception
		System.out.println(add("13,-16,15,-8"));
		

	}

}
