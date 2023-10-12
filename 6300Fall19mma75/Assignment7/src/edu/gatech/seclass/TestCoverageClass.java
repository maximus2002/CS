package edu.gatech.seclass;

public class TestCoverageClass {
    //@Override
    //Method
    public void testCoverageMethod1(int x, int y) {
        int result;
        if (y>=0) {
            result = x / y;
        }
        else {
            result = x+y;
        };
        //System.out.println(x);
        //System.out.println(y);
        //return result;
    }

    public void testCoverageMethod2(int x, int y) {
        int result;
        if (x>=0) {
            result = x / y;
        }
        if (y>=0) {
            result = x+y;
        };

    }
    public void testCoverageMethod3(int x, int y) {
        int result;
        if (x != 0) {
            result = x / y;
        }
    }
    public void testCoverageMethod4() {
            // Creating such method is not possible because branch coverage is stronger than statement coverage.
            // If every test suite that achieves 100% statement coverage reveals the fault,
            // test suite that achieves 100% branch coverage will also reveal the fault for sure.
    }

    public boolean testCoverageMethod5 (boolean a, boolean b) {
        int x = 2;
        int y = 4;
        if(a)
            x += 2;
        else
            y = y/x;
        if(b)
            y -= 4;
        else
            y -= 2;
        return ((x/y)>= 0);
    }


    // ================
    //
    // Fill in column “output” with T, F, or E:
    //
    // | a | b |output|
    // ================
    // | T | T |  E   |
    // | T | F |  T   |
    // | F | T |  F   |
    // | F | F |  E   |
    // ================
    //
    // Fill in the blanks in the following sentences with
    // “NEVER”, “SOMETIMES” or “ALWAYS”:
    //
    // Test suites with 100% statement coverage _sometimes_ reveal the fault in this method.
    // Test suites with 100% branch coverage _sometimes_ reveal the fault in this method.
    // Test suites with 100% path coverage _sometimes_ reveal the fault in this method.
    // ================

}
