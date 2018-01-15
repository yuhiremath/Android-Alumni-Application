package reva.com.revaalumni;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.text.TextUtilsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends FragmentActivity {
    /*private EditText email;
    private EditText password;
    private Button login;
    private FirebaseAuth mauth;
    private FirebaseAuth.AuthStateListener mauthListener;*/
    public static FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_root);

        LoginFragment loginFragment = new LoginFragment();

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.login_container,loginFragment).commit();

        /*mauth = FirebaseAuth.getInstance();
        email = (EditText)findViewById(R.id.username_login);
        password = (EditText)findViewById(R.id.password_login);
        login = (Button)findViewById(R.id.button_login);

        mauthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() != null)
                    startActivity(new Intent(Login.this,MainActivity.class));
            }
        };

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSignIn();
            }
        });*/
    }

    /*@Override
    protected void onStart() {
        super.onStart();

        mauth.addAuthStateListener(mauthListener);
    }

    public void startSignIn(){
        String email_s = email.getText().toString();
        String password_s = password.getText().toString();
        if(TextUtils.isEmpty(email_s)||TextUtils.isEmpty(password_s)){
            Toast.makeText(Login.this,"Field/s are empty!",Toast.LENGTH_SHORT).show();
        }
        else {
            mauth.signInWithEmailAndPassword(email_s, password_s).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(!task.isSuccessful())
                        Toast.makeText(Login.this,"Wrong Username or Password",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }*/
}
