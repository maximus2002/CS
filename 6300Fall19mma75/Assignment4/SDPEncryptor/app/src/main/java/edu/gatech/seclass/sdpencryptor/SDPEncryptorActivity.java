package edu.gatech.seclass.sdpencryptor;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SDPEncryptorActivity extends AppCompatActivity {


    public EditText message_Input;
    public EditText key_Number;
    public EditText increment_Number;
    public EditText cipher_Text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        message_Input = (EditText) findViewById(R.id.messageInput);
        key_Number = (EditText) findViewById(R.id.keyNumber);
        increment_Number = (EditText) findViewById(R.id.incrementNumber);
        cipher_Text = (EditText) findViewById(R.id.cipherText);

    }
    public void handleClick (View view){
        //if(R.id.encryptButton == 1){
        String result;
        String mes = message_Input.getText().toString();
        String knum = key_Number.getText().toString();
        String inum = increment_Number.getText().toString();

        if((mes.trim().length() == 0) || (!mes.matches(".*[a-zA-Z].*"))){
            message_Input.setError("Invalid Message");
            cipher_Text.setText("");
        }

        if ((knum.trim().length() == 0)||(Integer.parseInt(knum) > 25 || Integer.parseInt(knum) <1))  {
            key_Number.setError("Invalid Key Number");
            cipher_Text.setText("");
        }

        if ((inum.trim().length() == 0)||(Integer.parseInt(inum) > 25 || Integer.parseInt(inum) < 1)) {
            increment_Number.setError("Invalid Increment Number");
            cipher_Text.setText("");
        }

        if ((mes.matches(".*[a-zA-Z].*")) && (Integer.parseInt(knum) <= 26) && (Integer.parseInt(inum) <= 26)){
            result = SDPEncrypt(mes,knum,inum);
            cipher_Text.setText(result);
        }


        //}
    }



    public String SDPEncrypt (String message, String Knum, String Inum){
        Integer K_value = Integer.parseInt(Knum);
        Integer I_value = Integer.parseInt(Inum);

        int j = 0;
        StringBuilder temp_str = new StringBuilder();
        for(int i = 0; i < message.length(); i++){
            char temp = message.charAt(i);
            char tempL = message.toLowerCase().charAt(i);
            if((int)tempL >= 97 && (int)tempL <= 122){
                int ASC = (int)temp + K_value + j * I_value;
                j++;
                if((int)temp >= 65 && (int)temp <= 90) {
                    if (ASC <= 90) {
                        temp_str.append((char)ASC);
                    }else {
                        int ASC_U = (ASC-90) % 26 + 65 - 1;
                        if (ASC_U < 65){
                            ASC_U = 91 - (65 - ASC_U);
                        }
                        temp_str.append((char)ASC_U);
                    }

                }
                if ((int)temp >= 97 && (int)temp <= 122) {
                    if (ASC <= 122){
                        temp_str.append((char)ASC);
                    }else{
                        int ASC_L = (ASC-122) % 26 + 97 - 1;
                        if (ASC_L < 97){
                            ASC_L = 123 - (97 - ASC_L);
                        }
                        temp_str.append((char)ASC_L);
                    }
                }
            } else{
                temp_str.append(message.charAt(i));
            }
        }
        return temp_str.toString();
    }
}
