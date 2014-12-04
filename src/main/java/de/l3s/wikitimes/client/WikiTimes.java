package de.l3s.wikitimes.client;

import java.time.LocalDate;
import java.util.Collection;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.jackson.JacksonFeature;

import de.l3s.wikitimes.Entity;
import de.l3s.wikitimes.Event;
import de.l3s.wikitimes.Story;

/**
 * Client interface to the WikiTimes REST API.
 *
 * All methods of this class call the API.
 *
 * @see <a href="http://wikitimes.l3s.de/">WikiTimes web site</a>
 */
public class WikiTimes {
    /** Base URL of the service */
    private static final String URL = "http://wikitimes.l3s.de/webresources/WebService";
    /** Entry point to the API */
    private final WebTarget target;

    /** Connect to the live API */
    public WikiTimes() {
        this(URL);
    }

    /** Connect to an alternate server (e.g. for test) */
    WikiTimes(String baseUrl) {
        target = ClientBuilder.newClient()
            .register(ObjectMapperProvider.class)
            .register(JacksonFeature.class)
            .target(baseUrl);
    }

    /**
     * Retrieve all stories with full details..
     *
     * WARNING: This returns a log of data!
     */
    public Collection<Story> getStories() {
        return getCollection("getStories", Story.class);
    }

    /**
     * Retrieve all entities.
     *
     * WARNING: This returns a log of data!
     */
    public Collection<Entity> getEntities() {
        return getCollection("getEntities", Entity.class);
    }

    /**
     * Retrieve all entities occurring during a time interval.
     *
     * @param start
     *            start of the time interval (inclusive)
     * @param end
     *            end of the time interval (inclusive)
     */
    public Collection<Entity> getEntities(LocalDate start, LocalDate end) {
        return getCollection("getEntities", start, end, Entity.class);
    }

    /**
     * Retrieve all stories with reduced detail
     *
     * @return stories where only the properties <tt>id</tt>, <tt>name</tt> and
     *         <tt>wikipediaUrl</tt> are set
     * @see #getStory(long)
     */
    public Collection<Story> getStoriesWithoutTimeline() {
        return getCollection("getStoriesWithoutTimeline", Story.class);
    }

    /**
     * Retrieve all events occurring during a time interval
     *
     * @param start
     *            start of the time interval (inclusive)
     * @param end
     *            end of the time interval (inclusive)
     */
    public Collection<Event> getEvents(LocalDate start, LocalDate end) {
        return getCollection("getEvents", start, end, Event.class);
    }

    /**
     * Retrieve all information about a story.
     *
     * @param storyId
     *            internal id of the story, e.g. from
     *            {@link #getStoriesWithoutTimeline()}
     */
    public Story getStory(long storyId) {
        return target.path("getStory/json/{story}")
            .resolveTemplate("story", storyId)
            .request(MediaType.APPLICATION_JSON_TYPE)
            .get(Story.class);
    }

    /**
     * Retrieve all items of a given type during a time interval.
     *
     * @param resourceName
     *            API resource name, e.g. 'getEvents'
     * @param itemType
     *            the type of an indiviudal item
     */
    private <T> Collection<T> getCollection(String resourceName, LocalDate start, LocalDate end,
            Class<T> itemType) {
        WebTarget webTarget = target.path(resourceName + "/json/{start}/{end}")
            .resolveTemplate("start", start)
            .resolveTemplate("end", end);
        return getCollection(webTarget, itemType);
    }

    /**
     * Retrieve all items of a given type.
     *
     * @param resourceName
     *            API resource name, e.g. 'getEvents'
     * @param itemType
     *            the type of an indiviudal item
     */
    private <T> Collection<T> getCollection(final String resourceName, final Class<T> itemType) {
        return getCollection(target.path(resourceName + "/json"), itemType);
    }

    /** Retrieve a collection using a GET request. */
    private <T> Collection<T> getCollection(final WebTarget webTarget, final Class<T> itemType) {
        return webTarget.request(MediaType.APPLICATION_JSON_TYPE).get(createType(itemType));
    }

    /** Create a type reference for a collection of <tt>itemType</tt> */
    private <T> GenericType<Collection<T>> createType(final Class<T> itemType) {
        return new GenericType<Collection<T>>(new ParameterizedCollectionType<T>(itemType));
    }


    public static void main(String[] args) {
        WikiTimes wikiTimes = new WikiTimes();
        System.out.printf("%5s: %-50s %s%n", "ID", "Name", "URL");
        Collection<Story> stories = wikiTimes.getStoriesWithoutTimeline();
        for (Story story : stories) {
            System.out.printf("%5d: %-50s %s%n", story.getId(), story.getName(),
                story.getWikipediaUrl());
        }
    }
}
