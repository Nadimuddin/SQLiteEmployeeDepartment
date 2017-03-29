package com.nexteducation.sqliteemployeedept.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by next on 24/3/17.
 */
public class SQLiteDatabaseHelper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "EmployeeDatabase";
    public static final String EMPLOYEE_TABLE = "Employee";
    public static final String EMP_ID = "emp_id";
    public static final String EMP_NAME = "emp_name";
    public static final String DATE_OF_JOINING = "date_of_joining";
    public static final String EMP_DEPT_ID = "emp_dept_id";

    public static final String DEPARTMENT_TABLE = "Department";
    public static final String DEPT_ID = "dept_id";
    public static final String DEPT_NAME = "dept_name";

    public SQLiteDatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table "+DEPARTMENT_TABLE+"(" +
                DEPT_ID+" integer primary key autoincrement,"+
                DEPT_NAME+" text)");

        db.execSQL("create table "+EMPLOYEE_TABLE+"(" +
                EMP_ID+" integer primary key autoincrement,"+
                EMP_NAME+" text," +
                DATE_OF_JOINING+" text," +
                EMP_DEPT_ID+" integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("drop table if exists "+EMPLOYEE_TABLE);
        db.execSQL("drop table if exists "+DEPARTMENT_TABLE);
        onCreate(db);
    }

    public void insertData()
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues deptValues = new ContentValues();
        deptValues.put(DEPT_NAME, "Development");  //id -> 1
        db.insert(DEPARTMENT_TABLE, null, deptValues);

        deptValues = new ContentValues();
        deptValues.put(DEPT_NAME, "UI Design"); //id -> 2
        db.insert(DEPARTMENT_TABLE, null, deptValues);

        deptValues = new ContentValues();
        deptValues.put(DEPT_NAME, "Quality Assurance"); //id -> 3
        db.insert(DEPARTMENT_TABLE, null, deptValues);

        deptValues = new ContentValues();
        deptValues.put(DEPT_NAME, "Human Resource");    //id -> 4
        db.insert(DEPARTMENT_TABLE, null, deptValues);


        ContentValues empValues = new ContentValues();
        empValues.put(EMP_NAME, "Nadimuddin");
        empValues.put(DATE_OF_JOINING, "12-09-2016");
        empValues.put(EMP_DEPT_ID, 1);
        db.insert(EMPLOYEE_TABLE, null, empValues);

        empValues = new ContentValues();
        empValues.put(EMP_NAME, "Laxman");
        empValues.put(DATE_OF_JOINING, "21-11-2016");
        empValues.put(EMP_DEPT_ID, 1);
        db.insert(EMPLOYEE_TABLE, null, empValues);

        empValues = new ContentValues();
        empValues.put(EMP_NAME, "Neeraj");
        empValues.put(DATE_OF_JOINING, "03-01-2017");
        empValues.put(EMP_DEPT_ID, 1);
        db.insert(EMPLOYEE_TABLE, null, empValues);

        empValues = new ContentValues();
        empValues.put(EMP_NAME, "Jyotish");
        empValues.put(DATE_OF_JOINING, "03-01-2017");
        empValues.put(EMP_DEPT_ID, 1);
        db.insert(EMPLOYEE_TABLE, null, empValues);

        empValues = new ContentValues();
        empValues.put(EMP_NAME, "Arindam");
        empValues.put(DATE_OF_JOINING, "05-07-2015");
        empValues.put(EMP_DEPT_ID, 2);
        db.insert(EMPLOYEE_TABLE, null, empValues);

        empValues = new ContentValues();
        empValues.put(EMP_NAME, "Madhu");
        empValues.put(DATE_OF_JOINING, "09-12-2015");
        empValues.put(DEPT_ID, 2);
        db.insert(EMPLOYEE_TABLE, null, empValues);

        empValues = new ContentValues();
        empValues.put(EMP_NAME, "John");
        empValues.put(DATE_OF_JOINING, "25-11-2014");
        empValues.put(EMP_DEPT_ID, 2);
        db.insert(EMPLOYEE_TABLE, null, empValues);

        empValues = new ContentValues();
        empValues.put(EMP_NAME, "Arvind");
        empValues.put(DATE_OF_JOINING, "15-10-2015");
        empValues.put(EMP_DEPT_ID, 3);
        db.insert(EMPLOYEE_TABLE, null, empValues);

        empValues = new ContentValues();
        empValues.put(EMP_NAME, "Harry");
        empValues.put(DATE_OF_JOINING, "07-09-2014");
        empValues.put(EMP_DEPT_ID, 3);
        db.insert(EMPLOYEE_TABLE, null, empValues);

        empValues = new ContentValues();
        empValues.put(EMP_NAME, "Farhan");
        empValues.put(DATE_OF_JOINING, "26-11-2011");
        empValues.put(EMP_DEPT_ID, 3);
        db.insert(EMPLOYEE_TABLE, null, empValues);


        empValues = new ContentValues();
        empValues.put(EMP_NAME, "Rizwan");
        empValues.put(DATE_OF_JOINING, "11-09-2014");
        empValues.put(EMP_DEPT_ID, 4);
        db.insert(EMPLOYEE_TABLE, null, empValues);

        empValues = new ContentValues();
        empValues.put(EMP_NAME, "Vijeth");
        empValues.put(DATE_OF_JOINING, "22-08-2016");
        empValues.put(EMP_DEPT_ID, 4);
        db.insert(EMPLOYEE_TABLE, null, empValues);
    }

    public Cursor retrieveData(String query)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        return db.rawQuery(query, null);
    }

    public long getNumberOfRows(String tableName)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        return  DatabaseUtils.queryNumEntries(db, tableName);
    }

    public long getNumberOfRows(String tableName, String selection, int selectionArg)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        String s[] = new String[]{Integer.toString(selectionArg)};
        return DatabaseUtils.queryNumEntries(db, tableName, selection+"=?", s);
    }
}
