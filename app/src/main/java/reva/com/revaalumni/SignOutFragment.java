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
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;

import reva.com.revaalumni.Login;
import reva.com.revaalumni.R;


public class SignOutFragment extends Fragment {
    private Firebase firebase;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authListener;
    private Button button1,feedback;
    private Firebase firebase_feed;
    private EditText feedback_text,feedback_name;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.mfragment_signout, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        auth.removeAuthStateListener(authListener);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        feedback_text = (EditText) view.findViewById(R.id.signout_feedback);
        feedback_name = (EditText) view.findViewById(R.id.signout_name);
        button1 = (Button) view.findViewById(R.id.signoutbutton);
        feedback = (Button) view.findViewById(R.id.button_feedback);
        firebase_feed = new Firebase("https://revaalumni-3e332.firebaseio.com/feedback");
        auth = FirebaseAuth.getInstance();
        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() == null)
                    startActivity(new Intent(getActivity(), Login.class));
            }
        };
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
            }
        });
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(feedback_text.getText().toString()) && !TextUtils.isEmpty(feedback_name.getText().toString())) {
                    Feedback feedback = new Feedback();
                    feedback.setFeedback(feedback_text.getText().toString());
                    feedback.setName(feedback_name.getText().toString());
                    firebase_feed.push().setValue(feedback);
                    Toast.makeText(getActivity(),"Feedback Sent",Toast.LENGTH_SHORT).show();
                    feedback_text.getText().clear();
                    feedback_name.getText().clear();
                }
                else
                    Toast.makeText(getActivity(),"Empty Fields",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
