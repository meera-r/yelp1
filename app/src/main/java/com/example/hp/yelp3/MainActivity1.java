 package com.example.hp.yelp3;

 import android.net.Uri;
 import android.os.Bundle;
 import android.support.design.widget.TabLayout;
 import android.support.v4.view.ViewPager;
 import android.support.v7.app.AppCompatActivity;

 public class MainActivity1 extends AppCompatActivity implements Tab1.OnFragmentInteractionListener,Tab2.OnFragmentInteractionListener,Tab3.OnFragmentInteractionListener{

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);
         TabLayout tabLayout=(TabLayout)findViewById(R.id.tablayout);
         tabLayout.addTab(tabLayout.newTab().setText("MAP"));
         tabLayout.addTab(tabLayout.newTab().setText("Emergencuy"));
         tabLayout.addTab(tabLayout.newTab().setText("Profile"));
         tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
         final ViewPager viewPager=(ViewPager)findViewById(R.id.viewpager);
         final PagerAdapter adapter=new PagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
         viewPager.setAdapter(adapter);
         viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
         tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
             @Override
             public void onTabSelected(TabLayout.Tab tab) {
                 viewPager.setCurrentItem(tab.getPosition());
             }

             @Override
             public void onTabUnselected(TabLayout.Tab tab) {

             }

             @Override
             public void onTabReselected(TabLayout.Tab tab) {

             }
         });

     }

     @Override
     public void onFragmentInteraction(Uri uri) {

     }
 }
