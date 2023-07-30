package com.racartech.library.rctandroid.snippets;

import android.content.Context;
import android.widget.Toast;

public class SnippetSQLite {


    public static void test(Context context){
        Toast.makeText(context, "Android Basic", Toast.LENGTH_SHORT).show();
        /*
    NOTE : this is a snippet class intended to help developers to fasten there production
           by providing them easy to use code common code snippets
        */
    }





    /*

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "Products.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("CREATE TABLE Products (product_name TEXT PRIMARY KEY, product_description TEXT, product_price DECIMAL, product_quantity INT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("DROP TABLE IF EXISTS Products");
    }

    public Boolean insertProductData(String product_name, String product_description, double product_price, int product_quantity) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("product_name", product_name);
        contentValues.put("product_description", product_description);
        contentValues.put("product_price", product_price);
        contentValues.put("product_quantity", product_quantity);

        long result = DB.insert("Products", null, contentValues);

        if(result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean updateProductData(String product_name, String product_description, double product_price, int product_quantity) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("product_name", product_name);
        contentValues.put("product_description", product_description);
        contentValues.put("product_price", product_price);
        contentValues.put("product_quantity", product_quantity);
        Cursor cursor = DB.rawQuery("SELECT * FROM Products WHERE LOWER(product_name)=LOWER(?)", new String[]{product_name});
        if(cursor.getCount() > 0) {
            long result = DB.update("Products", contentValues, "LOWER(product_name)=(?)", new String[]{product_name});

            if(result == -1) {
                return false;
            } else {
                return true;
            }
        }
        else
        {
            return  false;
        }



    }

    public Boolean deleteProductData(String product_name) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM Products WHERE LOWER(product_name)=LOWER(?)", new String[]{product_name});
        if(cursor.getCount() > 0) {
            long result = DB.delete("Products", "LOWER(product_name)=LOWER(?)", new String[]{product_name});
            if(result == -1) {
                return false;
            }
            else
            {
                return true;
            }
        }
        else
        {
            return  false;
        }
    }

    public Boolean checkOut(String product_name) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Cursor cursor = DB.rawQuery("SELECT (product_quantity) FROM Products WHERE product_name=?", new String[]{product_name});
        if(cursor.getInt(0) != 0) {
            contentValues.put("product_quantity", cursor.getInt(0)-1);
        }
        else
        {
            contentValues.put("product_quantity", cursor.getInt(0));
        }
        long result = DB.update("Products", contentValues, "product_name=?", new String[]{product_name});
        return true;
    }


    public Cursor searchProductData(String product_name, Boolean isEmpty) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor;

        cursor = DB.rawQuery("SELECT * FROM Products WHERE product_name LIKE ? AND product_quantity != 0", new String[]{"%" + product_name + "%"});

        return cursor;

    }
}

     */

}
