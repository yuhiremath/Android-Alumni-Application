package reva.com.revaalumni.TabLayoutSupport;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import reva.com.revaalumni.CardView.CardViewFragment;
import reva.com.revaalumni.ConnectFragment;
import reva.com.revaalumni.EventFragment;
import reva.com.revaalumni.NoticeFragment;
import reva.com.revaalumni.ReferFragment;
import reva.com.revaalumni.SignOutFragment;

/**
 * Created by yashas.hiremath on 8/24/2016.
 */
public class TabFragmentAdapter extends FragmentStatePagerAdapter {
    private String[] charseq;
    private int count;
    public static int[] fragments;

    public TabFragmentAdapter(FragmentManager fm, String[] charseq, int[] fragments) {
        super(fm);
        this.charseq = charseq;
        this.count = charseq.length;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        if(position==3)
            return new ReferFragment();
        else if (position==1)
            return new EventFragment();
        else if(position==4)
            return new SignOutFragment();
        else if(position==2)
            return new NoticeFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(TabFragment.EXTRA,position);
        TabFragment tabFragment = new TabFragment();
        tabFragment.setArguments(bundle);
        return tabFragment;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return charseq[position];
    }
}
