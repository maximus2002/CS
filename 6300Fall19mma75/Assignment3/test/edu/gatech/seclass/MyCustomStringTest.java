package edu.gatech.seclass;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Testing class created for use in Georgia Tech CS6300, for posting only in assigned private Georgia Tech repositories.
 *
 * This is an test class for a simple class that represents a string, defined
 * as a sequence of characters.
 *
 * You should implement your tests in this class.  Do not change the test names.
 */

public class MyCustomStringTest {

    private MyCustomStringInterface mycustomstring;

    @Before
    public void setUp() {
        mycustomstring = new MyCustomString();
    }

    @After
    public void tearDown() {
        mycustomstring = null;
    }

    //Test Purpose: This is the first instructor example test.
    @Test
    public void testMostCommonChar1() {
        mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        assertEquals('t', mycustomstring.mostCommonChar());
    }
    //Test Purpose: Whether throw NullPointerException exception if the current string is null
    @Test(expected = NullPointerException.class)
    public void testMostCommonChar2() {
        mycustomstring.setString(null);
        mycustomstring.mostCommonChar();
    }
    //Test Purpose: Test whether method throws NullPointerException exception if the current string is empty
    @Test(expected = NullPointerException.class)
    public void testMostCommonChar3() {
        mycustomstring.setString("");
        mycustomstring.mostCommonChar();
    }
    //Test Purpose: Check whether all number will be counted as most common
    @Test
    public void testMostCommonChar4() {
        mycustomstring.setString("66666666666");
        assertEquals('6', mycustomstring.mostCommonChar());
    }
    //Test Purpose: Check whether method counts non alphabetic characters
    @Test
    public void testMostCommonChar5() {
        mycustomstring.setString("!!!!!66!!!");
        assertEquals('6', mycustomstring.mostCommonChar());
    }
    //Test Purpose: Check whether method handles all special characters
    @Test
    public void testMostCommonChar6() {
        mycustomstring.setString("#$%^&!@#");
        assertEquals(' ', mycustomstring.mostCommonChar());
    }

    //Test Purpose: This is the second instructor example test.
    @Test
    public void testFilterLetters1() {
        mycustomstring.setString("1234!!! H3y, L3t'5 put 50me d161ts in this 5tr1n6!11!1");
        assertEquals("1234!!! H3y, L3t'5 put 50m 161ts in this 5tr1n6!11!1", mycustomstring.filterLetters('E', false));
    }

    //Test Purpose: This the third instructor example test.
    @Test
    public void testFilterLetters2() {
        mycustomstring.setString("1234!!! H3y, L3t'5 put 50me d161ts in this 5tr1n6!11!1");
        assertEquals("1234!!! 3, 3'5  50 d161   516!11!1", mycustomstring.filterLetters('E', true));
    }
    //Test Purpose: This test checks whether method filterLetters suitably throws a NullPointerException if the current string is null
    @Test(expected = NullPointerException.class)
    public void testFilterLetters3() {
        mycustomstring.setString(null);
        mycustomstring.filterLetters('E', true);
    }
    //Test Purpose: This test checks if n case sensitive
    @Test
    public void testFilterLetters4() {
        mycustomstring.setString("1234!!! H3y, L3t'5 put 50me d161ts in this 5tr1n6!11!1");
        assertEquals("1234!!! H3y, L3t'5 put 50m 161ts in this 5tr1n6!11!1", mycustomstring.filterLetters('e', false));
    }
    //Test Purpose: This test checks if the method can remove all letters when more is false
    @Test
    public void testFilterLetters5() {
        mycustomstring.setString("1234!!! H3y, L3t'5 put 50me d161ts in this 5tr1n6!11!1");
        assertEquals("1234!!! 3, 3'5  50 161   516!11!1", mycustomstring.filterLetters('Z', false));
    }
    //Test Purpose: This test checks if the method can remove all letters when more is true
    @Test
    public void testFilterLetters6() {
        mycustomstring.setString("1234!!! H3y, L3t'5 put 50me d161ts in this 5tr1n6!11!1");
        assertEquals("1234!!! 3, 3'5  50 161   516!11!1", mycustomstring.filterLetters('a', true));
    }
    //Test Purpose: This test checks whether method filterLetters suitably throws a NullPointerException if n is not letter
    @Test(expected = IllegalArgumentException.class)
    public void testFilterLetters() {
        mycustomstring.setString("1234!!! H3y, L3t'5 put 50me d161ts in this 5tr1n6!11!1");
        mycustomstring.filterLetters('$', true);
    }
    //Test Purpose: Check all letter case and whether the method filterletters works when more is false
    @Test
    public void testFilterLetters8() {
        mycustomstring.setString("Abcdefg");
        assertEquals("defg",  mycustomstring.filterLetters('c', false));
    }
    //Test Purpose: Check all letter case and whether the method filterletters works when more is true
    @Test
    public void testFilterLetters9() {
        mycustomstring.setString("Abcdefg");
        assertEquals("Ab",  mycustomstring.filterLetters('c', true));
    }
    //Test Purpose: Check if method can handle special characters
    @Test
    public void testFilterLetters10() {
        mycustomstring.setString("!@#$%^&*()");
        assertEquals("!@#$%^&*()",  mycustomstring.filterLetters('c', true));
    }
    //Test Purpose: Check if method can handle all spaces
    @Test
    public void testFilterLetters11() {
        mycustomstring.setString("      ");
        assertEquals("      ",  mycustomstring.filterLetters('c', true));
    }
    //Test Purpose: Test a few more strings and make sure filter characters work as expected
    @Test
    public void testFilterLetters12() {
        mycustomstring.setString("I'd b3tt3r put 50me 123 d161ts in this 5tr1n6, right?");
        assertEquals("' 3tt3 ut 50 123 161ts  ts 5t16, t?",  mycustomstring.filterLetters('r', false));
    }


    //Test Purpose: This is the fourth instructor example test.
    @Test
    public void testConvertDigitsToProductsInSubstring1() {
        mycustomstring.setString("I'd b3tt3r put 50me 123 d161ts in this 5tr1n6, right?");
        mycustomstring.convertDigitsToProductsInSubstring(17, 27);
        assertEquals("I'd b3tt3r put 50me 6 d61ts in this 5tr1n6, right?", mycustomstring.getString());
    }

    //Test Purpose: This is the fifth instructor example test, demonstrating a test for an exception.  Your exceptions should be tested in this format.
    @Test(expected = MyIndexOutOfBoundsException.class)
    public void testConvertDigitsToProductsInSubstring2() {
        mycustomstring.convertDigitsToProductsInSubstring(2, 10);
    }
    //Test Purpose: This test checks whether method ConvertDigitsToProductsInSubstring suitably throws a IllegalArgumentException if the start position less than 1"
    @Test(expected = IllegalArgumentException.class)
    public void testConvertDigitsToProductsInSubstring3() {
        mycustomstring.convertDigitsToProductsInSubstring(0, 27);
    }
    //Test Purpose: This test checks whether method ConvertDigitsToProductsInSubstring behaves well in all special characters cases
    @Test
    public void testConvertDigitsToProductsInSubstring4() {
        mycustomstring.setString("!!!!!!!!!!");
        mycustomstring.convertDigitsToProductsInSubstring(1, 10);
        assertEquals("!!!!!!!!!!", mycustomstring.getString());
    }
    //Test Purpose: This test contains empty string to test whether method ConvertDigitsToProductsInSubstring can throw MyIndexOutOfBoundsException because empty string end position is less than string length
    @Test(expected = MyIndexOutOfBoundsException.class)
    public void testConvertDigitsToProductsInSubstring5() {
        mycustomstring.setString("");
        mycustomstring.convertDigitsToProductsInSubstring(1, 1);
    }
    //Test Purpose: This test checks multiple zeros in the string and see the method ConvertDigitsToProductsInSubstring is correct
    @Test
    public void testConvertDigitsToProductsInSubstring6() {
        mycustomstring.setString("0 00 000 0000 00000 00000");
        mycustomstring.convertDigitsToProductsInSubstring(4, 10);
        assertEquals("0 00 0 0000 00000 00000", mycustomstring.getString());
    }
    //Test Purpose: This test checks whether method ConvertDigitsToProductsInSubstring suitably throws a MyIndexOutOfBoundsException if input string is null"
    @Test(expected = MyIndexOutOfBoundsException.class)
    public void testConvertDigitsToProductsInSubstring7() {
        mycustomstring.setString(null);
        mycustomstring.convertDigitsToProductsInSubstring(2, 10);
    }
    //Test Purpose: This test checks whether method ConvertDigitsToProductsInSubstring suitably throws a MyIndexOutOfBoundsException if endposition is out of boundary
    @Test(expected = MyIndexOutOfBoundsException.class)
    public void testConvertDigitsToProductsInSubstring8() {
        mycustomstring.setString("CS6300");
        mycustomstring.convertDigitsToProductsInSubstring(1, 70);
    }
    //Test Purpose: This test checks whether method ConvertDigitsToProductsInSubstring provides non continuous sequence of digits product
    @Test
    public void testConvertDigitsToProductsInSubstring9() {
        mycustomstring.setString("1a2 3b4 5c6 7d8 9e10");
        mycustomstring.convertDigitsToProductsInSubstring(1, 20);
        assertEquals("1a2 3b4 5c6 7d8 9e0", mycustomstring.getString());
    }

    @Test
    public void testConvertDigitsToProductsInSubstring10() {
        mycustomstring.setString("asd45678910qwe");
        mycustomstring.convertDigitsToProductsInSubstring(3, 10);
        assertEquals("asd604800qwe", mycustomstring.getString());
    }

//    @Test(expected = NullPointerException.class)
//    public void testConvertDigitsToProductsInSubstring11() {
//        mycustomstring.setString("");
//        mycustomstring.convertDigitsToProductsInSubstring(3, 10);
//    }

}
