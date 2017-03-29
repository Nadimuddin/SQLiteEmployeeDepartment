package com.nexteducation.sqliteemployeedept;

import android.database.Cursor;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.nexteducation.sqliteemployeedept.adapter.SQLitePagerAdapter;
import com.nexteducation.sqliteemployeedept.model.Department;
import com.nexteducation.sqliteemployeedept.model.Employee;
import com.nexteducation.sqliteemployeedept.sqlite.SQLiteDatabaseHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String EMPLOYEE_TABLE = "Employee";
    private static final String DEPARTMENT_TABLE = "Department";
    private static final String DEPT_ID = "dept_id";

    public static final String TAG = "MainActivity";
    private SQLiteDatabaseHelper mDatabaseHelper;
    private List<Employee> mEmployeeList;
    private List<Department> mDepartmentList;
    private HashMap<Integer, List<Employee>> mEmployeeMap;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabaseHelper = new SQLiteDatabaseHelper(this);
        mDepartmentList = new ArrayList<>();

        mViewPager = (ViewPager) findViewById(R.id.view_pager);

        SQLitePagerAdapter adapter = new SQLitePagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapter);
        //mTabLayout.setupWithViewPager(mViewPager);

        insertData();
        /*retrieveData();*/

        /*DepartmentExpandableListAdapter adapter = new DepartmentExpandableListAdapter(this, mDepartmentList, mEmployeeMap);
        mExpandableListView.setAdapter(adapter);*/
    }

    private void insertData()
    {
        if(mDatabaseHelper.getNumberOfRows(EMPLOYEE_TABLE) <= 0 && mDatabaseHelper.getNumberOfRows(DEPARTMENT_TABLE) <= 0)
            mDatabaseHelper.insertData();
    }

    private void retrieveData()
    {
        Cursor cursor = mDatabaseHelper.retrieveData("select * from "+DEPARTMENT_TABLE);
        if(cursor != null)
        {
            while (cursor.moveToNext())
            {
                Department department = new Department();
                department.setId(cursor.getInt(0));
                department.setName(cursor.getString(1));
                mDepartmentList.add(department);
                Log.i(TAG, "onCreate: "+cursor.getInt(0)+" "+cursor.getString(1));
            }
        }


        mEmployeeMap = new HashMap<>();
        for(Department department : mDepartmentList)
        {
            int deptId = department.getId();
            Cursor empCursor = mDatabaseHelper.retrieveData("select * from "+EMPLOYEE_TABLE+
                    " where "+DEPT_ID+"="+deptId);

            mEmployeeList = new ArrayList<>();
            while (empCursor.moveToNext())
            {
                Employee employee = new Employee();
                employee.setId(empCursor.getInt(0));
                employee.setName(empCursor.getString(1));
                employee.setDateOfJoining(empCursor.getString(2));
                employee.setDeptId(empCursor.getInt(3));
                mEmployeeList.add(employee);
            }
            mEmployeeMap.put(deptId, mEmployeeList);

        }
    }

}
