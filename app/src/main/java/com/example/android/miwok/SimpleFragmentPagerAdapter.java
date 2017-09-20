package com.example.android.miwok;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by nirmal vats on 6/16/2017.
 */

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {
    private Context mcontext;

public SimpleFragmentPagerAdapter(Context context,FragmentManager fm){
    super(fm);
    mcontext=context;
}

    @Override
    public Fragment getItem(int position) {
        if(position==0){
            return new NumbersFragment();
        }
        else if(position==1){
            return new FamilyFragment();
        }
        else if(position==2){
            return  new ColorFragment();
        }
        else
            return new PhraseFragment();
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
      if(position==0){
          return mcontext.getString(R.string.category_numbers);
      }
      else if(position==1){
          return mcontext.getString(R.string.category_family) ;
      }
      else if(position==2){
          return mcontext.getString(R.string.category_colors);
      }
      else return mcontext.getString(R.string.category_phrases);
    }
}
