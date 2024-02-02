package it.marcosoft.ticketwave.EventModel;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import it.marcosoft.ticketwave.EventModel.ClassificationModel.*;
import it.marcosoft.ticketwave.EventModel.VenueModel.*;

/**
 * Represents an event with various attributes such as name, type, ID, date, venue, etc.
 */
public class Event {

    // Event attributes
    private String name;
    private String type;
    private String id;
    private Boolean ageRestrictions;
    private String urlInfo;
    private List<Image> images;
    private String date;
    private List<Classification> classifications;
    private Venue venue;

    /**
     * Constructor that receives a JSON object and initializes the Event instance.
     *
     * @param eventObj The JSON object containing event information.
     * @throws JSONException If there is an issue parsing the JSON.
     */
    public Event(JSONObject eventObj) throws JSONException {
        // Extract basic event information
        setName(eventObj.optString("name", null));
        setType(eventObj.optString("type", null));
        setUrlInfo(eventObj.optString("url", null));
        setId(eventObj.optString("id", null));
        setAgeRestrictions(eventObj.optBoolean("ageRestrictions", false));

        // Extract and format the event date
        JSONObject dateObj = eventObj.optJSONObject("dates");
        if (dateObj != null) {
            JSONObject startObj = dateObj.optJSONObject("start");
            if (startObj != null) {
                LocalDate localDate = LocalDate.parse(startObj.optString("localDate", ""));
                // Formatter for date parsing and formatting
                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
                setDate(localDate.format(myFormatObj));
            }
        }

        // Extract and build the list of images associated with the event
        JSONArray imagesArray = eventObj.optJSONArray("images");
        images = new ArrayList<>();
        if (imagesArray != null) {
            for (int i = 0; i < imagesArray.length(); i++) {
                JSONObject imageObj = imagesArray.getJSONObject(i);
                Image image = new Image();
                image.setUrlImage(imageObj.optString("url", null));
                image.setWidth(imageObj.optInt("width", 0));
                image.setHeight(imageObj.optInt("height", 0));
                images.add(image);
            }
        }

        // Extract and build the list of event classifications
        JSONArray classificationsArray = eventObj.optJSONArray("classifications");
        classifications = new ArrayList<>();
        if (classificationsArray != null) {
            for (int i = 0; i < classificationsArray.length(); i++) {
                JSONObject classificationObj = classificationsArray.getJSONObject(i);

                // Extract information about segments, genres, and subgenres
                Classification classification = buildClassification(classificationObj);
                classifications.add(classification);
            }
        }

        // Extract information about the venue, if available
        JSONObject embeddedObj = eventObj.optJSONObject("_embedded");
        if (embeddedObj != null) {
            JSONArray venuesArray = embeddedObj.optJSONArray("venues");
            if (venuesArray != null && venuesArray.length() > 0) {
                JSONObject venueObj = venuesArray.getJSONObject(0);
                Venue venue1 = buildVenue(venueObj);

                // Set the event's venue
                setVenue(venue1);
            }
        }
    }

    private Classification buildClassification(JSONObject classificationObj) {
        Classification classification = new Classification();

        // Extract information about segments
        JSONObject segmentObj = classificationObj.optJSONObject("segment");
        if (segmentObj != null) {
            classification.setSegment(buildSegment(segmentObj));
        }

        // Extract information about genres
        JSONObject genreObj = classificationObj.optJSONObject("genre");
        if (genreObj != null) {
            classification.setGenre(buildGenre(genreObj));
        }

        // Extract information about subgenres
        JSONObject subGenreObj = classificationObj.optJSONObject("subGenre");
        if (subGenreObj != null) {
            classification.setSubGenre(buildSubGenre(subGenreObj));
        }

        return classification;
    }

    private Segment buildSegment(JSONObject segmentObj) {
        Segment segment = new Segment();
        segment.setName(segmentObj.optString("name", null));
        segment.setId(segmentObj.optString("id", null));
        return segment;
    }

    private Genre buildGenre(JSONObject genreObj) {
        Genre genre = new Genre();
        genre.setName(genreObj.optString("name", null));
        genre.setId(genreObj.optString("id", null));
        return genre;
    }

    private SubGenre buildSubGenre(JSONObject subGenreObj) {
        SubGenre subGenre = new SubGenre();
        subGenre.setName(subGenreObj.optString("name", null));
        subGenre.setId(subGenreObj.optString("id", null));
        return subGenre;
    }

    private Venue buildVenue(JSONObject venueObj) {
        Venue venue1 = new Venue();
        venue1.setName(venueObj.optString("name", null));
        venue1.setId(venueObj.optString("id", null));
        venue1.setPostalCode(venueObj.optString("postalCode", null));

        // Extract information about the city
        City city = buildCity(venueObj.optJSONObject("city"));
        venue1.setCity(city);

        // Extract information about the state
        State state = buildState(venueObj.optJSONObject("state"));
        venue1.setState(state);

        // Extract information about the country
        Country country = buildCountry(venueObj.optJSONObject("country"));
        venue1.setCountry(country);

        // Extract information about the address
        Address address = buildAddress(venueObj.optJSONObject("address"));
        venue1.setAddress(address);

        // Extract information about the location
        Location location = buildLocation(venueObj.optJSONObject("location"));
        venue1.setLocation(location);

        return venue1;
    }

    private City buildCity(JSONObject cityObj) {
        City city = new City();
        city.setName(cityObj.optString("name", null));
        return city;
    }

    private State buildState(JSONObject stateObj) {
        State state = new State();
        state.setName(stateObj.optString("name", null));
        state.setStateCode(stateObj.optString("stateCode", null));
        return state;
    }

    private Country buildCountry(JSONObject countryObj) {
        Country country = new Country();
        country.setName(countryObj.optString("name", null));
        country.setCountryCode(countryObj.optString("countryCode", null));
        return country;
    }

    private Address buildAddress(JSONObject addressObj) {
        Address address = new Address();
        address.setLine1(addressObj.optString("line1", null));
        address.setLine2(addressObj.optString("line2", null));
        return address;
    }

    private Location buildLocation(JSONObject locationObj) {
        Location location = new Location();
        location.setLongitude(locationObj.optLong("longitude", 0));
        location.setLatitude(locationObj.optLong("latitude", 0));
        return location;
    }

    // Getter and setter methods to access and modify private fields

    /**
     * Gets the name of the event.
     *
     * @return The name of the event.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the event.
     *
     * @param name The name to set for the event.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the type of the event.
     *
     * @return The type of the event.
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of the event.
     *
     * @param type The type to set for the event.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the ID of the event.
     *
     * @return The ID of the event.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the ID of the event.
     *
     * @param id The ID to set for the event.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the age restrictions status for the event.
     *
     * @return True if there are age restrictions, false otherwise.
     */
    public Boolean getAgeRestrictions() {
        return ageRestrictions;
    }

    /**
     * Sets the age restrictions status for the event.
     *
     * @param ageRestrictions True if there are age restrictions, false otherwise.
     */
    public void setAgeRestrictions(Boolean ageRestrictions) {
        this.ageRestrictions = ageRestrictions;
    }

    /**
     * Gets the URL information for the event.
     *
     * @return The URL information for the event.
     */
    public String getUrlInfo() {
        return urlInfo;
    }

    /**
     * Sets the URL information for the event.
     *
     * @param urlInfo The URL information to set for the event.
     */
    public void setUrlInfo(String urlInfo) {
        this.urlInfo = urlInfo;
    }

    /**
     * Gets the list of images associated with the event.
     *
     * @return The list of images associated with the event.
     */
    public List<Image> getImages() {
        return images;
    }

    /**
     * Sets the list of images associated with the event.
     *
     * @param images The list of images to set for the event.
     */
    public void setImages(List<Image> images) {
        this.images = images;
    }

    /**
     * Gets the date of the event.
     *
     * @return The date of the event.
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the date of the event.
     *
     * @param date The date to set for the event.
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Gets the list of classifications for the event.
     *
     * @return The list of classifications for the event.
     */
    public List<Classification> getClassifications() {
        return classifications;
    }

    /**
     * Sets the list of classifications for the event.
     *
     * @param classifications The list of classifications to set for the event.
     */
    public void setClassifications(List<Classification> classifications) {
        this.classifications = classifications;
    }

    /**
     * Gets the venue information for the event.
     *
     * @return The venue information for the event.
     */
    public Venue getVenue() {
        return venue;
    }

    /**
     * Sets the venue information for the event.
     *
     * @param venue The venue information to set for the event.
     */
    public void setVenue(Venue venue) {
        this.venue = venue;
    }


    /**
     * Returns a string representation of the Event object.
     *
     * @return A string representation of the Event object.
     */
    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", id='" + id + '\'' +
                ", ageRestrictions='" + ageRestrictions + '\'' +
                ", urlInfo='" + urlInfo + '\'' +
                ", images=" + images +
                ", date='" + date + '\'' +
                ", classifications=" + classifications +
                ", venue=" + venue +
                '}';
    }
}
