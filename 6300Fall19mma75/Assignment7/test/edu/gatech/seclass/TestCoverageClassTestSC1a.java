package edu.gatech.seclass;

import org.junit.After;
import org.junit.Before;
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

public class TestCoverageClassTestSC1a {


    @Test
    public void TestCoverageClassTestSC1a() {
        // This method achieves less than 100% statement coverage and reveal the division by zero fault therein
        // The test suite is TC1(1,0)

        TestCoverageClass test  = new TestCoverageClass();
        test.testCoverageMethod1(1,0);
        //test.testCoverageMethod1(0,0);
    }



}
