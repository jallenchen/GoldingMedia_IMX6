package com.goldingmedia.mvp.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class FragAdapter extends FragmentPagerAdapter {

	private List<Fragment> fragments;
    
	  
    public FragAdapter(FragmentManager fm) {
        super(fm);  
    }  
      
    public FragAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);  
        this.fragments = fragments;  
    }  
  
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);  
    }  
  
    @Override
    public int getCount() {  
        return fragments.size();  
    }  
    
}  