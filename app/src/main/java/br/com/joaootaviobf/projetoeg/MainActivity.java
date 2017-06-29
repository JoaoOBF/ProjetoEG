package br.com.joaootaviobf.projetoeg;



import android.content.Intent;
import android.support.v4.app.Fragment;
import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.astuetz.PagerSlidingTabStrip;

public class MainActivity extends AppCompatActivity {

//
private FloatingActionButton floatButton;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         intent = new Intent(this,CriadoresFragments.class);
        floatButton = (FloatingActionButton)findViewById(R.id.floatingActionButton2);
        floatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
        ViewPager viewPager = (ViewPager) findViewById(R.id.view);
        PagerAdapter pagerAdapter = new FixedTabsPageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        final PagerSlidingTabStrip tabLayout = (PagerSlidingTabStrip) findViewById(R.id.tab);
        tabLayout.setViewPager(viewPager);
       tabLayout.setTabPaddingLeftRight(20);



        //tabLayout.set



        tabLayout.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                tabLayout.setAllCaps(false);
                tabLayout.setTabPaddingLeftRight(20);
                tabLayout.setShouldExpand(true);

                switch (position){
                    case 0:
                        tabLayout.setBackgroundColor(Color.parseColor("#00838F"));
                        tabLayout.setTextColor(Color.WHITE);
                        tabLayout.setIndicatorColor(Color.WHITE);
                        tabLayout.setDividerColor(Color.WHITE);


                        break;
                    case 1:
                        tabLayout.setBackgroundColor(Color.WHITE);
                        tabLayout.setIndicatorColor(Color.BLACK);
                        tabLayout.setTextColor(Color.BLACK);

                        break;
                    case 2:
                        tabLayout.setBackgroundColor(Color.parseColor("#26A69A"));
                        tabLayout.setIndicatorColor(Color.WHITE);
                        tabLayout.setTextColor(Color.WHITE);


                        break;
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

       // tabLayout.getTabAt(0).setIcon(R.mipmap.ic_launcher);

    }


}
