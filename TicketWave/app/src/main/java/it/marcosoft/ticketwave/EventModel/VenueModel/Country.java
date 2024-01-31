package it.marcosoft.ticketwave.EventModel.VenueModel;

/**
 * Represents the country information associated with a venue.
 */
public class Country {
    private String name;
    private String countryCode;

    /**
     * Gets the name of the country.
     *
     * @return The name of the country.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the country.
     *
     * @param name The name to set for the country.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the country code.
     *
     * @return The country code.
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * Sets the country code.
     *
     * @param countryCode The country code to set.
     */
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    /**
     * Returns a string representation of the Country object.
     *
     * @return A string representation of the Country object.
     */
    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", countryCode='" + countryCode + '\'' +
                '}';
    }
}
