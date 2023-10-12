package edu.gatech.seclass;

//public class MyCustomString {
//}

//public static void main (String args[])
       // {system.out.println("Test");}

import java.math.BigInteger;
import java.util.Arrays;

import static java.lang.Character.isDigit;
import static java.lang.Character.isLetter;

public class MyCustomString implements MyCustomStringInterface {
    private String metring;
    @Override
    public String getString() {
        // TODO Auto-generated method stub
        if (metring != null) {
            return metring;
        }
        return null;
    }

    @Override
    public void setString(String string) {
        // TODO Auto-generated method stub
        this.metring = string;
    }

    @Override
    public char mostCommonChar() {
        // TODO Auto-generated method stub
        if (this.metring == null || this.metring == "") {
            throw new NullPointerException();
        }

        int ASCII_SIZE = 256;
        int count[] = new int[ASCII_SIZE];
        String newString = this.metring.toLowerCase();
        for (int i=0; i < newString.length(); i++) {
            int ascii = (int) newString.charAt(i);
            if (ascii >= 97 && ascii <= 122 || ascii >= 48 && ascii <= 57)
            {
                count[newString.charAt(i)]++;
            }
        }
        int max = -1;
        char maxChar = ' ';
        for (int i = 0; i < this.metring.length(); i++) {
            if (max < count[this.metring.charAt(i)]) {
                max = count[this.metring.charAt(i)];
                maxChar = this.metring.charAt(i);
            }
        }
        if (max == 0) {
            maxChar = ' ';
        }
        //System.out.println("Longest run: "+max+", for the character "+maxChar); // Print the maximum.
        return maxChar;
    }

    @Override
    public String filterLetters(char n, boolean more) {
        // TODO Auto-generated method stub
        if (this.metring == null) {
            throw new NullPointerException();
        }
        if (!Character.isLetter(n))  {
            throw new IllegalArgumentException();
        }
        StringBuilder temp_str = new StringBuilder();
        String newString = this.metring.toLowerCase();
        if (more) {
            for (int i = 0; i < this.metring.length(); i++) {
                char temp = this.metring.toLowerCase().charAt(i);
                if ((int) temp >= 97 && (int) temp <= 122) {
                    if (temp < Character.toLowerCase(n)){
                        temp_str.append(this.metring.charAt(i));
                    }
                } else {
                    temp_str.append(this.metring.charAt(i));
                }
            }
        } else {
            for (int i = 0; i < this.metring.length(); i++) {
                char temp = this.metring.toLowerCase().charAt(i);
                if ((int) temp >= 97 && (int) temp <= 122) {
                    if (temp > Character.toLowerCase(n)){
                        temp_str.append(this.metring.charAt(i));
                    }
                } else {
                    temp_str.append(this.metring.charAt(i));
                }
            }
        }

        return temp_str.toString();
    }

    @Override
    public void convertDigitsToProductsInSubstring(int startPosition, int endPosition) {
        //if (this.metring == ""){
        //    throw new NullPointerException();
        //}
        if (startPosition < 1 || startPosition > endPosition) {
            throw new IllegalArgumentException();
        }
        if (this.metring == null || startPosition >= 1 && endPosition >= startPosition && endPosition > this.metring.length())  {
            throw new MyIndexOutOfBoundsException();
        }

        int product = 1;
        String temp_str_bef = this.metring.substring(0, startPosition-1);
        String temp_str_mid = this.metring.substring(startPosition-1, endPosition);
        String temp_str_aft = this.metring.substring(endPosition, this.metring.length());
        String[] parts = temp_str_mid.split("(?<=\\d)(?=\\D)|(?<=\\D)(?=\\d)");
        String[] newArr = new String[parts.length];
        for (int i = 0; i < parts.length; i++) {
            String temp = parts[i];
            if (temp.matches("\\d+")) {
                int n = Integer.parseInt(temp);
                if (n == 0) {
                    product = 0;
                }
                while (n != 0) {
                    product = product * (n % 10);
                    n = n / 10;
                }
                newArr[i] = Integer.toString(product);
                product = 1;
            } else {
                newArr[i] = parts[i];
            }

        }
        String newStr = "";
        for(int i=0;i<newArr.length;i++)
            newStr+=newArr[i];
        this.metring = temp_str_bef + newStr+ temp_str_aft;

    }






}