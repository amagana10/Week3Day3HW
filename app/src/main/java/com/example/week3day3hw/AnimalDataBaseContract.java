package com.example.week3day3hw;

import android.util.Log;

import java.util.Locale;

public class AnimalDataBaseContract {
    public static final String DATABASE_NAME = "Animal_db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "Animals";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_SOUND = "sound";
    public static final String COLUMN_IMAGE = "image";
    public static final String COLUMN_ID= "id";

    public static String createQuery() {
        StringBuilder queryBuilder = new StringBuilder();
        //Query to create Table
        queryBuilder.append("CREATE TABLE ");
        queryBuilder.append(TABLE_NAME);
        queryBuilder.append(" ( ");
        //Must have unique primary key
        queryBuilder.append(COLUMN_ID);
        queryBuilder.append(" ");
        queryBuilder.append(" INTEGER PRIMARY KEY AUTOINCREMENT, ");
        //Add rest of the columns
        queryBuilder.append(COLUMN_TYPE);
        queryBuilder.append(" TEXT, ");
        queryBuilder.append(COLUMN_NAME);
        queryBuilder.append(" TEXT, ");
        queryBuilder.append(COLUMN_SOUND);
        queryBuilder.append(" TEXT, ");
        queryBuilder.append(COLUMN_IMAGE);
        queryBuilder.append(" TEXT )");

        //Log the query so we can check for correctness
        Log.d("TAG", "createQuery: " + queryBuilder.toString());

        return queryBuilder.toString();
    }

    public static String getAllQuery() {
        return "SELECT * FROM " + TABLE_NAME;
    }

    public static String getOneById(int id) {
        return String.format("SELECT * FROM %s WHERE %s = \"%d\"", TABLE_NAME, COLUMN_ID, id);
    }

    public static String getWhereClauseById() {
        return String.format(Locale.US, "%s = ", COLUMN_ID);
    }


}
