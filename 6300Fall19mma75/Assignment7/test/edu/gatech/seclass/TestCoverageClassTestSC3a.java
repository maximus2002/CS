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

public class TestCoverageClassTestSC3a {


    @Test
    public void TestCoverageClassTestSC3a() {
        // This method achieves every test suite that achieves 100% statement coverage does not reveal the fault
        // The test suite is TC1(1,1), TC2(1,-1), TC3(-1,1), TC4(-1,-1)

        TestCoverageClass test  = new TestCoverageClass();
        test.testCoverageMethod3(1,1);
        test.testCoverageMethod3(-1,-1);
        test.testCoverageMethod3(1,-1);
        test.testCoverageMethod3(-1,-1);
    }



}
