package de.l3s.wikitimes.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.Collection;

import org.apache.commons.io.IOUtils;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.thoughtworks.webstub.StubServerFacade;
import com.thoughtworks.webstub.dsl.HttpDsl;
import com.thoughtworks.webstub.dsl.builders.ResponseBuilder;

import de.l3s.wikitimes.Entity;
import de.l3s.wikitimes.Event;
import de.l3s.wikitimes.Story;

import static com.thoughtworks.webstub.dsl.builders.ResponseBuilder.response;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class ApiTest {
    private static HttpDsl stubServer;
    private static StubServerFacade server;
    private static WikiTimes api;

    @BeforeClass
    public static void setup() {
        server = StubServerFacade.newServer(9099);
        server.start();
        stubServer = server.withContext("/webresources/WebService");

        api = new WikiTimes("http://localhost:9099/webresources/WebService");
    }

    @Before
    public void setupTest() {
        stubServer.reset();
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testStoriesWithoutTimeline() throws IOException {
        stubServer.get("/getStoriesWithoutTimeline/json").returns(
            jsonResource("/storiesWithoutTimeline.json"));
        Collection<Story> storiesWithoutTimeline = api.getStoriesWithoutTimeline();
        assertThat(storiesWithoutTimeline, hasSize(879));
        for (Story story : storiesWithoutTimeline) {
            assertThat(story.getId(), is(greaterThan(0L)));
            assertThat(story.getName(), is(notNullValue()));
            assertThat(story.getWikipediaUrl(), is(notNullValue()));
            assertThat(story.getEvents(), is(anyOf(nullValue(), empty())));
        }
    }

    @Test
    public void testStories() throws IOException {
        stubServer.get("/getStories/json").returns(jsonResource("/stories.json"));
        Collection<Story> stories = api.getStories();
        assertThat(stories, hasSize(879));
        for (Story story : stories) {
            assertThat(story.getId(), is(greaterThan(0L)));
            assertThat(story.getName(), is(notNullValue()));
            assertThat(story.getWikipediaUrl(), is(notNullValue()));
            assertThat(story.getEvents(), is(not(emptyCollectionOf(Event.class))));
        }
    }

    @Test
    public void testGetEntities() throws Exception {
        stubServer.get("/getEntities/json").returns(jsonResource("/entities.json"));
        Collection<Entity> entities = api.getEntities();
        assertThat(entities, hasSize(50375));
        for (Entity entity : entities) {
            assertValidEntity(entity);
        }
    }

    private void assertValidEntity(Entity entity) {
        assertThat(entity.getId(), is(greaterThan(0L)));
        assertThat(entity.getName(), is(notNullValue()));
        assertThat(entity.getWikipediaUrl(), is(notNullValue()));
    }

    @Test
    public void testGetEventsInInterval() throws Exception {
        //        <resource path="/getEvents/json/{from}/{to}">
        stubServer.get("/getEvents/json/2001-01-01/2001-02-01").returns(
            jsonResource("/events-2001-01.json"));
        Collection<Event> events = api.getEvents(LocalDate.of(2001, 1, 1), LocalDate.of(2001, 2, 1));
        assertThat(events, hasSize(18));
        for (Event event : events) {
            assertThat(event.getId(), is(greaterThan(0L)));
            assertThat(event.getDate(), is(notNullValue()));
            assertThat(event.getDescription(), is(notNullValue()));
        }
    }

    @Test
    public void testGetStory() throws Exception {
        //        <resource path="/getStory/json/{storyId}">
        stubServer.get("/getStory/json/99").returns(jsonResource("/story-99.json"));
        Story story = api.getStory(99L);
        assertThat(story.getId(), is(99L));
        assertThat(story.getName(), is("Taliban insurgency"));
        assertThat(story.getWikipediaUrl(), is("http://en.wikipedia.org/wiki/Taliban_insurgency"));
        assertThat(story.getStartDate(), is(LocalDate.of(2006, 8, 20)));
        assertThat(story.getEndDate(), is(LocalDate.of(2014, 11, 10)));
        assertThat(story.getEvents(), hasSize(35));
    }

    @Test
    public void getEntitiesInInterval() throws Exception {
        //        <resource path="/getEntities/json/{from}/{to}">
        stubServer.get("/getEntities/json/2001-01-01/2001-02-01").returns(
            jsonResource("/entities-2001-01.json"));

        Collection<Entity> entities = api.getEntities(LocalDate.of(2001, 1, 1),
            LocalDate.of(2001, 2, 1));
        assertThat(entities, hasSize(55));
        for (Entity entity : entities) {
            assertValidEntity(entity);
        }
    }

    @AfterClass
    public static void cleanup() {
        server.stop();
    }

    private static ResponseBuilder jsonResource(String resourceName) throws IOException {
        return response(200)
                .withContent(readResource(resourceName))
                .withHeader("Content-Type", "application/json; charset=utf-8");
    }

    private static String readResource(String name) throws IOException {
        try (InputStream is = ApiTest.class.getResourceAsStream(name);
                InputStreamReader reader = new InputStreamReader(is, UTF_8)) {
            return IOUtils.toString(reader);
        }
    }


}
