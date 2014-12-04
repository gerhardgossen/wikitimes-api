package de.l3s.wikitimes;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import de.l3s.wikitimes.client.WikiTimes;

/** An event. */
public class Event {
    private final long id      ;
    private final LocalDate date;
    private final String description;
    private final Optional<Story> story;
    private final Optional<Category> category;
    private final Collection<Entity> entities;
    private final Collection<Source> sources;
    private final Optional<Location> location;

    @JsonCreator
    public Event(@JsonProperty("id") long id, @JsonProperty("date") LocalDate date,
            @JsonProperty("description") String description,
            @JsonProperty("belongsToStory") Optional<Story> story,
            @JsonProperty("category") Optional<Category> category,
            @JsonProperty("entity") Collection<Entity> entities,
            @JsonProperty("source") Collection<Source> sources,
            @JsonProperty("location") Optional<Location> location) {
        this.id = id;
        this.date = date;
        this.description = description;
        this.story = story;
        this.category = category;
        this.entities = entities;
        this.sources = sources;
        this.location = location;
    }

    /** Internal ID of the event */
    public long getId() {
        return id;
    }

    /** Date of the event */
    public LocalDate getDate() {
        return date;
    }

    /** Textual summary of the event */
    public String getDescription() {
        return description;
    }

    /**
     * News story this event belongs to (if it exists).
     *
     * Only the compact representation of the STORY is returned, i.e. without
     * timeline.
     *
     * @see WikiTimes#getStory(long)
     */
    public Optional<Story> getStory() {
        return story;
    }

    /** Category of this event (if it exists) */
    public Optional<Category> getCategory() {
        return category;
    }

    /** Entities involved in the event. */
    public Collection<Entity> getEntities() {
        return entities;
    }

    /** External resources (i.e. online news articles) */
    public Collection<Source> getSources() {
        return sources;
    }

    /** TODO document */
    public Optional<Location> getLocation() {
        return location;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Event)) {
            return false;
        }
        return id == ((Event) obj).id;
    }

    @Override
    public String toString() {
        return "Event[" + description + "]";
    }
}
