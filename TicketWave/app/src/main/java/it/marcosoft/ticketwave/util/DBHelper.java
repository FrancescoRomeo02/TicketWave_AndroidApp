package it.marcosoft.ticketwave.util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import it.marcosoft.ticketwave.data.CardData;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "travel_database";
    private static final int DATABASE_VERSION = 1;

    // Constructor
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Create the database table when the database is created
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Define the table for travels
        String createTable = "CREATE TABLE IF NOT EXISTS travels " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "from_location TEXT, " +
                "to_location TEXT, " +
                "destination TEXT)";
        db.execSQL(createTable);
    }

    // Implement database upgrade if needed
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Implement database upgrade if necessary
        // This method is called when the version number of your database changes
    }

    // Method to clear all data from the "travels" table
// warning but when is never used this method
    public void clearDatabase() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete all rows in the "travels" table
        db.delete("travels", null, null);
        // Reset the auto-increment value for the "id" column
        db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = 'travels'");
        // Close the database connection
        db.close();
    }
    // Retrieve all travel data from the database
    public List<CardData> getAllTravelData() {
        List<CardData> cardDataList = new ArrayList<>();

        // Get a readable database instance
        SQLiteDatabase db = this.getReadableDatabase();

        // Query the "travels" table to retrieve all rows
        Cursor cursor = db.query("travels", null, null, null, null, null, null);

        // Check if the cursor is not null and move to the first row
        if (cursor != null && cursor.moveToFirst()) {
            do {
                // Retrieve data from the cursor
                String destination = getColumnValue(cursor, "destination");
                String fromLocation = getColumnValue(cursor, "from_location");
                String toLocation = getColumnValue(cursor, "to_location");
                String id = getColumnValue(cursor, "id");
                // Create a CardData object and add it to the list
                CardData cardData = new CardData(destination, fromLocation, toLocation, id);
                cardDataList.add(cardData);

                // Move to the next row in the cursor
            } while (cursor.moveToNext());

            // Close the cursor to avoid memory leaks
            cursor.close();
        }

        // Return the list of CardData objects
        return cardDataList;
    }

    // Retrieve data for a single row based on the specified id
    public CardData getTravelDataById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        // Query the "travels" table to retrieve a single row based on id
        Cursor cursor = db.query("travels", null, "id=?", new String[]{String.valueOf(id)}, null, null, null);

        // Check if the cursor is not null and move to the first row
        if (cursor != null && cursor.moveToFirst()) {
            // Retrieve data from the cursor
            String destination = getColumnValue(cursor, "destination");
            String fromLocation = getColumnValue(cursor, "from_location");
            String toLocation = getColumnValue(cursor, "to_location");
            // Create a CardData object for the single row
            CardData cardData = new CardData(destination, fromLocation, toLocation, String.valueOf(id));

            // Close the cursor to avoid memory leaks
            cursor.close();

            // Return the CardData object for the specified id
            return cardData;
        }

        // Return null if no data found for the specified id
        return null;
    }

    // Helper method to get column value from cursor safely
    private String getColumnValue(Cursor cursor, String columnName) {
        int columnIndex = cursor.getColumnIndex(columnName);
        return (columnIndex != -1) ? cursor.getString(columnIndex) : "";
    }
}
