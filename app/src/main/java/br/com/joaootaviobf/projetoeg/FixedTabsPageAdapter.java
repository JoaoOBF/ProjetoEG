package br.com.joaootaviobf.projetoeg;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.astuetz.PagerSlidingTabStrip;

/**
 * Created by joao otavio on 19/04/2017.
 */

public class FixedTabsPageAdapter extends FragmentPagerAdapter   {
   // private int tabIcons[] = {R.mipmap.ic_launcher,R.mipmap.ic_launcher, R.mipmap.ic_launcher};

    public FixedTabsPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FragmentDiolinux();
            case 1:
                return new FragmentBrLinux();
            case 2:
                return new FragmentOpenSource();

            default:
                return null;
        }
    }


    @Override
    public int getCount() {
        return 3;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "DIO Linux";
            case 1:
                return "BR Linux";
            case 2:
                return "Open Source";

            default:
                return null;
        }


    }


}