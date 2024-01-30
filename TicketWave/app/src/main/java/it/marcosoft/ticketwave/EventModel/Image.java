package it.marcosoft.ticketwave.EventModel;

/**
 * Represents an image with information such as URL, width, and height.
 */
public class Image {
    private String urlImage = "";
    private int width = 0;
    private int height = 0;

    /**
     * Gets the URL of the image.
     *
     * @return The URL of the image.
     */
    public String getUrlImage() {
        return urlImage;
    }

    /**
     * Sets the URL of the image.
     *
     * @param urlImage The URL to set for the image.
     */
    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    /**
     * Gets the width of the image.
     *
     * @return The width of the image.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Sets the width of the image.
     *
     * @param width The width to set for the image.
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Gets the height of the image.
     *
     * @return The height of the image.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets the height of the image.
     *
     * @param height The height to set for the image.
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Returns a string representation of the Image object.
     *
     * @return A string representation of the Image object.
     */
    @Override
    public String toString() {
        return "Image{" +
                "urlImage='" + urlImage + '\'' +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
