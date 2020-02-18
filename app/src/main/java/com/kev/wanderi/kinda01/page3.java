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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class page3 extends AppCompatActivity {

    private EditText username,email,pass;
    private Button btn;
    private ProgressBar Bar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page3);

        username=findViewById(R.id.editText2);
        email=findViewById(R.id.editText4);
        pass=findViewById(R.id.editText5);
        btn=findViewById(R.id.button3);
        Bar=findViewById(R.id.progressBar3);
        Bar.setVisibility(View.GONE);
        mAuth=FirebaseAuth.getInstance();


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Username=username.getText().toString();
                String Email=email.getText().toString();
                String Pass=pass.getText().toString();

                if(Username.isEmpty()){
                    username.setError("Username is empty!");
                    username.requestFocus();
                }else if(Email.length()<3){
                    username.setError("invalid username");
                    username.requestFocus();
                }else if(Email.isEmpty()){
                    email.setError("Email is empty!");
                    email.requestFocus();
                }else if(Email.length()<5){
                    email.setError("invalid E-mail");
                    email.requestFocus();
                }else if(Pass.isEmpty()){
                    pass.setError("password is empty!");
                    pass.requestFocus();
                }else if(Pass.length()<6){
                    pass.setError("atleast six digits");
                    pass.requestFocus();
                }else if(!Username.isEmpty() && !Email.isEmpty() && !Pass.isEmpty()){

                    Bar.setVisibility(View.VISIBLE);
                    mAuth.createUserWithEmailAndPassword(Email,Pass).addOnCompleteListener(page3.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                Toast.makeText(page3.this,"User Regestered successfully",Toast.LENGTH_SHORT).show();

                    Intent nw=new Intent(page3.this,page4.class);
                    Bar.setVisibility(View.GONE);
                                startActivity(nw);
                                finish();

                            }else{
                                Toast.makeText(page3.this,"User not Regestered!",Toast.LENGTH_SHORT).show();
                                Bar.setVisibility(View.GONE);
                            }
                        }
                    });


                }


            }
        });
    }
}
