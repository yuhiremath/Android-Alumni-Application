package reva.com.revaalumni;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.regex.*;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

/**
 * Created by yashas.hiremath on 8/31/2016.
 */
public class ConnectFragment extends Fragment implements View.OnClickListener {
    private EditText name,email,phno,branch,yop,password;
    private String p_name,p_email,notValid,RegKey,RegStatus;
    private Button submit;
    private Firebase ref;
    private Context ctx;
    private SharedPreferences register;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.mfragment_connect,container,false);
        return rootview;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        //SharedPref value
        register = getActivity().getPreferences(Context.MODE_PRIVATE);
        RegKey = this.getString(R.string.registered);

        //Retreiving data from layout
        name = (EditText)view.findViewById(R.id.connect_name);
        email = (EditText)view.findViewById(R.id.connect_emailid);
        phno = (EditText)view.findViewById(R.id.connect_phno);
        branch = (EditText)view.findViewById(R.id.connect_branch);
        yop = (EditText)view.findViewById(R.id.connect_passout);
        password = (EditText)view.findViewById(R.id.connect_password);
        submit = (Button)view.findViewById(R.id.connect_subbtn);

        //Generating Pattern
        p_name = "[a-zA-Z .]+";
        p_email = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+[.][a-zA-Z.]{3,10}";

        //Getting Context
        ctx = this.getActivity();

        //Setting the Firebase referrence
        ref = new Firebase("https://revaalumni-3e332.firebaseio.com/users");
        auth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() != null){
                    Toast.makeText(getActivity(),"Signed Up",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getActivity(),MainActivity.class));
                }
            }
        };

        submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //Knowing the user has already registered


       // RegStatus = register.getString(RegKey,this.getString(R.string.defaultregvalue));

        //Checking if the field values provided by user is valid or not
        if(!validateFields())
            //Validated Wrong!!
            Toast.makeText(ctx,"Wrong "+notValid+" Please Enter the right Information",Toast.LENGTH_LONG).show();

        else {
            auth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful())
                        Toast.makeText(getActivity(), "Sign Up failed because of " + task.getException(), Toast.LENGTH_SHORT).show();
                    else {
                        Firebase userRef = ref.push();
                        userRef.child("name").setValue(name.getText().toString());
                        userRef.child("email").setValue(email.getText().toString());
                        userRef.child("phno").setValue(phno.getText().toString());
                        userRef.child("branch").setValue(branch.getText().toString());
                        userRef.child("yearofpassout").setValue(yop.getText().toString());

                        Toast.makeText(ctx,"Thank You for Registering",Toast.LENGTH_SHORT).show();
                    }
                }
            });
            startActivity(new Intent(getActivity(),MainActivity.class));
        }



        /*else if(RegStatus.equals("false")){
            //Validated Wright REGISTER USER DETAILS TO THE DATABASE
            Firebase userRef = ref.push();
            userRef.child("name").setValue(name.getText().toString());
            userRef.child("email").setValue(email.getText().toString());
            userRef.child("phno").setValue(phno.getText().toString());
            userRef.child("branch").setValue(branch.getText().toString());
            userRef.child("yearofpassout").setValue(yop.getText().toString());

            Toast.makeText(ctx,"Thank You for Registering",Toast.LENGTH_SHORT).show();
            register.edit().putString(RegKey,"true").commit();
        }
        else Toast.makeText(ctx,"You have already Registered",Toast.LENGTH_SHORT).show();*/
    }

    private String trim(String s) {
        int i;
        char c;
        StringBuilder stringBuilder = new StringBuilder(s);
        for(i=0;s.charAt(i)!='\0';i++){
            c = s.charAt(i);
            if(c == '@' || c == '.')
                stringBuilder.deleteCharAt(i);
        }
        return s;
    }

    private boolean validateFields() {
        int flag = 1;
        notValid = "";
        //Check for NAME
        if(!Pattern.matches(p_name,name.getText().toString())) {
            notValid = notValid.concat("Name");
            flag = 0;
        }

        //Check for EMAIL
        if(!Pattern.matches(p_email,email.getText().toString())){
            if(flag==1) {
                notValid = notValid.concat("Email ");
                flag = 0;
            }
            else
                notValid = notValid.concat(", Email ");
        }

        //Check for PASSWORD
        if(password.getText().toString().length()<6){
            if(flag==1) {
                notValid = notValid.concat("Password ");
                flag = 0;
            }
            else
                notValid = notValid.concat(", Password ");
        }

        //Check for PHONE NO.
        if(!(phno.getText().toString().length() == 10)){
            if(flag==1) {
                notValid = notValid.concat("Phone NO. ");
                flag = 0;
            }
            else
                notValid = notValid.concat(", Phone NO. ");
        }

        //Check for YEAR OF PASSOUT
        try {
            if (yop.getText() != null && !(Integer.parseInt(yop.getText().toString()) > 2008)) {
                if (flag == 1) {
                    notValid = notValid.concat("Year Of Pass Out ");
                    flag = 0;
                } else
                    notValid = notValid.concat(", Year Of Pass Out ");
            }
        }
        catch (NumberFormatException e){
            notValid = notValid.concat(", Year Of Pass Out ");
        }

        if(flag == 0)
            return false;
        else
            return true;
    }

}
