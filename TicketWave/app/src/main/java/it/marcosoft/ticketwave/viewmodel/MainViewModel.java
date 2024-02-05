package it.marcosoft.ticketwave.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import it.marcosoft.ticketwave.util.auth.SharedPreferencesUtil;

public class MainViewModel extends ViewModel {

    private MutableLiveData<Boolean> isLoggedIn = new MutableLiveData<>();

    public LiveData<Boolean> getIsLoggedIn() {
        return isLoggedIn;
    }
    
}
