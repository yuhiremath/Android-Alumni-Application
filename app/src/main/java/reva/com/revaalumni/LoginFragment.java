package reva.com.revaalumni;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginFragment extends Fragment {
    private EditText email;
    private EditText password;
    private TextView newuser;
    private Button login;
    private FirebaseAuth mauth;
    private FirebaseAuth.AuthStateListener mauthListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_login,container,false);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mauthListener != null) {
            mauth.removeAuthStateListener(mauthListener);
            //mauth.signOut();
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view,savedInstanceState);
        mauth = FirebaseAuth.getInstance();
        email = (EditText) view.findViewById(R.id.username_login);
        password = (EditText) view.findViewById(R.id.password_login);
        login = (Button) view.findViewById(R.id.button_login);
        newuser = (TextView)view.findViewById(R.id.login_newuser);

        mauthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null) {
                    Toast.makeText(getActivity(), "Signed In", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getActivity(), MainActivity.class));
                }
            }
        };

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSignIn();
            }
        });
        newuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login.fragmentManager.beginTransaction().replace(R.id.login_container,new ConnectFragment()).commit();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        mauth.addAuthStateListener(mauthListener);
    }

    public void startSignIn() {
        String email_s = email.getText().toString();
        String password_s = password.getText().toString();
        if (TextUtils.isEmpty(email_s) || TextUtils.isEmpty(password_s)) {
            Toast.makeText(getActivity(), "Field/s are empty!", Toast.LENGTH_SHORT).show();
        } else {
            mauth.signInWithEmailAndPassword(email_s, password_s).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful())
                        Toast.makeText(getActivity(), "Wrong Username or Password", Toast.LENGTH_SHORT).show();
                    else
                        startActivity(new Intent(getActivity(), MainActivity.class));
                }
            });
        }
    }
}


