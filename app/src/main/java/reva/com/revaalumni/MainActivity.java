package reva.com.revaalumni;

import android.graphics.Typeface;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.ui.FirebaseRecyclerAdapter;

import java.util.ArrayList;

import reva.com.revaalumni.CardView.CardViewFragment;
import reva.com.revaalumni.CardView.Card_Adapter;
import reva.com.revaalumni.CardView.Event;
import reva.com.revaalumni.TabLayoutSupport.*;
import reva.com.revaalumni.TabLayoutSupport.TabFragment;

public class MainActivity extends AppCompatActivity {
    //fragment declaration
    private ViewPager viewPager;
    private TabFragmentAdapter tabFragmentAdapter;
    private TabLayout tabLayout;
    private TextView revaTitle;
    private TextView connectTitle;
    public static Boolean registered = false;


    public static int[] fragmentids = {R.layout.mfragment_aboutus,R.layout.blank,R.layout.mfragment_notice,R.layout.mfragment_notification,R.layout.mfragment_signout};
    public static String[] charseq = {"ABOUT","EVENTS","NOTICE","REFER","SIGNOUT"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager)findViewById(R.id.main_viewpager);
        tabFragmentAdapter = new TabFragmentAdapter(getSupportFragmentManager(),charseq,fragmentids);
        viewPager.setAdapter(tabFragmentAdapter);
        tabLayout = (TabLayout)findViewById(R.id.main_tablayout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                int tablayoutwidth = tabLayout.getWidth();
                DisplayMetrics metrics = new DisplayMetrics();
                MainActivity.this.getWindowManager().getDefaultDisplay().getMetrics(metrics);
                int devicewidth = metrics.widthPixels;

                if(tablayoutwidth <= devicewidth) {
                    tabLayout.setTabMode(TabLayout.MODE_FIXED);
                    tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
                }
                else if(tablayoutwidth > devicewidth)
                    tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
            }
        });
    }

    public static void initrecy(RecyclerView recyclerView, RecyclerView.Adapter<RecyclerView.ViewHolder> adapter){
        recyclerView.setAdapter(adapter);
        return;
    }
}
