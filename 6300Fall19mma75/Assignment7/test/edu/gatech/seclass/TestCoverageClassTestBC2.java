package edu.gatech.seclass;

import org.junit.Test;

//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.fail;

/**
 * Testing class created for use in Georgia Tech CS6300, for posting only in assigned private Georgia Tech repositories.
 *
 * This is an test class for a simple class that represents a string, defined
 * as a sequence of characters.
 *
 * You should implement your tests in this class.  Do not change the test names.
 */

public class TestCoverageClassTestBC2 {


    @Test
    public void TestCoverageClassTestBC2() {
        // This method achieves 100% branch coverage and reveal the division by zero fault therein
        // The test suite is TC1(1,1), TC2(1,-1), TC3(-1,1), TC4(-1,-1), TC5(0,0)

        TestCoverageClass test  = new TestCoverageClass();
        test.testCoverageMethod2(1,1);
        test.testCoverageMethod2(1,-1);
        test.testCoverageMethod2(-1,1);
        test.testCoverageMethod2(-1,-1);
        test.testCoverageMethod2(0,0);

    }



}
