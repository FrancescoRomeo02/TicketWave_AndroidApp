package it.marcosoft.ticketwave.util;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import java.util.Calendar;

import it.marcosoft.ticketwave.R;

public class TravelFragment extends Fragment {

    private EditText fromTravelEditText;
    private EditText toTravelEditText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_travel, container, false);

        // Inizializza gli EditText
        fromTravelEditText = rootView.findViewById(R.id.fromtravel);
        toTravelEditText = rootView.findViewById(R.id.totravel);

        // Aggiungi il listener agli EditText
        fromTravelEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(fromTravelEditText);
            }
        });

        toTravelEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(toTravelEditText);
            }
        });

        return rootView;
    }

    // Metodo per mostrare il DatePickerDialog
    // Metodo per mostrare il DatePickerDialog
    private void showDatePickerDialog(final EditText selectedEditText) {
        // Rimuovi il focus dall'EditText per evitare che la tastiera si apra
        selectedEditText.clearFocus();

        // Ottieni la data corrente
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Crea un DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(requireContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // Imposta la data selezionata nell'EditText
                        selectedEditText.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                }, year, month, day);

        // Mostra il DatePickerDialog
        datePickerDialog.show();
    }

}
