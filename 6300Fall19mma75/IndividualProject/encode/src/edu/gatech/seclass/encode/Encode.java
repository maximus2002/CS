package edu.gatech.seclass.encode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.*;
import java.nio.file.Files;

public class Encode {

    private File file;
    public void setFile(File file) {
        this.file = file;
    }

    public String encryptAtbash(String text) {
        String content = null;
        try {
            content = readFile(file);
        } catch (IOException e) {
            System.out.println("Warning: File not found.\n"
                    + "Please make sure that you entered the correct file name or directory.\n"
                    + "java -cp <classpath> edu.gatech.seclass.encode.Main 'IndividualProject/encode/src/<filename>'");
            throw new RuntimeException();
        }
        String cText = "";
        //set charNum offset by ascii 48 if no value assigned after -a
        Integer charNum;
        if (text != ""){
            charNum = Integer.parseInt(text);
        } else {
            charNum = 0;
        }

        for(char c : content.toCharArray()) {
            if(Character.isLetter(c) && (c>= 'a' && c<= 'z')) {
                //set charNum offset by 0 ascii = 48
                Character key = (char) (('a' + ('z' - c) - charNum + 48 -48));
                if (key>= 'a' && key <= 'z') {
                    cText += key;
                } else {
                    int lNum = ((int)key - 122) % 26 + 97 -1;
                    if (lNum < 97) {
                        lNum = 123 - (97 - lNum);
                    }
                    cText += (char)lNum;
                }

            }
            else if(Character.isLetter(c) && (c>= 'A' && c<= 'Z')) {
                Character key = (char) (('A' + ('Z' - c) - charNum + 48 - 48));
                if (key>= 'A' && key <= 'Z') {
                    cText += key;
                } else {
                    int uNum = ((int)key - 90) % 26 + 65 -1;
                    if (uNum < 65) {
                        uNum = 91 - (65 - uNum);
                    }
                    cText += (char)uNum;
                }
            }
            else {
                cText += c;
            }
        }
//        if(cText.endsWith("\n")){
//            cText = cText.substring(0, cText.length()-1);
//        }
        return cText;
    }


    public String reverseCapitalization() {
        String content = null;
        try {
            content = readFile(file);
        } catch (IOException e) {
            System.out.println("Warning: File not found.\n"
                    + "Please make sure that you entered the correct file name or directory.\n"
                    + "java -cp <classpath> edu.gatech.seclass.encode.Main 'IndividualProject/encode/src/<filename>'");
            throw new RuntimeException();
        }
        String cText = "";
        for(char c : content.toCharArray()) {
            if(Character.isLetter(c) && (c>= 'a' && c<= 'z')) {
                cText += Character.toUpperCase(c);
            }
            else if(Character.isLetter(c) && (c>= 'A' && c<= 'Z')) {
                cText += Character.toLowerCase(c);
            }
            else {
                cText += c;
            }
        }
//        if(cText.endsWith("\n")){
//            cText = cText.substring(0, cText.length()-1);
//        }
        return cText;
    }

    public String changeCapitalization(String text) {
        String content = null;
        try {
            content = readFile(file);
        } catch (IOException e) {
            System.out.println("Warning: File not found.\n"
                    + "Please make sure that you entered the correct file name or directory.\n"
                    + "java -cp <classpath> edu.gatech.seclass.encode.Main 'IndividualProject/encode/src/<filename>'");
            throw new RuntimeException();
        }
        String cText = "";
        String textU = text.toUpperCase();
        String textL = text.toLowerCase();
        for(char c : content.toCharArray()) {
            if(Character.isLetter(c) && (c>= 'a' && c<= 'z') && (textL.indexOf(c) != -1)) {
                cText += Character.toUpperCase(c);
            }
            else if(Character.isLetter(c) && (c>= 'A' && c<= 'Z') && (textU.indexOf(c) != -1)) {
                cText += Character.toLowerCase(c);
            }
            else {
                cText += c;
            }
        }
//        if(cText.endsWith("\n")){
//            cText = cText.substring(0, cText.length()-1);
//        }
        return cText;
    }

    public String removeChar(String text) {
        String content = null;
        try {
            content = readFile(file);
        } catch (IOException e) {
            System.out.println("Warning: File not found.\n"
                    + "Please make sure that you entered the correct file name or directory.\n"
                    + "java -cp <classpath> edu.gatech.seclass.encode.Main 'IndividualProject/encode/src/<filename>'");
            throw new RuntimeException();
        }
        String cText = "";
        text = text.toUpperCase();
        for(char c : content.toCharArray()) {
            if((Character.isLetter(c) == false && Character.isDigit(c) == false) || text.indexOf(Character.toUpperCase(c)) == -1) {
                cText += c;
            }

        }
//        if(cText.endsWith("\n")){
//            cText = cText.substring(0, cText.length()-1);
//        }
        return cText;
    }

    public String keepChar(String text) {
        String content = null;
        try {
            content = readFile(file);
        } catch (IOException e) {
            System.out.println("Warning: File not found.\n"
                    + "Please make sure that you entered the correct file name or directory.\n"
                    + "java -cp <classpath> edu.gatech.seclass.encode.Main 'IndividualProject/encode/src/<filename>'");
            throw new RuntimeException();
        }
        String cText = "";

        text = text.toUpperCase();
        for(char c : content.toCharArray()) {
            if((Character.isLetter(c) != true && Character.isDigit(c) != true) || text.indexOf(Character.toUpperCase(c)) != -1) {
                cText += c;
            }

        }
//        if(cText.endsWith("\n")){
//            cText = cText.substring(0, cText.length()-1);
//        }
        return cText;
    }

    public void outputLine (String text) {
        String content = null;
        try {
            content = readFile(file);
        } catch (IOException e) {
            System.out.println("Warning: File not found.\n"
                    + "Please make sure that you entered the correct file name or directory.\n"
                    + "java -cp <classpath> edu.gatech.seclass.encode.Main 'IndividualProject/encode/src/<filename>'");
            throw new RuntimeException();
        }
        String cText = "";
        String sContent[] = content.split("\n");
//        ArrayList<Integer> list = new ArrayList<Integer>();

        for(int i = 0; i < sContent.length; i++){
            ArrayList<Integer> list = new ArrayList<Integer>();
            for(char c : text.toCharArray()) {
                list.add(sContent[i].indexOf(c));
                }
            if (list.contains(-1)==false) {
                System.out.println(sContent[i]);
            }
        }

//        for (String a : sContent)
//            System.out.println(a);
//        text = text.toUpperCase();
//        for(char c : content.toCharArray()) {
//            if((Character.isLetter(c) != true && Character.isDigit(c) != true) || text.indexOf(Character.toUpperCase(c)) != -1) {
//                cText += c;
//            }
//
//        }
//        if(cText.endsWith("\n")){
//            cText = cText.substring(0, cText.length()-1);
//        }
//        return cText;
    }

    private String readFile(File file) throws IOException {
        String content = "";
        int count = 0;
        byte[] bContent = Files.readAllBytes(file.toPath());
//        System.out.println(bContent);
        for (int i = 0; i < bContent.length; i++) {
            if (bContent[i] != 13) {
                continue;
            } else{
                count++;
            }
        }

        BufferedReader reader = new BufferedReader(new FileReader(file));

        try {
            String line = reader.readLine();
            while (line != null) {
                if (count >0) {
                    content += line + "\r";
                }else{
                    content += line + "\n";
                }
                line = reader.readLine();
            }
        } finally {
            reader.close();
        }
        if(content.endsWith("\n")||content.endsWith("\r")){
            content = content.substring(0, content.length()-1);
        }
        return content;
//        String content = "";
//        try {
//            Scanner sc = new Scanner(new File(String.valueOf(file)), StandardCharsets.UTF_8);
//            while (sc.hasNextLine()) {
//                String str = sc.nextLine();
//                content += str + "\n";
////                System.out.println(str);
//            }
//            sc.close();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
////            e.printStackTrace();
//
//        }
//        if(content.endsWith("\n")){
//            content = content.substring(0, content.length()-1);
//        }
//        return content;
    }

    public void writeFile(File file, String text) throws IOException {
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(text);
        bw.close();
    }
}
