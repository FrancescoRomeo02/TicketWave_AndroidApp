package it.marcosoft.ticketwave.EventModel.ClassificationModel;

/**
 * Represents a subgenre in the classification of an event.
 */
public class SubGenre {
    private String id = "";
    private String name = "";

    /**
     * Gets the ID of the subgenre.
     *
     * @return The ID of the subgenre.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the ID of the subgenre.
     *
     * @param id The ID to set for the subgenre.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the name of the subgenre.
     *
     * @return The name of the subgenre.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the subgenre.
     *
     * @param name The name to set for the subgenre.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns a string representation of the SubGenre object.
     *
     * @return A string representation of the SubGenre object.
     */
    @Override
    public String toString() {
        return "SubGenre{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}