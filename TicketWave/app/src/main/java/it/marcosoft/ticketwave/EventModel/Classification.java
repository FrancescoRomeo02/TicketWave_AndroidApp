package it.marcosoft.ticketwave.EventModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    /**
     * Returns a prettier string representation of the Classification object.
     *
     * @return A string representation of the Classification object.
     */
    public String toStringPretty() {
        Set<String> uniqueValues = new HashSet<>();
        List<String> values = new ArrayList<>();

        if (!"Other".equals(segment.getName()) && uniqueValues.add(segment.getName())) {
            values.add(segment.getName());
        }

        if (!"Other".equals(genre.getName()) && uniqueValues.add(genre.getName())) {
            values.add(genre.getName());
        }

        if (!"Other".equals(subGenre.getName()) && uniqueValues.add(subGenre.getName())) {
            values.add(subGenre.getName());
        }

        return String.join(", ", values) + ".";
    }



}
