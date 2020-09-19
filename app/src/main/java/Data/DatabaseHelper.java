package Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import Model.User;
import Utilities.Utilities;

public class DatabaseHelper extends SQLiteOpenHelper implements Utilities {
    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder sql =
                new StringBuilder().append("CREATE TABLE \"").append(USER_TABLE).append("\" (\n").
                        append("\t\"").append(ID).append("\"\tINTEGER NOT NULL,\n").
                        append("\t\"").append(FIRSTNAME).append("\"\tTEXT NOT NULL,\n").
                        append("\t\"").append(SURNAME).append("\"\tTEXT NOT NULL,\n").
                        append("\t\"").append(EMAIL).append("\"\tTEXT NOT NULL UNIQUE,\n").
                        append("\t\"").append(PASSWORD).append("\"\tTEXT NOT NULL,\n").
                        append("\t\"").append(MOBILE).append("\"\tTEXT NOT NULL UNIQUE,\n").
                        append("\t\"").append(GENDER).append("\"\tTEXT NOT NULL,\n").
                        append("\t\"").append(CURRENT_BALANCE).append("\"\tREAL,\n").
                        append("\t\"").append(SAVINGS_BALANCE).append("\"\tREAL,\n").
                        append("\tPRIMARY KEY(\"").append(ID).append("\" AUTOINCREMENT));");
        db.execSQL(sql.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        this.onCreate(db);
    }

    public boolean isRegistered(String email){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+USER_TABLE+" WHERE EMAIL='"+email+"'", null);
        boolean result = cursor.moveToFirst();
        cursor.close();
        return result;
    }

    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FIRSTNAME, user.getNAME());
        values.put(SURNAME, user.getSURNAME());
        values.put(EMAIL, user.getEMAIL());
        values.put(PASSWORD, user.getPASSWORD());
        values.put(MOBILE, user.getMOBILE());
        values.put(GENDER, user.getGENDER());
        values.put(CURRENT_BALANCE, user.getCURRENT_BALANCE());
        values.put(SAVINGS_BALANCE, user.getSAVINGS_BALANCE());
        db.insert(USER_TABLE,null,values);
        db.close();
    }

    public User getUserDetails(String email)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        //SELECT
        String[] columns = new String[]{FIRSTNAME,SURNAME,CURRENT_BALANCE,SAVINGS_BALANCE};
        //WHERE clause
        String selection = EMAIL + "=?";
        //WHERE clause arguments
        String[] selectionArgs = {email};
        Cursor c  = db.query(USER_TABLE, columns, selection, selectionArgs, null,null, null, null);
        if(c != null)  c.moveToFirst();
        User user = new User();
        user.setNAME(c.getString(0));
        user.setSURNAME(c.getString(1));
        user.setCURRENT_BALANCE(Double.parseDouble(c.getString(2)));
        user.setSAVINGS_BALANCE(Double.parseDouble(c.getString(3)));
        return user;

    }//validate Logi
    public User ValidateLogin(String un, String pwd)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        //SELECT
        String[] columns = new String[]{FIRSTNAME,SURNAME,EMAIL,PASSWORD ,CURRENT_BALANCE,SAVINGS_BALANCE,ID};
        //WHERE clause
        String selection = EMAIL + "=? OR " + PASSWORD + "=?";
        //WHERE clause arguments
        String[] selectionArgs = {un, pwd};
        Cursor c  = db.query(USER_TABLE, columns, selection, selectionArgs, null,null, null, null);
        if(c != null)  c.moveToFirst();
        User user = new User();
        user.setNAME(c.getString(0));
        user.setSURNAME(c.getString(1));
        user.setEMAIL(c.getString(2));
        user.setPASSWORD(c.getString(3));
        user.setCURRENT_BALANCE(Double.parseDouble(c.getString(4)));
        user.setSAVINGS_BALANCE(Double.parseDouble(c.getString(5)));
        user.setID(Integer.parseInt(c.getString(6)));
        return user;

    }//validate Login
}
