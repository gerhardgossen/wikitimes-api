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


public class WikiTimes {
    private static final String URL = "http://wikitimes.l3s.de/webresources/WebService";
    private final WebTarget target;

    public WikiTimes() {
        this(URL);
    }

    WikiTimes(String baseUrl) {
        target = ClientBuilder.newClient()
            .register(ObjectMapperProvider.class)
            .register(JacksonFeature.class)
            .target(baseUrl);
    }

    public Collection<Story> getStories() {
        return getCollection("getStories", Story.class);
    }

    public Collection<Entity> getEntities() {
        return getCollection("getEntities", Entity.class);
    }

    public Collection<Entity> getEntities(LocalDate start, LocalDate end) {
        return getCollection("getEntities", start, end, Entity.class);
    }

    public Collection<Story> getStoriesWithoutTimeline() {
        return getCollection("getStoriesWithoutTimeline", Story.class);
    }

    public Collection<Event> getEvents(LocalDate start, LocalDate end) {
        return getCollection("getEvents", start, end, Event.class);
    }

    public Story getStory(long storyId) {
        return target.path("getStory/json/{story}")
            .resolveTemplate("story", storyId)
            .request(MediaType.APPLICATION_JSON_TYPE)
            .get(Story.class);
    }

    private <T> Collection<T> getCollection(String resourceName, LocalDate start, LocalDate end,
            Class<T> itemType) {
        WebTarget webTarget = target.path(resourceName + "/json/{start}/{end}")
            .resolveTemplate("start", start)
            .resolveTemplate("end", end);
        return getCollection(webTarget, itemType);
    }

    private <T> Collection<T> getCollection(String resourceName, final Class<T> itemType) {
        return getCollection(target.path(resourceName + "/json"), itemType);
    }

    private <T> Collection<T> getCollection(WebTarget webTarget, final Class<T> itemType) {
        return webTarget.request(MediaType.APPLICATION_JSON_TYPE).get(createType(itemType));
    }

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
