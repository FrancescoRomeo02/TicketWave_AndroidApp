package it.marcosoft.ticketwave.util;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import it.marcosoft.ticketwave.R;
import it.marcosoft.ticketwave.data.repository.user.IUserRepository;
import it.marcosoft.ticketwave.ui.login.UserViewModel;
import it.marcosoft.ticketwave.ui.login.UserViewModelFactory;
import it.marcosoft.ticketwave.ui.main.MainActivity;
import it.marcosoft.ticketwave.util.db.DBHelper;

public class TravelFragment extends Fragment {

    private EditText fromTravelEditText;
    private EditText toTravelEditText;
    private EditText destinationEditText;

    UserViewModel userViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        IUserRepository userRepository = ServiceLocator.getInstance().
                getUserRepository(requireActivity().getApplication());
        userViewModel = new ViewModelProvider(
                requireActivity(),
                new UserViewModelFactory(userRepository)).get(UserViewModel.class);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_travel, container, false);

        // Inizializza gli EditText
        fromTravelEditText = rootView.findViewById(R.id.fromtravel);
        toTravelEditText = rootView.findViewById(R.id.totravel);
        destinationEditText = rootView.findViewById(R.id.destinationtravel);

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
                // Controlla se la data "from" è stata già selezionata
                if (fromTravelEditText.getText().toString().isEmpty()) {
                    // La data "from" non è stata ancora selezionata, mostra un messaggio
                    Toast.makeText(requireContext(), "Seleziona prima la data 'From'", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Se la data "from" è stata selezionata, mostra il DatePickerDialog per la data "to"
                showDatePickerDialog(toTravelEditText);
            }
        });

        // Aggiungi il listener al pulsante "Add"
        Button addButton = rootView.findViewById(R.id.addbuttontravel);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Controlla se i campi sono vuoti prima di inviare il form
                String fromLocation = fromTravelEditText.getText().toString().trim();
                String toLocation = toTravelEditText.getText().toString().trim();
                String destination = destinationEditText.getText().toString().trim();

                if (fromLocation.isEmpty() || toLocation.isEmpty() || destination.isEmpty()) {
                    // Almeno uno dei campi è vuoto, mostra un messaggio
                    Toast.makeText(requireContext(), "Compila tutti i campi prima di inviare il form", Toast.LENGTH_SHORT).show();
                } else {
                    // Tutti i campi sono compilati, verifica la destinazione
                    if (isValidDestination(destination)) {
                        // Destinazione valida, puoi procedere con l'invio del form
                        // ... Implementa qui la logica per l'invio del form ...
                        insertIntoDatabase();  // Esempio: richiama il metodo per inserire nel database
                    } else {
                        // Destinazione non valida, mostra un messaggio
                        Toast.makeText(requireContext(), "Destinazione non valida", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        Button logoutBtn = rootView.findViewById(R.id.useraccountbuttontravel);

        logoutBtn.setOnClickListener(v -> {
            ((MainActivity)getActivity()).goToUserPage();
        });

        return rootView;
    }

    // Metodo per mostrare il DatePickerDialog
    private void showDatePickerDialog(final EditText selectedEditText) {
        // Rimuovi il focus dall'EditText per evitare che la tastiera si apra
        selectedEditText.clearFocus();

        // Ottieni la data corrente
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Crea un DatePickerDialog con data di partenza impostata sulla data corrente
        DatePickerDialog datePickerDialog = new DatePickerDialog(requireContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // Imposta la data selezionata nell'EditText
                        selectedEditText.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                }, year, month, day);

        // Se è la data "to", imposta la data di partenza sulla data "from"
        if (selectedEditText.getId() == R.id.totravel) {
            String fromDateText = fromTravelEditText.getText().toString();
            if (!fromDateText.isEmpty()) {
                // Imposta la data di partenza sulla data "from"
                Calendar fromDate = Calendar.getInstance();
                fromDate.set(Integer.parseInt(fromDateText.split("/")[2]),
                        Integer.parseInt(fromDateText.split("/")[1]) - 1,
                        Integer.parseInt(fromDateText.split("/")[0]));
                datePickerDialog.getDatePicker().setMinDate(fromDate.getTimeInMillis());
            }
        }

        // Mostra il DatePickerDialog
        datePickerDialog.show();
    }

    private void insertIntoDatabase() {
        DBHelper dbHelper = new DBHelper(requireContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Recupera i valori dai campi di input
        String fromLocation = fromTravelEditText.getText().toString();
        String toLocation = toTravelEditText.getText().toString();
        String destination = destinationEditText.getText().toString();

        // Inserisci i dati nel database
        ContentValues values = new ContentValues();
        values.put("from_location", fromLocation);
        values.put("to_location", toLocation);
        values.put("destination", destination);

        long newRowId = db.insert("travels", null, values);

        if (newRowId != -1) {
            Log.d("Database", "Travel added to database with ID: " + newRowId);
            Toast.makeText(requireContext(), "Travel added to database", Toast.LENGTH_SHORT).show();
        } else {
            Log.e("Database", "Failed to add travel to database");
            Toast.makeText(requireContext(), "Failed to add travel to database", Toast.LENGTH_SHORT).show();
        }

        // Chiudi il database
        db.close();
    }

    private Set<String> readDestinationsFromFile() {
        Set<String> destinations = new HashSet<>();

        try {
            InputStream inputStream = getResources().openRawResource(R.raw.destinations); // Assicurati che il file sia presente nella cartella "res/raw"
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                destinations.add(line.trim());
            }

            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return destinations;
    }

    private boolean isValidDestination(String destination) {
        Set<String> validDestinations = readDestinationsFromFile();
        return validDestinations.contains(destination);
    }
}
