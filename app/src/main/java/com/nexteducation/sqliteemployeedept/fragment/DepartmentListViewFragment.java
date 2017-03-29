package com.nexteducation.sqliteemployeedept.fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.nexteducation.sqliteemployeedept.R;
import com.nexteducation.sqliteemployeedept.adapter.DepartmentListAdapter;
import com.nexteducation.sqliteemployeedept.model.Department;
import com.nexteducation.sqliteemployeedept.sqlite.SQLiteDatabaseHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by next on 25/3/17.
 */
public class DepartmentListViewFragment extends Fragment
{
    private static final String DATABASE_NAME = "EmployeeDatabase";
    private static final String EMPLOYEE_TABLE = "Employee";
    private static final String EMP_ID = "emp_id";
    private static final String EMP_NAME = "emp_name";
    private static final String DATE_OF_JOINING = "date_of_joining";
    private static final String EMP_DEPT_ID = "emp_dept_id";

    private static final String DEPARTMENT_TABLE = "Department";
    private static final String DEPT_ID = "dept_id";
    private static final String DEPT_NAME = "dept_name";


    View mView;
    ListView mListView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.simple_list_view_fragment, container, false);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        mListView = (ListView) mView.findViewById(R.id.list_view);
        SQLiteDatabaseHelper databaseHelper = new SQLiteDatabaseHelper(getContext());
        Cursor cursor = databaseHelper.retrieveData("select "+SQLiteDatabaseHelper.DEPT_ID+", "+DEPT_NAME+", count("+DEPT_NAME+") from "+
                EMPLOYEE_TABLE+" INNER JOIN "
                +DEPARTMENT_TABLE+" on "
                +EMPLOYEE_TABLE+"."+EMP_DEPT_ID+" = "+DEPARTMENT_TABLE+"."+DEPT_ID+" GROUP BY "+DEPT_NAME);
        final List<Department> departmentList = new ArrayList<>();

        while (cursor.moveToNext())
        {
            Department department = new Department();
            department.setId(cursor.getInt(0));
            department.setName(cursor.getString(1));
            department.setHeadCount(cursor.getInt(2));
            departmentList.add(department);
        }

        DepartmentListAdapter adapter = new DepartmentListAdapter(getContext(), departmentList);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                ExpandableViewFragment fragment = new ExpandableViewFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("dept_id" ,departmentList.get(position).getId());
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.linear_layout, fragment).commit();
            }
        });
    }
}
