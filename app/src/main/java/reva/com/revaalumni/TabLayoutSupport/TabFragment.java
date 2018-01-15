package reva.com.revaalumni.TabLayoutSupport;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



/**
 * Created by yashas.hiremath on 8/24/2016.
 */
public class TabFragment extends Fragment{
    public static String EXTRA = "object";
    private int count;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        count = bundle.getInt(EXTRA);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(TabFragmentAdapter.fragments[count],container,false);
    }
}
