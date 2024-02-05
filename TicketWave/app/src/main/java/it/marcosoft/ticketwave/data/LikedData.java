package it.marcosoft.ticketwave.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Represents data associated with a liked event.
 * Implements Parcelable for easy transfer between Android components.
 */
public class LikedData implements Parcelable {
    // Fields representing information about the liked event
    private String eventId;           // Unique identifier for the liked event
    private String userId;            // User ID associated with the liked event
    private String eventTitle;        // Title or name of the liked event
    private String eventLocation;     // Location of the liked event
    private String eventDate;         // Date of the liked event
    private String eventDescription;  // Description of the liked event
    private String eventImageUrl;     // URL of the image associated with the liked event

    /**
     * Constructor to create a LikedData object.
     *
     * @param eventId          Unique identifier for the liked event.
     * @param userId           User ID associated with the liked event.
     * @param eventTitle       Title or name of the liked event.
     * @param eventLocation    Location of the liked event.
     * @param eventDate        Date of the liked event.
     * @param eventDescription Description of the liked event.
     * @param eventImageUrl    URL of the image associated with the liked event.
     */
    public LikedData(String eventId, String userId, String eventTitle, String eventLocation, String eventDate, String eventDescription, String eventImageUrl) {
        this.eventId = eventId;
        this.userId = userId;
        this.eventTitle = eventTitle;
        this.eventLocation = eventLocation;
        this.eventDate = eventDate;
        this.eventDescription = eventDescription;
        this.eventImageUrl = eventImageUrl;
    }

    /**
     * Getter for the eventId field.
     *
     * @return Unique identifier for the liked event.
     */
    public String getEventId() {
        return eventId;
    }

    /**
     * Getter for the userId field.
     *
     * @return User ID associated with the liked event.
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Getter for the eventTitle field.
     *
     * @return Title or name of the liked event.
     */
    public String getEventTitle() {
        return eventTitle;
    }

    /**
     * Getter for the eventLocation field.
     *
     * @return Location of the liked event.
     */
    public String getEventLocation() {
        return eventLocation;
    }

    /**
     * Getter for the eventDate field.
     *
     * @return Date of the liked event.
     */
    public String getEventDate() {
        return eventDate;
    }

    /**
     * Getter for the eventDescription field.
     *
     * @return Description of the liked event.
     */
    public String getEventDescription() {
        return eventDescription;
    }

    /**
     * Getter for the eventImageUrl field.
     *
     * @return URL of the image associated with the liked event.
     */
    public String getEventImageUrl() {
        return eventImageUrl;
    }

    /**
     * Parcelable constructor to recreate a LikedData object from a Parcel.
     *
     * @param in The Parcel containing the serialized LikedData.
     */
    protected LikedData(Parcel in) {
        eventId = in.readString();
        userId = in.readString();
        eventTitle = in.readString();
        eventLocation = in.readString();
        eventDate = in.readString();
        eventDescription = in.readString();
        eventImageUrl = in.readString();
    }

    /**
     * Creator for Parcelable implementation.
     */
    public static final Creator<LikedData> CREATOR = new Creator<LikedData>() {
        @Override
        public LikedData createFromParcel(Parcel in) {
            return new LikedData(in);
        }

        @Override
        public LikedData[] newArray(int size) {
            return new LikedData[size];
        }
    };

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
        dest.writeString(eventId);
        dest.writeString(userId);
        dest.writeString(eventTitle);
        dest.writeString(eventLocation);
        dest.writeString(eventDate);
        dest.writeString(eventDescription);
        dest.writeString(eventImageUrl);
    }
}
