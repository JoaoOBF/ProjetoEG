package br.com.joaootaviobf.projetoeg;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by joao otavio on 19/04/2017.
 */

public class FixedTabsPageAdapter extends FragmentPagerAdapter {

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
                return "DIO linux";
            case 1:
                return "BR linux";
            case 2:
                return "Open source";
            default:
                return null;
        }


    }
}