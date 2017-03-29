package com.nexteducation.sqliteemployeedept.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nexteducation.sqliteemployeedept.R;
import com.nexteducation.sqliteemployeedept.model.Employee;
import com.nexteducation.sqliteemployeedept.sqlite.SQLiteDatabaseHelper;

import java.util.List;

/**
 * Created by next on 24/3/17.
 */
public class EmployeeListAdapter extends BaseAdapter
{
    Context mContext;
    List<Employee> mEmployeeList;
    SQLiteDatabaseHelper mDatabaseHelper;
    public EmployeeListAdapter(Context context, List<Employee> employeeList)
    {
        mContext = context;
        mEmployeeList = employeeList;
        mDatabaseHelper = new SQLiteDatabaseHelper(context);
    }

    @Override
    public int getCount() {
        return mEmployeeList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if(convertView == null)
            convertView = LayoutInflater.from(mContext).inflate(R.layout.employee_item, parent, false);

        TextView employeeName = (TextView) convertView.findViewById(R.id.employee_name);
        TextView dateOfJoining = (TextView) convertView.findViewById(R.id.date_of_joining);
        TextView departID = (TextView) convertView.findViewById(R.id.department_id);
        TextView departmentName = (TextView) convertView.findViewById(R.id.department_name);

        employeeName.setText("Name: "+mEmployeeList.get(position).getName());
        dateOfJoining.setText("Date of joining: "+mEmployeeList.get(position).getDateOfJoining());
        departID.setText("Dept ID: "+mEmployeeList.get(position).getDeptId());

        if(mEmployeeList.get(position).getDeptName() != null)
            departmentName.setText("Department: "+mEmployeeList.get(position).getDeptName());
        return convertView;
    }
}
