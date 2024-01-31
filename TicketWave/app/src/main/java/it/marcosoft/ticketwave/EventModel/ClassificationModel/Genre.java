package it.marcosoft.ticketwave.EventModel.ClassificationModel;

/**
 * Represents a genre in the classification of an event.
 */
public class Genre {
    private String id = "";
    private String name = "";

    /**
     * Gets the ID of the genre.
     *
     * @return The ID of the genre.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the ID of the genre.
     *
     * @param id The ID to set for the genre.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the name of the genre.
     *
     * @return The name of the genre.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the genre.
     *
     * @param name The name to set for the genre.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns a string representation of the Genre object.
     *
     * @return A string representation of the Genre object.
     */
    @Override
    public String toString() {
        return "Genre{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
