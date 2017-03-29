package com.nexteducation.sqliteemployeedept.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.nexteducation.sqliteemployeedept.R;
import com.nexteducation.sqliteemployeedept.model.Department;
import com.nexteducation.sqliteemployeedept.sqlite.SQLiteDatabaseHelper;

import java.util.List;

/**
 * Created by next on 25/3/17.
 */
public class DepartmentListAdapter extends BaseAdapter
{
    private static final String EMPLOYEE_TABLE = "Employee";
    private static final String EMP_DEPT_ID = "emp_dept_id";


    private Context mContext;
    private List<Department> mDepartmentList;
    SQLiteDatabaseHelper mDatabaseHelper;
    public DepartmentListAdapter(Context context, List<Department> departmentList)
    {
        mContext = context;
        mDepartmentList = departmentList;
        mDatabaseHelper = new SQLiteDatabaseHelper(context);
    }
    @Override
    public int getCount() {
        return mDepartmentList.size();
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.department_item, parent, false);

        TextView departmentName = (TextView) convertView.findViewById(R.id.depart_name_txt);
        TextView headCountTextView = (TextView) convertView.findViewById(R.id.head_count);
        departmentName.setText(mDepartmentList.get(position).getName());
        headCountTextView.setText(Integer.toString(mDepartmentList.get(position).getHeadCount()));

        return convertView;
    }
}
