package com.kev.wanderi.kinda01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class page1 extends AppCompatActivity {

    private EditText txt1;
    private Button btn1;
    private ProgressBar Bar;

    public PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private String verification;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page1);


        txt1=findViewById(R.id.editText);
        btn1=findViewById(R.id.button);
        Bar=findViewById(R.id.progressBar);
        Bar.setVisibility(View.GONE);
        mAuth=FirebaseAuth.getInstance();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bar.setVisibility(View.VISIBLE);
                String nw="+254"+txt1.getText().toString();

                if(nw.isEmpty() || nw.length()<14){
                    txt1.setError("invalid phoneNumber");
                    txt1.requestFocus();

                }else{

                   PhoneAuthProvider.getInstance().verifyPhoneNumber(nw,60,TimeUnit.SECONDS,page1.this,mCallbacks);


                }

            }
        });

        mCallbacks=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {

                Toast.makeText(page1.this,"verification failed!",Toast.LENGTH_SHORT).show();
                Bar.setVisibility(View.GONE);
            }

             @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                Toast.makeText(page1.this,"verification was successful",Toast.LENGTH_SHORT).show();
                verification=s;
                Intent next=new Intent(page1.this,page2.class);
                Bar.setVisibility(View.GONE);
                startActivity(next);
            }

        };
    }

}
