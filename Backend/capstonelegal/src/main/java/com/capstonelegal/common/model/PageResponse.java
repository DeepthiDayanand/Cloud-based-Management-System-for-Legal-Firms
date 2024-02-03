package com.capstonelegal.common.model;

/**
 * Represents a paginated response.
 * <p>
 * This class is designed to handle paginated responses, which are common in APIs that return a large number of objects.
 * These APIs return the objects in "pages" rather than all at once to reduce network load and make the UI more responsive.
 * This class encapsulates a "page" of objects (of type T) and the total number of these objects.
 *
 * @param <T> The type of the objects contained in the page.
 */


import java.util.List;

public class PageResponse<T> {

    private List<T> content;  // The list of objects in the current page
    private long totalElements;  // The total number of objects across all pages

    /**
     * Constructs a new PageResponse with the provided content and total elements.
     *
     * @param content       The list of objects in the current page
     * @param totalElements The total number of objects across all pages
     */
    public PageResponse(List<T> content, long totalElements) {
        this.content = content;
        this.totalElements = totalElements;
    }

    /**
     * Returns the content of the current page.
     *
     * @return A list of objects in the current page
     */
    public List<T> getContent() {
        return content;
    }

    /**
     * Sets the content of the current page.
     *
     * @param content The list of objects to be included in the current page
     */
    public void setContent(List<T> content) {
        this.content = content;
    }

    /**
     * Returns the total number of objects across all pages.
     *
     * @return The total number of objects
     */
    public long getTotalElements() {
        return totalElements;
    }

    /**
     * Sets the total number of objects across all pages.
     *
     * @param totalElements The total number of objects to be set
     */
    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }
}
