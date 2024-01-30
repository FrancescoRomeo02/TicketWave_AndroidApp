package it.marcosoft.ticketwave.EventModel.VenueModel;

/**
 * Represents the state information associated with a venue.
 */
public class State {
    private String name;
    private String stateCode;

    /**
     * Gets the name of the state.
     *
     * @return The name of the state.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the state.
     *
     * @param name The name to set for the state.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the state code.
     *
     * @return The state code.
     */
    public String getStateCode() {
        return stateCode;
    }

    /**
     * Sets the state code.
     *
     * @param stateCode The state code to set.
     */
    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    /**
     * Returns a string representation of the State object.
     *
     * @return A string representation of the State object.
     */
    @Override
    public String toString() {
        return "State{" +
                "name='" + name + '\'' +
                ", stateCode='" + stateCode + '\'' +
                '}';
    }
}
