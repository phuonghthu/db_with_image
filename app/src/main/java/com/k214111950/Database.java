package com.k214111950;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import androidx.annotation.Nullable;



public class Database extends SQLiteOpenHelper {
    public static final String DB_NAME = "products.sqlite";
    public static final int DB_VERSION = 1;
    public static final String TBL_NAME = "product";
    public static final String COL_ID = "ProductId";
    public static final String COL_NAME = "ProductName";
    public static final String COL_PRICE = "ProductPrice";
    public static final String COL_IMAGE = "ProductImage";
    public Database(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }



    public Cursor GetData(String sql){
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery(sql, null);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TBL_NAME + " " +
                "(" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_NAME + " VARCHAR(50), "
                + COL_PRICE + " REAL, "
                + COL_IMAGE + " BLOB)";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    //Insert
    public boolean execSql(String sql){
        SQLiteDatabase db = getWritableDatabase();
        try{
            db.execSQL(sql);
            return  true;
        }catch (Exception e) {
            Log.e("Error: ", e.toString());
            return false;
        }

    }

    public void INSERT_Product(String COL_NAME, Double COL_PRICE, byte[] COL_IMAGE){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO " + TBL_NAME + " VALUES(null, ?, ?, ?)";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, COL_NAME);
        statement.bindDouble(2, COL_PRICE);
        statement.bindBlob(3, COL_IMAGE);

        statement.executeInsert();
    }

}
