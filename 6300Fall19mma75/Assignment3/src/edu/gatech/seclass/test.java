package edu.gatech.seclass;

import java.util.Arrays;


class Test
{
    static void splitString(String str)
    {
        //String source = "810LN15";
        int product = 1;
        String temp_str_bef = str.substring(0, 3);
        String temp_str_mid = str.substring(3,14);
        String temp_str_aft = str.substring(14, 17);
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
        System.out.println(temp_str_bef + Arrays.toString(parts) + temp_str_aft);
        System.out.println(temp_str_bef + Arrays.toString(newArr) + temp_str_aft);
        System.out.println( temp_str_bef + newStr+ temp_str_aft );
    }

    // Driver method
    public static void main(String args[])
    {
        String str = "asd12345678910qwe";//"I'd b3tt3r put 50me 123 d161ts in this 5tr1n6, right?";
        splitString(str);
    }
}
