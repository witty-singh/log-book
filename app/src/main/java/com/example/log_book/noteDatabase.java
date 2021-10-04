package com.example.log_book;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class noteDatabase extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION=5;
    private static final String DATABASE_NAME="PATIENTS";
    private static final String DATABASE_TABLE="PATIENTTABLES";


    private static final String KEY_ID="id";
    private static final String KEY_NAME="name";
    private static final String KEY_MEDICINE="medicine";
    private static final String KEY_SYMPTOM="symptom";
    private static final String KEY_CHARGES="charges";
    private static final String KEY_DATE="date";
    private static final String KEY_AGE="age";


    public noteDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE "+ DATABASE_TABLE+"("+KEY_ID+" INTEGER PRIMARY KEY,"+
                KEY_NAME+" TEXT,"+
                KEY_MEDICINE+" TEXT,"+
                KEY_SYMPTOM+" TEXT,"+
                KEY_CHARGES+" TEXT,"+
                KEY_DATE+" TEXT,"+
                KEY_AGE+" TEXT"+")";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if(oldVersion>=newVersion)
            return;
        db.execSQL("DROP TABLE IF EXISTS "+DATABASE_TABLE);
        onCreate(db);
    }
    
    public long AddNote(Note note){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues c=new ContentValues();
        c.put(KEY_NAME,note.getNAME());
        c.put(KEY_MEDICINE,note.getMEDICINE());
        c.put(KEY_SYMPTOM,note.getSYMPTOM());
        c.put(KEY_CHARGES,note.getCHARGES());
        c.put(KEY_DATE,note.getDATE());
        c.put(KEY_AGE,note.getAGE());

        long ID = db.insert(DATABASE_TABLE, null, c);
        Log.d("inserted", " id->"+ ID);
        return ID;
    }
    public Note getnote(long id){
        SQLiteDatabase db =this.getReadableDatabase();
        Cursor cursor=db.query(DATABASE_TABLE,new String []{ KEY_ID,KEY_NAME,KEY_MEDICINE,KEY_SYMPTOM,KEY_CHARGES,KEY_DATE,KEY_AGE},KEY_ID+"=?",
                new String []{ String.valueOf(id)},null,null,null);
        if(cursor!=null)
            cursor.moveToFirst();

        return new Note(cursor.getLong(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),
                cursor.getString(4), cursor.getString(5),cursor.getString(6));
    }
    public List<Note> getnotes(){
        SQLiteDatabase db =this.getReadableDatabase();
        List<Note> allnotes=new ArrayList<Note>();

        String query= "SELECT * FROM "+DATABASE_TABLE;
        Cursor cursor=db.rawQuery(query,null);
        if(cursor.moveToFirst()) {
            do {
                Note note = new Note();
                note.setID(cursor.getLong(0));
                note.setNAME(cursor.getString(1));
                note.setMEDICINE(cursor.getString(2));
                note.setSYMPTOM(cursor.getString(3));
                note.setCHARGES(cursor.getString(4));
                note.setDATE(cursor.getString(5));
                note.setAGE(cursor.getString(6));
                Log.d("check", " age= "+ note.getAGE());

                allnotes.add(note);

            } while (cursor.moveToNext());
        }
        return allnotes;
    }

    void deleteNote(long id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DATABASE_TABLE,KEY_ID+"=?",new String[]{String.valueOf(id)});

        db.close();
    }

}
