package com.example.week3day3hw;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;

import static com.example.week3day3hw.AnimalDataBaseContract.*;

public class AnimalDBHelper extends SQLiteOpenHelper {
    public AnimalDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createQuery());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    public long insertIntoDB(@NonNull Animal animal){
        SQLiteDatabase writeDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_TYPE,animal.getType());
        contentValues.put(COLUMN_NAME,animal.getName());
        contentValues.put(COLUMN_SOUND,animal.getSound());
        contentValues.put(COLUMN_IMAGE,animal.getImage());

        return writeDB.insert(TABLE_NAME,null,contentValues);
    }

    public ArrayList<Animal> getAllFromDB() {
        ArrayList<Animal> returnList = new ArrayList<>();
        SQLiteDatabase readableDatabase = this.getReadableDatabase();
        Cursor cursor = readableDatabase.rawQuery(getAllQuery(), null);
        if(cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                String type = cursor.getString(cursor.getColumnIndex(COLUMN_TYPE));
                String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                String sound = cursor.getString(cursor.getColumnIndex(COLUMN_SOUND));
                String image = cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE));

                //add to list
                returnList.add(new Animal(type,name,sound,image,id));
            } while (cursor.moveToNext());
            //return the result in a list
        }
        cursor.close();
        return returnList;
    }

    //Get One entry from database
    public Animal getById(int id) {
        SQLiteDatabase readDB = this.getReadableDatabase();
        //Car to return
        Animal returnAnimal = new Animal();
        //cursor to hold results
        Cursor cursor = readDB.rawQuery(getOneById(id), null);
        if(cursor.moveToFirst()){
            int idFromDB = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
            String type = cursor.getString(cursor.getColumnIndex(COLUMN_TYPE));
            String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
            String sound = cursor.getString(cursor.getColumnIndex(COLUMN_SOUND));
            String image = cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE));
            //set return car
            returnAnimal = new Animal(type, name, sound, image, idFromDB);
        }
        cursor.close();
        return returnAnimal;
    }

    //update an item in the database
    public long updateInDB(@NonNull Animal newAnimalInfo) {
        SQLiteDatabase writeDB = this.getWritableDatabase();
        //Data container used for database key value pairs
        ContentValues contentValues = new ContentValues();

        //inset key value pairs into the contentValues container
        contentValues.put(COLUMN_TYPE, newAnimalInfo.getType());
        contentValues.put(COLUMN_NAME, newAnimalInfo.getName());
        contentValues.put(COLUMN_SOUND, newAnimalInfo.getSound());
        contentValues.put(COLUMN_IMAGE, newAnimalInfo.getImage());

        return writeDB.update(TABLE_NAME,
                contentValues,
                getWhereClauseById(),
                new String[]{String.valueOf(newAnimalInfo.getAnimalID())});
    }

    //delete entry(ies) from the database
    public long deleteFromDatabaseById(String[] id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME, getWhereClauseById() + id[0], null);

    }
}
