package it.marcosoft.ticketwave.data;

import android.os.Parcel;
import android.os.Parcelable;

public class CardData implements Parcelable {
    private String destination;
    private String dateFrom;
    private String dateTo;
    private String id;

    public CardData(String destination, String dateFrom, String dateTo, String id) {
        this.destination = destination;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.id = id;
    }

    protected CardData(Parcel in) {
        destination = in.readString();
        dateFrom = in.readString();
        dateTo = in.readString();
        id = in.readString();
    }

    public static final Creator<CardData> CREATOR = new Creator<CardData>() {
        @Override
        public CardData createFromParcel(Parcel in) {
            return new CardData(in);
        }

        @Override
        public CardData[] newArray(int size) {
            return new CardData[size];
        }
    };

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(destination);
        dest.writeString(dateFrom);
        dest.writeString(dateTo);
        dest.writeString(id);
    }
}
