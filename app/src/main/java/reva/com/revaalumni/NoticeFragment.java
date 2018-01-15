package reva.com.revaalumni;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.ui.FirebaseRecyclerAdapter;

import reva.com.revaalumni.CardView.CardViewFragment;
import reva.com.revaalumni.CardView.Event;

/**
 * Created by yashas.hiremath on 9/7/2016.
 */
public class NoticeFragment extends Fragment {
    Firebase ref;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mfragment_notice, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.notice_recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        ref = new Firebase("https://revaalumni-3e332.firebaseio.com/approved_notice");

        FirebaseRecyclerAdapter<Event, EventViewHolder> adapter;
        adapter = new FirebaseRecyclerAdapter<Event, EventViewHolder>(Event.class, R.layout.card_layout, EventViewHolder.class, ref) {
            @Override
            protected void populateViewHolder(EventViewHolder eventViewHolder, Event event, int i) {
                eventViewHolder.card_date_box.setText(event.getDate());
                eventViewHolder.card_desc.setText(event.getBody());
                eventViewHolder.card_title.setText(event.getSubject());
            }
        };
        recyclerView.setAdapter(adapter);
        return view;
    }

    public static class EventViewHolder extends RecyclerView.ViewHolder {
        public TextView card_date_box, card_desc, card_title;

        public EventViewHolder(View view) {
            super(view);
            card_date_box = (TextView) view.findViewById(R.id.card_date);
            card_title = (TextView) view.findViewById(R.id.card_title);
            card_desc = (TextView) view.findViewById(R.id.card_desc);

        }

    }
}

