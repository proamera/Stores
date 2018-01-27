package me.dvit.stores.Activities.Activities.Classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by amera on 1/26/2018.
 */

public class BaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Stores.db";
    public static final String ITEMS_TABLE_NAME = "item_table";
    public static final String ITEM_COL_1 = "ID";
    public static final String ITEM_COL_2 = "SHOPNAME";
    public static final String ITEM_COL_3 = "ITEMNAME";
    public static final String ITEM_COL_4 = "QUANTITY";
    public static final String ITEM_COL_5 = "CATEGORY";
    public static final String ITEM_COL_6 = "DATE";
    public static final String ITEM_COL_7 = "NOTES";
    public static final String SHOPS_TABLE_NAME = "shops_table";
    public static final String SHOP_COL_1 = "ID";
    public static final String SHOP_COL_2 = "LOCATION";
    public static final String USERS_TABLE_NAME = "shops_table";
    public static final String USER_COL_1 = "ID";
    public static final String USER_COL_2 = "EMAIL";

    public BaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + ITEMS_TABLE_NAME +"(ID INTEGER PRIMARY KEY AUTOINCREMENT,SHOPNAME TEXT,ITEMNAME TEXT,QUANTITY INTEGER , CATEGORY TEXT , DATE TEXT , NOTES TEXT)");
        db.execSQL("create table " + SHOPS_TABLE_NAME +"(ID INTEGER PRIMARY KEY AUTOINCREMENT,SHOPNAME TEXT,LOCATION TEXT,USERMAIL TEXT FOREIGN KEY REFERENCES USERS_TABLE_NAME(USERMAIL )");
        db.execSQL("create table " + USERS_TABLE_NAME +"(ID INTEGER AUTOINCREMENT ,USERMAIL TEXT PRIMARY KEY)");

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ITEMS_TABLE_NAME);
        onCreate(db);
    }
    public boolean insertItemData(String shop_name,String item_name,String quantity , String category , String date , String notes) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ITEM_COL_2,shop_name);
        contentValues.put(ITEM_COL_3,item_name);
        contentValues.put(ITEM_COL_4,quantity);
        contentValues.put(ITEM_COL_5,category);
        contentValues.put(ITEM_COL_6,date);
        contentValues.put(ITEM_COL_7,notes);
        long result = db.insert(ITEMS_TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getItemData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ITEMS_TABLE_NAME,null);
        return res;
    }

    public boolean updateItemData(String id,String shop_name,String item_name,String quantity , String category , String date , String notes) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ITEM_COL_1,id);
        contentValues.put(ITEM_COL_2,shop_name);
        contentValues.put(ITEM_COL_3,item_name);
        contentValues.put(ITEM_COL_4,quantity);
        contentValues.put(ITEM_COL_5,category);
        contentValues.put(ITEM_COL_6,date);
        contentValues.put(ITEM_COL_7,notes);
        db.update(ITEMS_TABLE_NAME, contentValues, "ID = ?",new String[] { id });
        return true;
    }
    public Integer deleteItemData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(ITEMS_TABLE_NAME, "ID = ?",new String[] {id});
    }
    public boolean insertShopData(String shop_name,String location,String user_mail) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ITEM_COL_2,shop_name);
        contentValues.put(SHOP_COL_2,location);
        contentValues.put(USER_COL_1,user_mail);
        long result = db.insert(SHOPS_TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getShopData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+SHOPS_TABLE_NAME,null);
        return res;
    }

    public boolean updateShopData(String id,String shop_name,String location,String user_mail) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SHOP_COL_1,id);
        contentValues.put(ITEM_COL_2,shop_name);
        contentValues.put(SHOP_COL_2,location);
        contentValues.put(USER_COL_1,user_mail);
        db.update(SHOPS_TABLE_NAME, contentValues, "ID = ?",new String[] { id });
        return true;
    }
    public Integer deleteShopData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(USERS_TABLE_NAME, "ID = ?",new String[] {id});
    }
    public boolean insertUData(String user_mail) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_COL_2,user_mail);

        long result = db.insert(USERS_TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getUserData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+USERS_TABLE_NAME,null);
        return res;
    }

    public boolean updateUserData(String id,String user_mail) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_COL_1,id);
        contentValues.put(ITEM_COL_2,user_mail);
        db.update(USERS_TABLE_NAME, contentValues, "ID = ?",new String[] { id });
        return true;
    }
    public Integer deleteUserData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(USERS_TABLE_NAME, "ID = ?",new String[] {id});
    }
}
