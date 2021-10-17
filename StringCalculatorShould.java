package com.wordpress.technologyconversations.tddtest;
 
import org.junit.Test;
import com.wordpress.technologyconversations.tdd.StringCalculator;
 
public class StringCalculatorTest {
    @Test(expected = RuntimeException.class)
    public final void whenMoreThan2NumbersAreUsedThenExceptionIsThrown() {
        StringCalculator.add("1,2,3");
    }
    @Test
    public final void when2NumbersAreUsedThenNoExceptionIsThrown() {
        StringCalculator.add("1,2");
        Assert.assertTrue(true);
    }
    @Test(expected = RuntimeException.class)
    public final void whenNonNumberIsUsedThenExceptionIsThrown() {
        StringCalculator.add("1,X");
    }
    @Test
    public final void whenEmptyStringIsUsedThenReturnValueIs0() {
    Assert.assertEquals(0, StringCalculator.add(""));
    }
    @Test
public final void whenOneNumberIsUsedThenReturnValueIsThatSameNumber() {
    Assert.assertEquals(3, StringCalculator.add("3"));
}
 
@Test
public final void whenTwoNumbersAreUsedThenReturnValueIsTheirSum() {
    Assert.assertEquals(3+6, StringCalculator.add("3,6"));
}

@Test
    public final void whenAnyNumberOfNumbersIsUsedThenReturnValuesAreTheirSums() {
        Assert.assertEquals(3+6+15+18+46+33, StringCalculator.add("3,6,15,18,46,33"));
    }
@Test
public final void whenNewLineIsUsedBetweenNumbersThenReturnValuesAreTheirSums() {
    Assert.assertEquals(3+6+15, StringCalculator.add("3,6n15"));
}

@Test
public final void whenDelimiterIsSpecifiedThenItIsUsedToSeparateNumbers() {
    Assert.assertEquals(3+6+15, StringCalculator.add("//;n3;6;15"));
}

@Test(expected = RuntimeException.class)
public final void whenNegativeNumberIsUsedThenRuntimeExceptionIsThrown() {
    StringCalculator.add("3,-6,15,18,46,33");
}
@Test
public final void whenNegativeNumbersAreUsedThenRuntimeExceptionIsThrown() {
    RuntimeException exception = null;
    try {
        StringCalculator.add("3,-6,15,-18,46,33");
    } catch (RuntimeException e) {
        exception = e;
    }
    Assert.assertNotNull(exception);
    Assert.assertEquals("Negatives not allowed: [-6, -18]", exception.getMessage());
}
}




public class StringCalculator {
    public static final void add(final String numbers) {
        String[] numbersArray = numbers.split(",");
        if (numbersArray.length > 2) {
            throw new RuntimeException("Up to 2 numbers separated by comma (,) are allowed");
        } else {
            for (String number : numbersArray) {
                Integer.parseInt(number); // If it is not a number, parseInt will throw an exception
            }
        }
    }

public static final int add(final String numbers) { 
    String[] numbersArray = numbers.split(",");
    if (numbersArray.length > 2) {
        throw new RuntimeException("Up to 2 numbers separated by comma (,) are allowed");
    } else {
        for (String number : numbersArray) {
            if (!number.isEmpty()) {
                Integer.parseInt(number);
            }
        }
    }
    return 0; // Added return
}

public static int add(final String numbers) {
    int returnValue = 0;
    String[] numbersArray = numbers.split(",");
    if (numbersArray.length > 2) {
        throw new RuntimeException("Up to 2 numbers separated by comma (,) are allowed");
    }
    for (String number : numbersArray) {
        if (!number.trim().isEmpty()) { // After refactoring
            returnValue += Integer.parseInt(number);
        }
    }
    return returnValue;
}

public static int add(final String numbers) {
    int returnValue = 0;
    String[] numbersArray = numbers.split(",");
    
    for (String number : numbersArray) {
        if (!number.trim().isEmpty()) { // After refactoring
            returnValue += Integer.parseInt(number);
        }
    }
    return returnValue;
}

public static int add(final String numbers) {
    int returnValue = 0;
    String[] numbersArray = numbers.split(",|n"); // Added |n to the split regex
    for (String number : numbersArray) {
        if (!number.trim().isEmpty()) {
            returnValue += Integer.parseInt(number.trim());
        }
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
 
private static int add(final String numbers, final String delimiter) {
    int returnValue = 0;
    String[] numbersArray = numbers.split(delimiter);
    for (String number : numbersArray) {
        if (!number.trim().isEmpty()) {
            returnValue += Integer.parseInt(number.trim());
        }
    }
    return returnValue;
}
private static int add(final String numbers, final String delimiter) {
    int returnValue = 0;
    String[] numbersArray = numbers.split(delimiter);
    List negativeNumbers = new ArrayList();
    for (String number : numbersArray) {
        if (!number.trim().isEmpty()) {
            int numberInt = Integer.parseInt(number.trim());
            if (numberInt < 0) {
                negativeNumbers.add(numberInt);
            }
            returnValue += numberInt;
        }
    }
    if (negativeNumbers.size() > 0) {
        throw new RuntimeException("Negatives not allowed: " + negativeNumbers.toString());
    }
    return returnValue;     
}

}