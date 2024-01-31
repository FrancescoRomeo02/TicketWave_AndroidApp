package it.marcosoft.ticketwave.EventModel.VenueModel;

/**
 * Represents the city information associated with a venue.
 */
public class City {
    private String name;

    /**
     * Gets the name of the city.
     *
     * @return The name of the city.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the city.
     *
     * @param name The name to set for the city.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns a string representation of the City object.
     *
     * @return A string representation of the City object.
     */
    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                '}';
    }
}