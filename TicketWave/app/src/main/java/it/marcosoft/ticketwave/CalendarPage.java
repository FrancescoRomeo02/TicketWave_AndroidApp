package it.marcosoft.ticketwave;

import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CalendarPage extends AppCompatActivity {
    CalendarView calendarView;
    Calendar calendar;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //calendarView = findViewById(R.id.calenderView);
        calendar = Calendar.getInstance();
        setDate(1,1,2023); //TASK: da settare con gli eventi selezionati

        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> Toast.makeText(CalendarPage.this, dayOfMonth +"/" + month + 1 + "/" + "" + year, Toast.LENGTH_SHORT).show());

    }
    public void getDate(){
        long date = calendarView.getDate();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy", Locale.getDefault());
        String selected_date = simpleDateFormat.format(calendar.getTime());
        Toast.makeText(this, selected_date, Toast.LENGTH_SHORT).show();
    }
    public void setDate(int day, int month, int year){
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month-1);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        long milli = calendar.getTimeInMillis();
        calendarView.setDate(milli);
    }

    /*METODO PER INSERIRE EVENTI SUL CALENDARIO

    public static long pushAppointmentsToCalender(Activity curActivity, String title, String addInfo, String place, int status, long startDate, boolean needReminder, boolean needMailService) {
        String eventUriString = "content://com.android.calendar/events";
        ContentValues eventValues = new ContentValues();

        eventValues.put("calendar_id", 1);
        eventValues.put("title", title);
        eventValues.put("description", addInfo);
        eventValues.put("eventLocation", place);

        long endDate = startDate + 1000 * 60 * 60; // For next 1hr

        eventValues.put("dtstart", startDate);
        eventValues.put("dtend", endDate);

        eventValues.put("eventStatus", status);
        eventValues.put("eventTimezone", "UTC/GMT +2:00");
        eventValues.put("hasAlarm", 1);

        Uri eventUri = curActivity.getApplicationContext().getContentResolver().insert(Uri.parse(eventUriString), eventValues);
        long eventID = Long.parseLong(eventUri.getLastPathSegment());

        if (needReminder) {

            String reminderUriString = "content://com.android.calendar/reminders";

            ContentValues reminderValues = new ContentValues();

            reminderValues.put("event_id", eventID);
            reminderValues.put("minutes", 5); // Default value of the
            // system. Minutes is a
            // integer
            reminderValues.put("method", 1); // Alert Methods: Default(0),
            // Alert(1), Email(2),
            // SMS(3)

            Uri reminderUri = curActivity.getApplicationContext().getContentResolver().insert(Uri.parse(reminderUriString), reminderValues);
        }

        if (needMailService) {
            String attendeuesesUriString = "content://com.android.calendar/attendees";

            ContentValues attendeesValues = new ContentValues();
            attendeesValues.put("event_id", eventID);
            attendeesValues.put("attendeeName", "xxxxx"); // Attendees name
            attendeesValues.put("attendeeEmail", "yyyy@gmail.com");// Attendee

            attendeesValues.put("attendeeRelationship", 0); // Relationship_Attendee(1),

            attendeesValues.put("attendeeType", 0); // None(0), Optional(1),
            attendeesValues.put("attendeeStatus", 0); // NOne(0), Accepted(1),
            Uri attendeuesesUri = curActivity.getApplicationContext().getContentResolver().insert(Uri.parse(attendeuesesUriString), attendeesValues);
        }
        return eventID;

    } */
    }



