package reva.com.revaalumni;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.SimpleDateFormat;
import java.util.Date;

import reva.com.revaalumni.CardView.Event;

/**
 * Created by yashas.hiremath on 8/27/2016.
 */
public class ReferFragment extends Fragment{

    private Event event;
    private Firebase firebase;
    private String ref2;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Button button,button1;
        final EditText subject,body;
        firebase = new Firebase("https://revaalumni-3e332.firebaseio.com/notice");

        button = (Button)view.findViewById(R.id.refer_button);
        subject = (EditText) view.findViewById(R.id.refer_subject);
        body = (EditText) view.findViewById(R.id.refer_content);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    //Setting data in firebase
                    SimpleDateFormat ft = new SimpleDateFormat("MM/yyyy");
                    String date = ft.format(new Date());
                    event = new Event();
                    event.setDate(date);
                    if(!TextUtils.isEmpty(subject.getText().toString()) && !TextUtils.isEmpty(body.getText().toString())) {
                        event.setSubject(subject.getText().toString());
                        event.setBody(body.getText().toString());
                        event.setApproved("Not Approved!");
                        ref2  = firebase.push().getKey();
                        ref2 = firebase+"/"+ref2;
                        event.setKey(ref2);
                        new Firebase(ref2).setValue(event);
                        Toast.makeText(getActivity(),"Referal Sent",Toast.LENGTH_SHORT).show();
                        subject.getText().clear();
                        body.getText().clear();
                    }
                    else
                        Toast.makeText(getActivity(),"Empty Subject or Body",Toast.LENGTH_SHORT).show();


                //Email Sending
                    /*Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setData(Uri.parse("mailto:"));
                    String[] to={"revauniversity.alumni@gmail.com"};
                    intent.putExtra(Intent.EXTRA_EMAIL,to);
                    intent.putExtra(Intent.EXTRA_SUBJECT,subject.getText().toString());
                    intent.putExtra(Intent.EXTRA_TEXT,body.getText().toString());
                    intent.setType("message/rfc822");
                    startActivity(Intent.createChooser(intent,"Send Email"));*/
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.mfragment_notification,container,false);
    }

}

