package it.marcosoft.ticketwave.EventModel.ClassificationModel;

/**
 * Represents a segment in the classification of an event.
 */
public class Segment {
    private String id = "";
    private String name = "";

    /**
     * Gets the ID of the segment.
     *
     * @return The ID of the segment.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the ID of the segment.
     *
     * @param id The ID to set for the segment.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the name of the segment.
     *
     * @return The name of the segment.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the segment.
     *
     * @param name The name to set for the segment.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns a string representation of the Segment object.
     *
     * @return A string representation of the Segment object.
     */
    @Override
    public String toString() {
        return "Segment{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}