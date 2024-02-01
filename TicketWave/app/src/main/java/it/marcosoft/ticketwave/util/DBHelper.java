package it.marcosoft.ticketwave.util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import it.marcosoft.ticketwave.CardData;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "travel_database";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Definisci la tabella per i viaggi
        String createTable = "CREATE TABLE IF NOT EXISTS travels " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "from_location TEXT, " +
                "to_location TEXT, " +
                "destination TEXT);";  // Modifica il nome del campo per corrispondere al tuo layout
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Implementa l'aggiornamento del database se necessario
    }

    // All'interno della classe DBHelper
    // All'interno della classe DBHelper
    public List<CardData> getAllTravelData() {
        List<CardData> cardDataList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("travels", null, null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String destination = cursor.getString(cursor.getColumnIndex("destination"));
                String fromLocation = cursor.getString(cursor.getColumnIndex("from_location"));
                String toLocation = cursor.getString(cursor.getColumnIndex("to_location"));

                CardData cardData = new CardData(destination, fromLocation, toLocation);
                cardDataList.add(cardData);
            } while (cursor.moveToNext());

            cursor.close();
        }

        return cardDataList;
    }


}
