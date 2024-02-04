package it.marcosoft.ticketwave.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import it.marcosoft.ticketwave.EventModel.Event;

public class EventViewModel extends ViewModel {
    private MutableLiveData<List<Event>> eventsLiveData = new MutableLiveData<>();

    public LiveData<List<Event>> getEvents() {
        return eventsLiveData;
    }

    public void setEvents(List<Event> events) {
        eventsLiveData.setValue(events);
    }
}
