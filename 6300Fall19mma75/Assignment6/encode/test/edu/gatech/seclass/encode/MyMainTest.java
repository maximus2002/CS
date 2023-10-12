package edu.gatech.seclass.encode;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MyMainTest {
    private ByteArrayOutputStream outStream;
    private ByteArrayOutputStream errStream;
    private PrintStream outOrig;
    private PrintStream errOrig;
    private Charset charset = StandardCharsets.UTF_8;

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Before
    public void setUp() throws Exception {
        outStream = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(outStream);
        errStream = new ByteArrayOutputStream();
        PrintStream err = new PrintStream(errStream);
        outOrig = System.out;
        errOrig = System.err;
        System.setOut(out);
        System.setErr(err);
    }

    @After
    public void tearDown() throws Exception {
        System.setOut(outOrig);
        System.setErr(errOrig);
    }

    /*
     *  TEST UTILITIES
     */

    // Create File Utility
    private File createTmpFile() throws Exception {
        File tmpfile = temporaryFolder.newFile();
        tmpfile.deleteOnExit();
        return tmpfile;
    }

    // Write File Utility
    private File createInputFile(String input) throws Exception {
        File file =  createTmpFile();

        OutputStreamWriter fileWriter =
                new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);

        fileWriter.write(input);

        fileWriter.close();
        return file;
    }
    private File createEmptyFile() throws Exception {
        File file =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file);

        fileWriter.close();
        return file;
    }

    //Read File Utility
    private String getFileContent(String filename) {
        String content = null;
        try {
            content = new String(Files.readAllBytes(Paths.get(filename)), charset);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
/*
Place all  of your tests in this class, optionally using encodeTest.java as an example.
*/
    //private static final String FILE1 = "";
    private static final String FILE2 = "";
    //private static final String FILE3 = "";
    private static final String FILE4 = "n/a";
    private static final String FILE5= "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String FILE6= "/n/t@#$%^&";
    private static final String FILE7= "                     ";
    private static final String FILE8 = "Hello,\n" +
            "I took cs6300 this semester.\n" +
            "It is very useful\n";
    private static final String FILE9 = "abcXYZ3456ABCxyz";
    private static final String FILE11 = "abcXYZ\n" +
                                         "XYZabc";
    private static final String FILE13= "                      \n" +
                                        "                      \n" +
                                        "                      \n" +
                                        "                      \n";
    private static final String FILE14= "@#$%^&*\n" +
            "&^%$#\n" +
            "!#$%^\n" +
            "*^$@!\n";

    // Purpose: To test the scenario that a given file that is empty
    // Frame #: Test case number #1 in the catpart.txt.tsl of Part I
    @Test
    public void encodeTest1() throws Exception {
        File inputFile = createEmptyFile();

        String args[] = {inputFile.getPath()};
        Main.main(args);

        assertEquals("The file is empty", errStream.toString().trim());
    }

    // Purpose: To test the scenario that a given file that is empty
    // Frame #: Test case number #2 in the catpart.txt.tsl of Part I
    @Test
    public void encodeTest2() throws Exception {
        File inputFile = createInputFile(FILE2);

        String args[] = {inputFile.getPath()};
        Main.main(args);

        String expected = "";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The output is none", errStream.toString().trim());
    }

    // Purpose: To test the error scenario that a given file "file0.txt" does not exist
    // Frame #: Test case number #3 in the catpart.txt.tsl of Part I
    @Test
    public void encodeTest3() throws Exception {
        String args[] = {"FILE0.txt"};
        Main.main(args);
        assertEquals("File Not exist", errStream.toString().trim());
    }

    // Purpose: To test the scenario that contains n/a
    // Frame #: Test case number #3 in the catpart.txt.tsl of Part I
    @Test
    public void encodeTest4() throws Exception {
        File inputFile = createInputFile(FILE4);

        String args[] = {inputFile.getPath()};
        Main.main(args);

        String expected = "n/a";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    // Purpose: To test the scenario that a given file contains A to Z letters in one row
    // Frame #: Test case number #5 in the catpart.txt.tsl of Part I
    @Test
    public void encodeTest5() throws Exception {
        File inputFile = createInputFile(FILE5);

        String args[] = {inputFile.getPath()};
        Main.main(args);

        String expected = "ZYXWVUTSRQPONMLKJIHGFEDCBA";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    // Purpose: To test the scenario that a given file contains one row of special characters
    // Frame #: Test case number #6 in the catpart.txt.tsl of Part I
    @Test
    public void encodeTest6() throws Exception {
        File inputFile = createInputFile(FILE6);

        String args[] = {inputFile.getPath()};
        Main.main(args);

        String expected = "/n/t@#$%^&";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    // Purpose: To test the scenario that a given file contains one row of spaces
    // Frame #: Test case number #7 in the catpart.txt.tsl of Part I
    @Test
    public void encodeTest7() throws Exception {
        File inputFile = createInputFile(FILE7);

        String args[] = {inputFile.getPath()};
        Main.main(args);

        String expected = "                     ";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    // Purpose: To test the scenario that a given file contains multiple rows and with -c and -k flag
    // Frame #: Test case number #43 in the catpart.txt.tsl of Part I
    @Test
    public void encodeTest8() throws Exception {
        File inputFile = createInputFile(FILE8);

        String args[] = {"-c", "aeiou", "-k", "aeiouxyz", inputFile.getPath()};
        Main.main(args);

        String expected = "EO\n" +
                "i OO 6300 I EE.\n" +
                "i I EE UEU\n";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    // Purpose: To test the scenario that a given file contains multiple rows and with -a and -k arguments
    // Frame #: Test case number #44 in the catpart.txt.tsl of Part I
    @Test
    public void encodeTest9() throws Exception {
        File inputFile = createInputFile(FILE9);

        String args[] = {"-a", "-k", "abc", inputFile.getPath()};
        Main.main(args);

        String expected = "zyx3456ZYX";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    // Purpose: To test the scenario that a given file contains no arguments passed
    // Frame #: Test case number #10 in the catpart.txt.tsl of Part I
    @Test
    public void encodeTest10() {
        String args[]  = new String[0]; //no arguments
        Main.main(args);
        assertEquals("Usage: encode [-a] [-r string | -k string] [-c string] <filename>", errStream.toString().trim());
    }

    // Purpose: To test the scenario that a given file contains multiple rows and with -r argument
    // Frame #: Test case number #36 in the catpart.txt.tsl of Part I
    @Test
    public void encodeTest11() throws Exception {
        File inputFile = createInputFile(FILE11);

        String args[] = {"-r", "aZ", inputFile.getPath()};
        Main.main(args);

        String expected = "bcXY\n" +
                            "XYbc";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    // Purpose: To test the scenario that a given file contains all spaces and with -r argument
    // Frame #: Test case number #36 in the catpart.txt.tsl of Part I
    @Test
    public void encodeTest12() throws Exception {
        File inputFile = createInputFile(FILE7);

        String args[] = {"-r", "bY", inputFile.getPath()};
        Main.main(args);

        String expected = "                      ";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    // Purpose: To test the scenario that a given file contains multiple rows of spaces and with -k -r arguments
    // Frame #: Test case number #35 in the catpart.txt.tsl of Part I
    @Test
    public void encodeTest13() throws Exception {
        File inputFile = createInputFile(FILE13);

        String args[] = {"-r", "-k", "bY", inputFile.getPath()};
        Main.main(args);

        String expected =   "                      \n" +
                            "                      \n" +
                            "                      \n" +
                            "                      \n";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    // Purpose: To test the scenario that a given file contains multiple rows of special characters and with -a -k arguments
    // Frame #: Test case number #34 in the catpart.txt.tsl of Part I
    @Test
    public void encodeTest14() throws Exception {
        File inputFile = createInputFile(FILE14);

        String args[] = {"-a", "-k", "abc", inputFile.getPath()};
        Main.main(args);

        String expected =   "@#$%^&*\n" +
                "&^%$#\n" +
                "!#$%^\n" +
                "*^$@!\n";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    // Purpose: To test the scenario that a given file contains multiple rows and with -a and -r arguments
    // Frame #: Test case number #44 in the catpart.txt.tsl of Part I
    @Test
    public void encodeTest15() throws Exception {
        File inputFile = createInputFile(FILE9);

        String args[] = {"-a", "-r", "abc", inputFile.getPath()};
        Main.main(args);

        String expected = "XYZ3456xyz";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
    }
}
