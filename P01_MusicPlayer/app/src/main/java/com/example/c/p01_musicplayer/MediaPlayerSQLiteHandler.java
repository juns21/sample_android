package com.example.c.p01_musicplayer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

/**
 * Created by c on 2015-02-08.
 */
public class MediaPlayerSQLiteHandler {
    MediaPlayerSQLiteOpenHelper mHelper;

    public MediaPlayerSQLiteHandler(Context context) {
        mHelper = new MediaPlayerSQLiteOpenHelper(context, "playinfo.sqlite", null, 1);
    }

    public long inert(String fileName) {
        ContentValues values = new ContentValues();
        values.put("filename", fileName);
        values.put("playtime", 0);

        return mHelper.getWritableDatabase().insert("playinfo", null, values);
    }

    public void update(int id, int playtime) {
        ContentValues values = new ContentValues();
        values.put("playtime", playtime);

        mHelper.getWritableDatabase().update("playinfo", values, "id=?", new String[]{""+id});
    }

    public int getPlaytime(int id) {
        String sql = "select * FROM playinfo where id="+ id;
        Cursor c = mHelper.getReadableDatabase().rawQuery(sql, null);

        c.moveToFirst();
        return c.getInt(c.getColumnIndex("playtime"));
    }
}
