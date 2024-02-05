package it.marcosoft.ticketwave.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Represents data associated with a card, possibly related to an event.
 * Implements Parcelable for easy transfer between Android components.
 */
public class CardData implements Parcelable {
    // Fields representing information about the card
    private String destination;  // The destination of the card (e.g., location or event name)
    private String dateFrom;     // Starting date of the event or the beginning date of a range
    private String dateTo;       // Ending date of the event or the end date of a range
    private String id;           // Unique identifier for the card

    /**
     * Constructor to create a CardData object.
     *
     * @param destination The destination of the card.
     * @param dateFrom    Starting date of the event or the beginning date of a range.
     * @param dateTo      Ending date of the event or the end date of a range.
     * @param id          Unique identifier for the card.
     */
    public CardData(String destination, String dateFrom, String dateTo, String id) {
        this.destination = destination;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.id = id;
    }

    /**
     * Parcelable constructor to recreate a CardData object from a Parcel.
     *
     * @param in The Parcel containing the serialized CardData.
     */
    protected CardData(Parcel in) {
        destination = in.readString();
        dateFrom = in.readString();
        dateTo = in.readString();
        id = in.readString();
    }

    /**
     * Creator for Parcelable implementation.
     */
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

    // Getter and setter methods for the fields

    /**
     * Gets the destination of the card.
     *
     * @return The destination of the card.
     */
    public String getDestination() {
        return destination;
    }

    /**
     * Sets the destination of the card.
     *
     * @param destination The destination to set.
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }

    /**
     * Gets the starting date of the event or the beginning date of a range.
     *
     * @return The starting date of the event or the beginning date of a range.
     */
    public String getDateFrom() {
        return dateFrom;
    }

    /**
     * Sets the starting date of the event or the beginning date of a range.
     *
     * @param dateFrom The starting date to set.
     */
    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    /**
     * Gets the ending date of the event or the end date of a range.
     *
     * @return The ending date of the event or the end date of a range.
     */
    public String getDateTo() {
        return dateTo;
    }

    /**
     * Sets the ending date of the event or the end date of a range.
     *
     * @param dateTo The ending date to set.
     */
    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    /**
     * Gets the unique identifier for the card.
     *
     * @return The unique identifier for the card.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the card.
     *
     * @param id The unique identifier to set.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Describe the kinds of special objects contained in this Parcelable instance.
     *
     * @return A bitmask indicating the set of special object types marshaled by this Parcelable object instance.
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Flatten this object into a Parcel.
     *
     * @param dest  The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(destination);
        dest.writeString(dateFrom);
        dest.writeString(dateTo);
        dest.writeString(id);
    }
}
