package it.marcosoft.ticketwave.EventModel;

import it.marcosoft.ticketwave.EventModel.ClassificationModel.*;

/**
 * Represents the classification of an event, including information about segments, genres, and subgenres.
 */
public class Classification {
    private Segment segment;
    private Genre genre;
    private SubGenre subGenre;

    /**
     * Gets the segment information for the classification.
     *
     * @return The segment information for the classification.
     */
    public Segment getSegment() {
        return segment;
    }

    /**
     * Sets the segment information for the classification.
     *
     * @param segment The segment information to set for the classification.
     */
    public void setSegment(Segment segment) {
        this.segment = segment;
    }

    /**
     * Gets the genre information for the classification.
     *
     * @return The genre information for the classification.
     */
    public Genre getGenre() {
        return genre;
    }

    /**
     * Sets the genre information for the classification.
     *
     * @param genre The genre information to set for the classification.
     */
    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    /**
     * Gets the subgenre information for the classification.
     *
     * @return The subgenre information for the classification.
     */
    public SubGenre getSubGenre() {
        return subGenre;
    }

    /**
     * Sets the subgenre information for the classification.
     *
     * @param subGenre The subgenre information to set for the classification.
     */
    public void setSubGenre(SubGenre subGenre) {
        this.subGenre = subGenre;
    }

    /**
     * Returns a string representation of the Classification object.
     *
     * @return A string representation of the Classification object.
     */
    @Override
    public String toString() {
        return "Classification{" +
                "segment=" + segment +
                ", genre=" + genre +
                ", subGenre=" + subGenre +
                '}';
    }
}
