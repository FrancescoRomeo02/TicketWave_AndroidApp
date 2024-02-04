package it.marcosoft.ticketwave.data;

import android.os.Parcel;
import android.os.Parcelable;

public class LikedData implements Parcelable {
    private String eventId;
    private String userId;
    private String eventTitle;
    private String eventLocation;
    private String eventDate;
    private String eventDescription;
    private String eventImageUrl;

    public LikedData(String eventId, String userId, String eventTitle, String eventLocation, String eventDate, String eventDescription, String eventImageUrl) {
        this.eventId = eventId;
        this.userId = userId;
        this.eventTitle = eventTitle;
        this.eventLocation = eventLocation;
        this.eventDate = eventDate;
        this.eventDescription = eventDescription;
        this.eventImageUrl = eventImageUrl;
    }

    protected LikedData(Parcel in) {
        eventId = in.readString();
        userId = in.readString();
        eventTitle = in.readString();
        eventLocation = in.readString();
        eventDate = in.readString();
        eventDescription = in.readString();
        eventImageUrl = in.readString();
    }

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

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getEventImageUrl() {
        return eventImageUrl;
    }

    public void setEventImageUrl(String eventImageUrl) {
        this.eventImageUrl = eventImageUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

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
