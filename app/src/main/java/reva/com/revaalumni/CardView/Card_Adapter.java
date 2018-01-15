package reva.com.revaalumni.CardView;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import reva.com.revaalumni.R;

/**
 * Created by yashas.hiremath on 8/26/2016.
 */
public class Card_Adapter extends RecyclerView.Adapter<Card_Adapter.EventViewHolder> {
    ArrayList<Event> events=new ArrayList<Event>();

    public Card_Adapter(ArrayList<Event> events){
        this.events=events;

    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vi= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout,parent,false);
        EventViewHolder eventViewHolder=new EventViewHolder(vi);
        return eventViewHolder;
    }

    @Override
    public void onBindViewHolder(EventViewHolder holder, int position) {

        Event event=events.get(position);
        holder.card_title.setText(event.getSubject());
        holder.card_desc.setText(event.getBody());
        holder.card_date_box.setText(event.getDate());


    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public static class EventViewHolder extends RecyclerView.ViewHolder{
        TextView card_date_box,card_desc,card_title;

        public EventViewHolder(View view) {
            super(view);
            card_date_box=(TextView)view.findViewById(R.id.card_date);
            card_title=(TextView)view.findViewById(R.id.card_title);
            card_desc=(TextView)view.findViewById(R.id.card_desc);

        }

    }


}
