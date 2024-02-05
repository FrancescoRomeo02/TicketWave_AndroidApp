package it.marcosoft.ticketwave.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import it.marcosoft.ticketwave.data.LikedData;
import it.marcosoft.ticketwave.util.db.DBHelperLiked;

public class LikedViewModel extends AndroidViewModel {

    private final MutableLiveData<List<LikedData>> likedEventsLiveData = new MutableLiveData<>();
    private final DBHelperLiked dbHelper;

    public LikedViewModel(@NonNull Application application) {
        super(application);
        dbHelper = new DBHelperLiked(application);
        loadLikedEvents();
    }

    public LiveData<List<LikedData>> getLikedEvents() {
        return likedEventsLiveData;
    }

    public void addLikedEvent(LikedData likedData) {
        dbHelper.addLikedEvent(likedData);
        loadLikedEvents();
    }

    String userID = "cacca";
    private void loadLikedEvents() {
        List<LikedData> likedEvents = dbHelper.getAllLikedEventsData();
        likedEventsLiveData.setValue(likedEvents);
    }
}
