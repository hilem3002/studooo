package com.example.studooo_v1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class verifyepostaActivity extends AppCompatActivity {

    EditText verifyepostaUsername;
    Button sendcodetouserButton;
    ArrayList<ArrayList<String>> userdata;
    String verifyInfo;
    String receiverEposta;
    String receiverUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verifyeposta_design);

        // object assignment
        this.verifyepostaUsername = findViewById(R.id.verifyepostaUsername);
        this.sendcodetouserButton = findViewById(R.id.sendcodetouserButton);


        sendcodetouserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    verifyInfo = verifyepostaUsername.getText().toString();
                    dataOperator operator = new dataOperator();

                    // looking for is there any user with this infos
                    if (operator.selectUser("username='"+verifyInfo+"'").isEmpty()) {
                        Toast.makeText(verifyepostaActivity.this, "girdiğiniz bilgiye sahip bir hesap bulunamadı lütfen tekrar deneyiniz", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        // getting the user info
                        userdata = operator.selectUser("username='"+verifyInfo+"'");
                    }

                }
                catch (Exception e) {

                    // error page executed
                    Intent intent = new Intent(verifyepostaActivity.this, errormassageLoginActivity.class);
                    startActivity(intent);

                }

                // receiver eposta assignment from the data list
                receiverEposta = userdata.get(0).get(3);
                receiverUsername = userdata.get(0).get(2);

                Intent intent = new Intent(verifyepostaActivity.this, verifyCodeToChangePass.class);

                //sending the receiver eposta to other page
                intent.putExtra("receiverEposta", receiverEposta);
                intent.putExtra("receiverUsername", receiverUsername);
                startActivity(intent);


            }
        });
    }
}
