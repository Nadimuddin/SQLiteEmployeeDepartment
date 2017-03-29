package com.nexteducation.sqliteemployeedept.adapter;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.nexteducation.sqliteemployeedept.R;
import com.nexteducation.sqliteemployeedept.model.Department;
import com.nexteducation.sqliteemployeedept.model.Employee;
import com.nexteducation.sqliteemployeedept.sqlite.SQLiteDatabaseHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by next on 24/3/17.
 */
public class DepartmentExpandableListAdapter extends BaseExpandableListAdapter
{
    private static final String EMPLOYEE_TABLE = "Employee";
    private static final String EMP_DEPT_ID = "emp_dept_id";

    private Context mContext;
    private List<Department> mDepartmentList;
    private HashMap<Integer, List<Employee>> mEmployeeMap;
    SQLiteDatabaseHelper mDatabaseHelper;
    public DepartmentExpandableListAdapter(Context context, List<Department> departmentList)
    {
        mContext = context;
        mDepartmentList = departmentList;
        mDatabaseHelper = new SQLiteDatabaseHelper(context);
        mEmployeeMap = fetchEmployeesData(departmentList);
    }

    private HashMap<Integer, List<Employee>> fetchEmployeesData(List<Department> departmentList)
    {
        Cursor cursor;
        mEmployeeMap = new HashMap<>();
        for(Department department : departmentList)
        {
            List<Employee> employeeList = new ArrayList<>();
            int deptId = department.getId();
            cursor = mDatabaseHelper.retrieveData("select * from "+EMPLOYEE_TABLE+" where "+EMP_DEPT_ID+" = "+deptId);
            while (cursor.moveToNext())
            {
                Employee employee = new Employee();
                employee.setId(cursor.getInt(0));
                employee.setName(cursor.getString(1));
                employee.setDateOfJoining(cursor.getString(2));
                employee.setDeptId(cursor.getInt(3));
                employeeList.add(employee);
            }
            mEmployeeMap.put(deptId, employeeList);
        }
        return mEmployeeMap;
    }

    @Override
    public int getGroupCount() {
        return mDepartmentList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition)
    {
        int deptId = mDepartmentList.get(groupPosition).getId();
        int size = mEmployeeMap.get(deptId).size();

        return size;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent)
    {
        if(convertView == null)
            convertView = LayoutInflater.from(mContext).inflate(R.layout.department_item, parent, false);

        TextView departmentName = (TextView) convertView.findViewById(R.id.depart_name_txt);
        TextView headCountTextView = (TextView) convertView.findViewById(R.id.head_count);
        departmentName.setText(mDepartmentList.get(groupPosition).getName());
        long headCount = mDatabaseHelper.getNumberOfRows(EMPLOYEE_TABLE, EMP_DEPT_ID, mDepartmentList.get(groupPosition).getId());
        headCountTextView.setText(Long.toString(headCount));
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent)
    {
        if(convertView == null)
            convertView = LayoutInflater.from(mContext).inflate(R.layout.employee_item, parent, false);

        TextView employeeName = (TextView) convertView.findViewById(R.id.employee_name);
        TextView dateOfJoining = (TextView) convertView.findViewById(R.id.date_of_joining);
        TextView departID = (TextView) convertView.findViewById(R.id.department_id);

        int deptId = mDepartmentList.get(groupPosition).getId();
        Employee employee = mEmployeeMap.get(deptId).get(childPosition);

        employeeName.setText("Employee name: "+employee.getName());
        dateOfJoining.setText("date of joining: "+employee.getDateOfJoining());
        departID.setText("Dept Id: "+Integer.toString(employee.getDeptId()));

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
