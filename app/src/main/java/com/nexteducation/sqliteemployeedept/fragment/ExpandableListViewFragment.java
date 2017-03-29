package com.nexteducation.sqliteemployeedept.fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.nexteducation.sqliteemployeedept.R;
import com.nexteducation.sqliteemployeedept.adapter.DepartmentExpandableListAdapter;
import com.nexteducation.sqliteemployeedept.model.Department;
import com.nexteducation.sqliteemployeedept.sqlite.SQLiteDatabaseHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by next on 24/3/17.
 */
public class ExpandableListViewFragment extends Fragment
{
    private static final String DEPARTMENT_TABLE = "Department";

    View mView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        mView = inflater.inflate(R.layout.expandable_view, container, false);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        ExpandableListView expandableListView = (ExpandableListView) mView.findViewById(R.id.expandable_list_view);

        SQLiteDatabaseHelper databaseHelper = new SQLiteDatabaseHelper(getContext());
        Cursor cursor = databaseHelper.retrieveData("select * from "+DEPARTMENT_TABLE);
        List<Department> departmentList = new ArrayList<>();

        while (cursor.moveToNext())
        {
            Department department = new Department();
            department.setId(cursor.getInt(0));
            department.setName(cursor.getString(1));
            departmentList.add(department);
        }
        DepartmentExpandableListAdapter adapter = new DepartmentExpandableListAdapter(getContext(), departmentList);
        expandableListView.setAdapter(adapter);
    }
}
