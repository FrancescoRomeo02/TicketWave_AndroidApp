package it.marcosoft.ticketwave.util.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import it.marcosoft.ticketwave.data.LikedData;

public class DBHelperLiked extends SQLiteOpenHelper {

    private static final String DATABASE_NAME_LIKED = "liked_events.db";
    private static final int DATABASE_VERSION_LIKED = 1;

    public static final String TABLE_LIKED_EVENTS = "liked_events";
    public static final String COLUMN_EVENT_ID = "event_id";
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_EVENT_TITLE = "event_title";
    public static final String COLUMN_EVENT_LOCATION = "event_location";
    public static final String COLUMN_EVENT_DATE = "event_date";
    public static final String COLUMN_EVENT_DESCRIPTION = "event_description";
    public static final String COLUMN_EVENT_IMAGE_URL = "event_image_url";

    private static final String CREATE_TABLE_LIKED_EVENTS =
            "CREATE TABLE " + TABLE_LIKED_EVENTS + "(" +
                    COLUMN_EVENT_ID + " TEXT PRIMARY KEY," +
                    COLUMN_USER_ID + " TEXT," +
                    COLUMN_EVENT_TITLE + " TEXT," +
                    COLUMN_EVENT_LOCATION + " TEXT," +
                    COLUMN_EVENT_DATE + " TEXT," +
                    COLUMN_EVENT_DESCRIPTION + " TEXT," +
                    COLUMN_EVENT_IMAGE_URL + " TEXT);";

    public DBHelperLiked(Context context) {
        super(context, DATABASE_NAME_LIKED, null, DATABASE_VERSION_LIKED);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_LIKED_EVENTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database upgrade if needed
    }


    // Method to get all liked events
    public Cursor getAllLikedEvents() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_LIKED_EVENTS, null, null, null, null, null, null);
    }

    // Method to check if an event is already liked
    public boolean isEventLiked(String eventId, String userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_EVENT_ID + " = ? AND " + COLUMN_USER_ID + " = ?";
        String[] selectionArgs = {eventId, userId};
        Cursor cursor = db.query(TABLE_LIKED_EVENTS, null, selection, selectionArgs, null, null, null);
        boolean isEventLiked = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return isEventLiked;
    }

    // Method to remove a liked event
    public void removeLikedEvent(String eventId, String userId) {
        SQLiteDatabase db = this.getWritableDatabase();
        String whereClause = COLUMN_EVENT_ID + " = ? AND " + COLUMN_USER_ID + " = ?";
        String[] whereArgs = {eventId, userId};
        db.delete(TABLE_LIKED_EVENTS, whereClause, whereArgs);
        db.close();


    }

    // Metodo per ottenere tutti gli eventi liked dal database
    public List<LikedData> getAllLikedEventsData() {
        List<LikedData> likedEventList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_LIKED_EVENTS, null, null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String eventId = cursor.getString(cursor.getColumnIndex(COLUMN_EVENT_ID));
                String userId = cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID));
                String eventTitle = cursor.getString(cursor.getColumnIndex(COLUMN_EVENT_TITLE));
                String eventLocation = cursor.getString(cursor.getColumnIndex(COLUMN_EVENT_LOCATION));
                String eventDate = cursor.getString(cursor.getColumnIndex(COLUMN_EVENT_DATE));
                String eventDescription = cursor.getString(cursor.getColumnIndex(COLUMN_EVENT_DESCRIPTION));
                String eventImageUrl = cursor.getString(cursor.getColumnIndex(COLUMN_EVENT_IMAGE_URL));

                LikedData likedData = new LikedData(eventId, userId, eventTitle, eventLocation, eventDate, eventDescription, eventImageUrl);
                likedEventList.add(likedData);
            } while (cursor.moveToNext());

            cursor.close();
        }

        db.close();
        return likedEventList;
    }

    // Metodo per aggiungere un evento liked
    public void addLikedEvent(LikedData likedData) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_EVENT_ID, likedData.getEventId());
        values.put(COLUMN_USER_ID, likedData.getUserId());
        values.put(COLUMN_EVENT_TITLE, likedData.getEventTitle());
        values.put(COLUMN_EVENT_LOCATION, likedData.getEventLocation());
        values.put(COLUMN_EVENT_DATE, likedData.getEventDate());
        values.put(COLUMN_EVENT_DESCRIPTION, likedData.getEventDescription());
        values.put(COLUMN_EVENT_IMAGE_URL, likedData.getEventImageUrl());
        db.insert(TABLE_LIKED_EVENTS, null, values);
        db.close();
    }

    public LikedData getLikedEventDataById(String eventId, String userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_EVENT_ID + " = ? AND " + COLUMN_USER_ID + " = ?";
        String[] selectionArgs = {eventId, userId};
        Cursor cursor = db.query(TABLE_LIKED_EVENTS, null, selection, selectionArgs, null, null, null);

        LikedData likedData = null;

        if (cursor != null && cursor.moveToFirst()) {
            @SuppressLint("Range") String eventTitle = cursor.getString(cursor.getColumnIndex(COLUMN_EVENT_TITLE));
            @SuppressLint("Range") String eventLocation = cursor.getString(cursor.getColumnIndex(COLUMN_EVENT_LOCATION));
            @SuppressLint("Range") String eventDate = cursor.getString(cursor.getColumnIndex(COLUMN_EVENT_DATE));
            @SuppressLint("Range") String eventDescription = cursor.getString(cursor.getColumnIndex(COLUMN_EVENT_DESCRIPTION));
            @SuppressLint("Range") String eventImageUrl = cursor.getString(cursor.getColumnIndex(COLUMN_EVENT_IMAGE_URL));

            likedData = new LikedData(eventId, userId, eventTitle, eventLocation, eventDate, eventDescription, eventImageUrl);

            cursor.close();
        }

        db.close();
        return likedData;
    }



}
