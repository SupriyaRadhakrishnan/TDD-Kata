package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.StringCalculator;

class StringCalculatorTest {
/**
 * First 8 tests positive numbers and different scenarios related to it
 * Next 8 tests negative numbers and different scenarios related to it
 * 17th test is for delimiter like [***]
 * 18th test is for delimiter like [*][%]
 * Have written 4 test cases for getDemiliter(); this return the demiliter and the startindex of the actual String. 
 */
	@Test
	void testAddWithEmptyString() {
		int actual = StringCalculator.add("");
		assertEquals(0,actual);
	}

	@Test
	void testAddWithOneValue() {
		int actual = StringCalculator.add("6");
		assertEquals(6,actual);
	}
	
	@Test
	void testAddWithMultipleValue() {
		int actual = StringCalculator.add("1,2,3,4");
		assertEquals(10,actual);
	}
	@Test
	void testAddWithSingleNLValue() {
		int actual = StringCalculator.add("1,2,3\n4");
		assertEquals(10,actual);
	}
	
	@Test
	void testAddWithMultipleNLValue() {
		int actual = StringCalculator.add("1,2\n5,3\n4");
		assertEquals(15,actual);
	}
	
	@Test
	void testAddWithMultipleNLInMultipleValue() {
		int actual = StringCalculator.add("1,2\n5\n10,3\n4");
		assertEquals(25,actual);
	}
	@Test
	void testAddWithOtherDelimitersValue() {
		int actual = StringCalculator.add("//;1;2");
		assertEquals(3,actual);
	}
	@Test
	void testAddWithOnlyDelimitersValue() {
		int actual = StringCalculator.add("//;");
		assertEquals(0,actual);
	}
	@Test
	void testAddWithOtherDelimitersAndNLValue() {
		int actual = StringCalculator.add("//;\n1;2");
		assertEquals(3,actual);
	}
/*** All the above cases with negative ***/	
	
	@Test
	void testAddWithMultipleNegativeNumbers() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			StringCalculator.add("-2,4,5,-8,7");
	    }); 
		String expectedMessage = "negatives not allowed";
	    String actualMessage = exception.getMessage();
		 assertTrue(actualMessage.contains(expectedMessage));
	}
	@Test
	void testAddWithOneNegValue() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			StringCalculator.add("-2");
	    }); 
		String expectedMessage = "negatives not allowed";
	    String actualMessage = exception.getMessage();
		 assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void testAddWithNegOutSideSingleNLValue() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			StringCalculator.add("1,2,-3\n4");
	    }); 
		String expectedMessage = "negatives not allowed";
	    String actualMessage = exception.getMessage();
		 assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void testAddWithNegInsideSingleNLValue() {
		
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			StringCalculator.add("1,2\n-5,3\n4");
	    }); 
		String expectedMessage = "negatives not allowed";
	    String actualMessage = exception.getMessage();
		 assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void testAddWithMultipleNLInAndOutMultipleValue() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			StringCalculator.add("1,-2\n-5\n10,3\n4");
	    }); 
		String expectedMessage = "negatives not allowed";
	    String actualMessage = exception.getMessage();
		 assertTrue(actualMessage.contains(expectedMessage));
	}

	
	@Test
	void testAddWithNegOtherDelimitersValue() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			StringCalculator.add("//;1;-2");
	    }); 
		String expectedMessage = "negatives not allowed";
	    String actualMessage = exception.getMessage();
		 assertTrue(actualMessage.contains(expectedMessage));
	}
	@Test
	void testAddWithNegOtherDelimitersAndNLValue() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			StringCalculator.add("//;\n1;-2");
	    }); 
		String expectedMessage = "negatives not allowed";
	    String actualMessage = exception.getMessage();
		 assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void testSequencedDelimitersValue() {
		int actual = StringCalculator.add("//[***]\n1***2***3");
		assertEquals(6,actual);
	}
	
	@Test
	void testMultipleDelimitersValue() {
		int actual = StringCalculator.add("//[*][%]\n1*2%3");
		assertEquals(6,actual);
	}
	
	@Test
	void testSingleDelimiter() {
		String actual[] = StringCalculator.getDelimiter("1,2,3,4");
		assertEquals(",",actual[0]);
		assertEquals("0",actual[1]);
	}
	@Test
	void testExplicitSingleDelimiter() {
		String actual[] = StringCalculator.getDelimiter("//;\n1;2;3");
		assertEquals(";",actual[0]);
		assertEquals("3",actual[1]);
	}
	@Test
	void testExplicitSequenceDelimiter() {
		String actual[] = StringCalculator.getDelimiter("//[***]\n1***2***3");
		assertEquals("***",actual[0]);
		assertEquals("7",actual[1]);
	}
	@Test
	void testExplicitMultipleDelimiter() {
		String actual[] = StringCalculator.getDelimiter("//[*][%]\n1***2***3");
		assertEquals("*%",actual[0]);
		assertEquals("8",actual[1]);
	}
	
	
}
