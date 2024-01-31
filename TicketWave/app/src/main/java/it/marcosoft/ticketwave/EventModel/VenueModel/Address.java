package it.marcosoft.ticketwave.EventModel.VenueModel;

/**
 * Represents the address information associated with a venue.
 */
public class Address {
    private String line1;
    private String line2;

    /**
     * Gets the first line of the address.
     *
     * @return The first line of the address.
     */
    public String getLine1() {
        return line1;
    }

    /**
     * Sets the first line of the address.
     *
     * @param line1 The first line to set for the address.
     */
    public void setLine1(String line1) {
        this.line1 = line1;
    }

    /**
     * Gets the second line of the address.
     *
     * @return The second line of the address.
     */
    public String getLine2() {
        return line2;
    }

    /**
     * Sets the second line of the address.
     *
     * @param line2 The second line to set for the address.
     */
    public void setLine2(String line2) {
        this.line2 = line2;
    }

    /**
     * Returns a string representation of the Address object.
     *
     * @return A string representation of the Address object.
     */
    @Override
    public String toString() {
        return "Address{" +
                "line1='" + line1 + '\'' +
                ", line2='" + line2 + '\'' +
                '}';
    }
}
