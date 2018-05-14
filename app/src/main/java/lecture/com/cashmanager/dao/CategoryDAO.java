package lecture.com.cashmanager.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import lecture.com.cashmanager.model.Category;

import static android.content.ContentValues.TAG;

public class CategoryDAO extends SQLiteOpenHelper {

    private static final String DB_NAME = "cash_manager" ;
    private static final String TABLE_NAME = "cash_category" ;
    private static final String ID = "id" ;
    private static final String NAME = "name";
    private static final String TYPE = "type";
    private static final int    VERSION = 1;

    private static final String CREATE_TABLE = "CREATE TABLE "+
                                                TABLE_NAME+ "(" +
                                                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                                                NAME + " TEXT, "+
                                                TYPE + " TEXT)";

    private Context context;

    public CategoryDAO(Context context) {
        super(context, DB_NAME, null, VERSION);
        this.context = context;

        Log.d(TAG, ">>>>>>>>>>>>>>>>>>>>>>>>CategoryDAO: ");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        Log.d(TAG, "On Create Database: " + CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.d(TAG, "On Upgrade Database: ");
    }

    public void addCategory(Category category){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(NAME, category.getName());
        values.put(TYPE, category.getType());

        if(database.insert(TABLE_NAME, null, values) != -1){
            Log.d(TAG, "addCategory: Successful ");
            Log.d(TAG, "Name: " + category.getName());
            Log.d(TAG, "Type: " + category.getType());
        }
        database.close();


    }

    public List<Category> getAllCategory(String type){
        List<Category> lstCategory = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + TYPE + " LIKE " + type;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                Category category = new Category();
                category.setId(cursor.getInt(0));
                category.setName(cursor.getString(1));
                category.setType(cursor.getString(2));

                lstCategory.add(category);
            }while (cursor.moveToNext());
        }

        db.close();
        return lstCategory;
    }

    public List<String> getAllStringCategory(String type){
        List<String> lstCategory = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + TYPE + " LIKE " + type;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                lstCategory.add(cursor.getString(1));
            }while (cursor.moveToNext());
        }

        db.close();
        return lstCategory;
    }
}
