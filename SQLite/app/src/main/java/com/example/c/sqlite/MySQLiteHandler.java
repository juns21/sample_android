package com.example.c.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by c on 2015-01-18.
 */
public class MySQLiteHandler {
    MySQLiteOpenHelper helper;
    SQLiteDatabase db;

    public MySQLiteHandler(Context context) {
        helper = new MySQLiteOpenHelper(context, "person.sqlite", null, 1);
    }

    public void insert(String name, int age, String address) {
        //String sql = "INSERT INTO STUDENT VALUES(NULL, '"+ name +"', '"+ age +"', '"+ address +"')";
        //db.execSQL(sql);

        ContentValues value = new ContentValues();
        value.put("name", name);
        value.put("age", age);
        value.put("address", address);

        db = helper.getWritableDatabase();
        db.insert("STUDENT", null, value);
    }

    public void updateAge(String name, int newAge) {
        ContentValues values = new ContentValues();
        values.put("age", newAge);

        db = helper.getWritableDatabase();
        db.update("STUDENT", values, "name=?", new String[] {name});
    }

    public void delete(String name) {
        db = helper.getWritableDatabase();
        db.delete("STUDENT", "name=?", new String[] {name});
    }

    public String getAllData() {
        String res = "";
        db = helper.getReadableDatabase();
        Cursor c = db.query("STUDENT", null, null, null, null, null, null);

        // String sql = "SELECT * from STUDENT";
        // Cursor c = db.rawQuery(sql, null);

        while(c.moveToNext()) {
            int age = c.getInt(c.getColumnIndex("AGE"));
            String name = c.getString(c.getColumnIndex("NAME"));
            String address = c.getString(c.getColumnIndex("ADDRESS"));
            int id = c.getInt(c.getColumnIndex("ID"));

            res += "id: "+ id +", name: "+ name +", age: "+ age +", address: "+ address +"\n";
        }
        return res;
    }
}
