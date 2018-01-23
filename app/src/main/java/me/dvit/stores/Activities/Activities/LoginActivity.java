package me.dvit.stores.Activities.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import me.dvit.stores.R;

public class LoginActivity extends AppCompatActivity {
    EditText password , email;
    TextView textView;
    ImageView imageView;
    FirebaseAuth auth;
    ProgressBar progressBar;
String password1 , email1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(View view) {
        textView = (TextView) findViewById(R.id.textview);
        password= (EditText)findViewById(R.id.password);
        email = (EditText)findViewById(R.id.user_mail);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
            auth = FirebaseAuth.getInstance();
            password1=password.getText().toString().trim();
            email1=email.getText().toString().trim();
            if (TextUtils.isEmpty(email1)) {
                Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(password1)) {
                Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                return;
            }

            progressBar.setVisibility(View.VISIBLE);

            //authenticate user
            auth.signInWithEmailAndPassword(email1, password1)
                    .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            progressBar.setVisibility(View.GONE);
                            if (!task.isSuccessful()) {
                                // there was an error
                                if (password.length() < 6) {
                                    password.setError(getString(R.string.minimum_password));
                                } else {
                                    Toast.makeText(LoginActivity.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                    });
        }



    public void signup(View view) {
        Intent intent =new Intent(LoginActivity.this , SignupActivity.class);
        startActivity(intent);
    }

    public void showpassword(View view) {
        imageView = (ImageView) findViewById(R.id.showpass);
        password= (EditText) findViewById(R.id.password);
        password.setInputType(InputType.TYPE_CLASS_TEXT);
    }

    public void forgetpassword(View view) {
        Intent intent = new Intent(LoginActivity.this, ForgetPasswordActivity.class);
        startActivity(intent);
    }
}
