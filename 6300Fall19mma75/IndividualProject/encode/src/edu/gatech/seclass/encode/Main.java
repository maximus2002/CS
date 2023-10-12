package edu.gatech.seclass.encode;

import java.io.File;
import java.io.IOException;

public class Main {
//    private File file;
//    public void setFile(File file) {
//        this.file = file;
//    }
/**
 * This is a Georgia Tech provided code template for use in assigned private GT repositories. Students and users of this template
 * code are advised not to share it with other students or to make it available on publicly viewable websites including
 * repositories such as github and gitlab.  Such sharing may be investigated as a GT honor code violation. Created for CS6300.
 *
 * Empty Main class for starting the Individual Project.
 */

    public static void main(String[] args) {
        // Empty Skeleton Method
//        if (args.length == 0) {
//            return;
//        }
//        String filename = "C:\\Users\\minju\\Documents\\User4\\6300Fall19mma75\\IndividualProject\\encode\\src\\file1.txt";
        try{
            if(args.length == 0){
                usage();
                System.out.println("Usage: encode [-a] [-r string | -k string] [-c string] <filename>");
                return;
            }
        } catch (Exception e) {

            return;
        }
        String filename = "";
        String flagSign = "";
        String flagArgA = "";
        String flagArgC  = "";
        String flagArgL = "";
        String flagArgK  = "";
        String flagArgR  = "";
//        if (args[0].length() >2){
//            usage();
//            System.out.println("Usage: encode [-a] [-r string | -k string] [-c string] <filename>");
//            return;
//        }
        int i = 0;
        while (i < args.length) {
            String token = args[i];
            if (token.startsWith("-")) {
                String nextToken = i + 1 < args.length ? args[i+1] : null;
                if (token.length() >2){
                    usage();
                    System.out.println("Usage: encode [-a] [-r string | -k string] [-c string] <filename>");
                    return;
                }
                else if (token.equals("-a")) {
                    flagSign += token;
                    if (nextToken != null && nextToken.startsWith("-")) {
                        flagSign += nextToken;
                        if (nextToken.equals("-l")) {
                            if (!args[i+2].endsWith(".tmp")){
                                flagArgL = args[i+2];
                            }
                        }
                        else if (nextToken.equals("-c")) {
                            if (!args[i+2].endsWith(".tmp")){
                                flagArgC = args[i+2];
                            }
                        }
                        else if (nextToken.equals("-k")) {
                            if (!args[i+2].endsWith(".tmp")){
                                flagArgK = args[i+2];
                            }
                        }
                        else if (nextToken.equals("-r")) {
                            if (!args[i+2].endsWith(".tmp")){
                                flagArgR = args[i+2];
                            }
                        }
                        i++;
                    }
                    else if (!nextToken.endsWith(".tmp")){
                        flagArgA = nextToken;
                        i++;
                    }
                }
                else if (token.equals("-l")) {
                    flagSign += token;
                    if (!nextToken.endsWith(".tmp")){
                        flagArgL = nextToken;
                        i++;
                    }

                }
                else if (token.equals("-c")) {
                    flagSign += token;
                    if (!nextToken.endsWith(".tmp")){
                        flagArgC = nextToken;
                        i++;
                    }

                }
                else if (token.equals("-k")) {
                    flagSign += token;
                    if (!nextToken.endsWith(".tmp")){
                        flagArgK = nextToken;
                        i++;
                    }

                }
                else if (token.equals("-r")) {
                    flagSign += token;
                    if (!nextToken.endsWith(".tmp")){
                        flagArgR = nextToken;
                        i++;
                    }

                }else {
                    System.err.println("Usage: encode [-a [integer]] [-r string | -k string] [-c string] [-l string] <filename>");
                    return;
                }
            } else {
                filename = token;
            }
            i++;
        }

        Encode fl = new Encode();

        try{
            fl.setFile(new File(filename));
            String sChars = "-!\\/@#$%^&_+=()~`^*" ;
            String result = "";
            if (flagSign.indexOf('l') != -1) {
                fl.outputLine(flagArgL);
                //fl.writeFile(new File(filename),result);
            }
            if (flagSign.indexOf('c') != -1) {
                if ((!flagArgC.equals("")) && (flagArgC != null) && (flagArgC.matches("^[a-zA-Z]*$"))){
                    result = fl.changeCapitalization(flagArgC);
                    fl.writeFile(new File(filename),result);
                } else{
                    System.err.println("Usage: encode [-a [integer]] [-r string | -k string] [-c string] [-l string] <filename>");
                }
            }
            if (flagSign.indexOf('r') != -1 && flagSign.indexOf('k') != -1) {
                for(char c : flagArgR.toCharArray()) {
                    if (Character.isLetter(c) || Character.isDigit(c)){
                        if (flagArgK.indexOf(c)!=-1){
                            System.err.println("Usage: encode [-a [integer]] [-r string | -k string] [-c string] [-l string] <filename>");
                            return;
                        }
                    }
                }
                result = fl.removeChar(flagArgR);
                fl.writeFile(new File(filename),result);
            }
            if (flagSign.indexOf('r') != -1) {
                boolean foundR = flagArgR.matches("[" + sChars + "]+");
                if (foundR){
                    System.err.println("Usage: encode [-a [integer]] [-r string | -k string] [-c string] [-l string] <filename>");
                } else {
                    result = fl.removeChar(flagArgR);
                    fl.writeFile(new File(filename),result);
                }

            }
            if (flagSign.indexOf('k') != -1) {
                boolean foundK = flagArgK.matches("[" + sChars + "]+");
                if (foundK){
                    System.err.println("Usage: encode [-a [integer]] [-r string | -k string] [-c string] [-l string] <filename>");
                } else {
                    result = fl.keepChar(flagArgK);
                    fl.writeFile(new File(filename),result);
                }
            }
            if (flagSign.indexOf('a') != -1) {
                result = fl.encryptAtbash(flagArgA);
                fl.writeFile(new File(filename),result);
            }

            if (flagSign == "") {
                result = fl.reverseCapitalization();
                fl.writeFile(new File(filename),result);
            }


//            System.out.println(result);
//            fl.writeFile(new File(filename),result);
        } catch (RuntimeException | IOException e) {
            System.err.println("File Not Found");
//            return;
        }

    }




    private static void usage() {
        System.err.println("Usage: encode [-a [integer]] [-r string | -k string] [-c string] [-l string] <filename>");
    }

}