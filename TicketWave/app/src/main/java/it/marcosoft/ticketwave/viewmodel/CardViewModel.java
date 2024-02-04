package it.marcosoft.ticketwave.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import it.marcosoft.ticketwave.data.CardData;

public class CardViewModel extends ViewModel {
    private MutableLiveData<CardData[]> cardData = new MutableLiveData<>();

    public MutableLiveData<CardData[]> getCardData() {
        return cardData;
    }

    public void setCardData(CardData[] data) {
        cardData.setValue(data);
    }
}
