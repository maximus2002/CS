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

public class TestCoverageClassTestSC2 {


    @Test
    public void TestCoverageClassTestSC2() {
        // This method achieves 100% statement coverage less than 100% branch coverage and not reveal the division by zero fault therein
        // The test suite is TC1(1,1)

        TestCoverageClass test  = new TestCoverageClass();
        test.testCoverageMethod2(1,1);

    }



}
