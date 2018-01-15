package reva.com.revaalumni.CardView;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.firebase.client.Firebase;
import com.firebase.ui.FirebaseRecyclerAdapter;

import java.util.Observer;

import reva.com.revaalumni.R;


/**
 * Created by yashas.hiremath on 8/26/2016.
 */
public class CardViewFragment extends Fragment {
    /*RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Event> list=new ArrayList<Event>();
    String Des,title;
    Date date;*/
    Firebase ref;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Toast.makeText(getActivity(),"onview",Toast.LENGTH_SHORT).show();







        RecyclerView recyclerView=(RecyclerView)view.findViewById(R.id.recyclerview_e);
        ref = new Firebase("https://revaalumni-3e332.firebaseio.com/events1");
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        FirebaseRecyclerAdapter<Event, EventViewHolder> adapter = (new FirebaseRecyclerAdapter<Event, EventViewHolder>(Event.class,
                R.layout.card_layout,
                EventViewHolder.class, ref) {
            @Override
            protected void populateViewHolder(EventViewHolder eventViewHolder, Event event, int i) {
                Toast.makeText(getActivity(), "Insinde Firebase", Toast.LENGTH_SHORT).show();
                eventViewHolder.card_date_box.setText(event.getDate());
                eventViewHolder.card_title.setText(event.getSubject());
                eventViewHolder.card_desc.setText(event.getBody());
            }
        }) ;

        recyclerView.setAdapter(adapter);
        recyclerView.setAdapter(adapter);
        recyclerView.setAdapter(adapter);
        recyclerView.setAdapter(adapter);
        recyclerView.setAdapter(adapter);









//        recyclerView=(RecyclerView)view.findViewById(R.id.recycler_view);
        /*date=getResources().getStringArray(R.array.Date_box);
        Des=getResources().getStringArray(R.array.Description);
        title=getResources().getStringArray(R.array.Title);

        int count=0;
        for (String fc_date:date){
            Event event= new Event(fc_date,Des[count],title[count]);
            count++;
            list.add(event);
        }

        recyclerView=(RecyclerView)view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
*/
    }



    @Override
    public void onStart() {
        super.onStart();
        Toast.makeText(getActivity(),"onstart",Toast.LENGTH_SHORT).show();

        //recyclerView <code>
        /*ref = new Firebase("https://revaalumni-3e332.firebaseio.com/events");
        recyclerView.setHasFixedSize(true);*/
//        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        /*adapter = new FirebaseRecyclerAdapter<Event, EventViewHolder>(Event.class,
                R.layout.card_layout,
                EventViewHolder.class, ref) {
            @Override
            protected void populateViewHolder(EventViewHolder eventViewHolder, Event event, int i) {
                Toast.makeText(getActivity(), "Insinde Firebase", Toast.LENGTH_SHORT).show();
                eventViewHolder.card_date_box.setText(event.getDate());
                eventViewHolder.card_title.setText(event.getSubject());
                eventViewHolder.card_desc.setText(event.getBody());
            }
        };*/
        /*recyclerView.setAdapter(adapter);
        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
            }
        });*/
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {




        View vi=inflater.inflate(R.layout.mfragment_events,container,false);
        Toast.makeText(getActivity(),"oncreatview",Toast.LENGTH_SHORT).show();




//
//
//
//
// recyclerView <code>
//
//        date=getResources().getStringArray(R.array.Date_box);
//        Des=getResources().getStringArray(R.array.Description);
//        title=getResources().getStringArray(R.array.Title);
//
//        int count=0;
//        for (String fc_date:date){
//            Event event= new Event(fc_date,Des[count],title[count]);
//            count++;
//            list.add(event);
//        }
//
//        recyclerView=(RecyclerView)vi.findViewById(R.id.recycler_view);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        recyclerView.setHasFixedSize(true);
//        adapter=new Card_Adapter(list);
//        recyclerView.setAdapter(adapter);
//

        return vi;


    }

    public static class EventViewHolder extends RecyclerView.ViewHolder{
        public TextView card_date_box,card_desc,card_title;

        public EventViewHolder(View view) {
            super(view);
            card_date_box=(TextView)view.findViewById(R.id.card_date);
            card_title=(TextView)view.findViewById(R.id.card_title);
            card_desc=(TextView)view.findViewById(R.id.card_desc);

        }

    }

}
