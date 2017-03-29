package com.nexteducation.sqliteemployeedept.fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.nexteducation.sqliteemployeedept.R;
import com.nexteducation.sqliteemployeedept.adapter.EmployeeListAdapter;
import com.nexteducation.sqliteemployeedept.model.Employee;
import com.nexteducation.sqliteemployeedept.sqlite.SQLiteDatabaseHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by next on 24/3/17.
 */
public class EmployeeListViewFragment extends Fragment
{
    View mView;
    ListView mListView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        mView = inflater.inflate(R.layout.simple_list_view_fragment, container, false);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        mListView = (ListView) mView.findViewById(R.id.list_view);

        List<Employee> employeeList = new ArrayList<>();

        SQLiteDatabaseHelper databaseHelper = new SQLiteDatabaseHelper(getContext());

        Cursor cursor = databaseHelper.retrieveData("select "+SQLiteDatabaseHelper.EMP_ID+", "
                +SQLiteDatabaseHelper.EMP_NAME +", "+SQLiteDatabaseHelper.DATE_OF_JOINING+", "
                +SQLiteDatabaseHelper.EMP_DEPT_ID+", "+SQLiteDatabaseHelper.DEPT_NAME +" from "
                +SQLiteDatabaseHelper.EMPLOYEE_TABLE+" INNER JOIN " +SQLiteDatabaseHelper.DEPARTMENT_TABLE+" on "
                +SQLiteDatabaseHelper.EMPLOYEE_TABLE+"."+SQLiteDatabaseHelper.EMP_DEPT_ID+" = "
                +SQLiteDatabaseHelper.DEPARTMENT_TABLE+"."+SQLiteDatabaseHelper.DEPT_ID);
        while (cursor.moveToNext())
        {
            Employee employee = new Employee();
            employee.setId(cursor.getInt(0));
            employee.setName(cursor.getString(1));
            employee.setDateOfJoining(cursor.getString(2));
            employee.setDeptId(cursor.getInt(3));
            employee.setDeptName(cursor.getString(4));

            employeeList.add(employee);

        }

        EmployeeListAdapter adapter = new EmployeeListAdapter(getContext(), employeeList);
        mListView.setAdapter(adapter);

    }
}