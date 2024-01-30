package it.marcosoft.ticketwave.EventModel;

import it.marcosoft.ticketwave.EventModel.VenueModel.*;

/**
 * Represents a venue with information such as name, ID, address, city, country, location, and state.
 */
public class Venue {
    private String name;
    private String id;
    private String postalCode;
    private Address address;
    private City city;
    private Country country;
    private Location location;
    private State state;

    /**
     * Gets the name of the venue.
     *
     * @return The name of the venue.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the venue.
     *
     * @param name The name to set for the venue.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the ID of the venue.
     *
     * @return The ID of the venue.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the ID of the venue.
     *
     * @param id The ID to set for the venue.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the postal code of the venue.
     *
     * @return The postal code of the venue.
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Sets the postal code of the venue.
     *
     * @param postalCode The postal code to set for the venue.
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Gets the address of the venue.
     *
     * @return The address of the venue.
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Sets the address of the venue.
     *
     * @param address The address to set for the venue.
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * Gets the city of the venue.
     *
     * @return The city of the venue.
     */
    public City getCity() {
        return city;
    }

    /**
     * Sets the city of the venue.
     *
     * @param city The city to set for the venue.
     */
    public void setCity(City city) {
        this.city = city;
    }

    /**
     * Gets the country of the venue.
     *
     * @return The country of the venue.
     */
    public Country getCountry() {
        return country;
    }

    /**
     * Sets the country of the venue.
     *
     * @param country The country to set for the venue.
     */
    public void setCountry(Country country) {
        this.country = country;
    }

    /**
     * Gets the location of the venue.
     *
     * @return The location of the venue.
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Sets the location of the venue.
     *
     * @param location The location to set for the venue.
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Gets the state of the venue.
     *
     * @return The state of the venue.
     */
    public State getState() {
        return state;
    }

    /**
     * Sets the state of the venue.
     *
     * @param state The state to set for the venue.
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * Returns a string representation of the venue.
     *
     * @return A string representation of the venue.
     */
    @Override
    public String toString() {
        return "Venue{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", address=" + address +
                ", city=" + city +
                ", country=" + country +
                ", location=" + location +
                ", state=" + state +
                '}';
    }
}
