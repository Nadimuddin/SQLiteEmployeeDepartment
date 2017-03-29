package com.nexteducation.sqliteemployeedept.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.nexteducation.sqliteemployeedept.fragment.DepartmentListViewFragment;
import com.nexteducation.sqliteemployeedept.fragment.ExpandableListViewFragment;
import com.nexteducation.sqliteemployeedept.fragment.EmployeeListViewFragment;

/**
 * Created by next on 24/3/17.
 */
public class SQLitePagerAdapter extends FragmentPagerAdapter
{
    public SQLitePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position)
    {
        if(position == 0)
            return new EmployeeListViewFragment();
        else if(position == 1)
            return new ExpandableListViewFragment();
        else if(position == 2)
            return new DepartmentListViewFragment();
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
