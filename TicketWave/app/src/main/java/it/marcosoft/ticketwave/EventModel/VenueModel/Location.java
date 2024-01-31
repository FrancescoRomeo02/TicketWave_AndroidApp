package it.marcosoft.ticketwave.EventModel.VenueModel;

/**
 * Represents the geographical location information associated with a venue.
 */
public class Location {
    private Long longitude;
    private Long latitude;

    /**
     * Gets the longitude of the location.
     *
     * @return The longitude of the location.
     */
    public Long getLongitude() {
        return longitude;
    }

    /**
     * Sets the longitude of the location.
     *
     * @param longitude The longitude to set for the location.
     */
    public void setLongitude(Long longitude) {
        this.longitude = longitude;
    }

    /**
     * Gets the latitude of the location.
     *
     * @return The latitude of the location.
     */
    public Long getLatitude() {
        return latitude;
    }

    /**
     * Sets the latitude of the location.
     *
     * @param latitude The latitude to set for the location.
     */
    public void setLatitude(Long latitude) {
        this.latitude = latitude;
    }

    /**
     * Returns a string representation of the Location object.
     *
     * @return A string representation of the Location object.
     */
    @Override
    public String toString() {
        return "Location{" +
                "longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}
