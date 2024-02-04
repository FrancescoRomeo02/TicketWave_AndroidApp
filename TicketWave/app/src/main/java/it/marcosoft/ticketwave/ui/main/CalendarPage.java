package it.marcosoft.ticketwave.ui.main;

import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import it.marcosoft.ticketwave.R;

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

    //warning but this never used
    public void getDate(){
        //warning but this never used
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

}



