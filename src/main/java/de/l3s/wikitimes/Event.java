package de.l3s.wikitimes;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Event {
    /** WikiTimes internal ID of the event*/
    private final long id      ;
    /** the date of the event */
    private final LocalDate date;
    /** the textual summary of the event */
    private final String description;
    /** the news story this event belongs to (if exists, otherwise null) in the form of a compact representation of the STORY object, i.e. without timeline */
    private final Optional<Story> story;
    /** the category of this event (if exist, otherwise null) */
    private final Optional<Category> category;
    /** a list of entities involved in the event (if exist otherwise null or empty list) in the form of an ENTITY object: id, name, wikipedial URL */
    private final Collection<Entity> entities;
        /** a list of external resources (i.e. online news articles) (if exist otherwise null or empty list) in the form of a SOURCE object: id, url (to the online news article), type (i.e. article) and source (i.e. cnn, bbc, twitter, youtube etc).*/
    private final Collection<Source> sources;
    /** TODO */
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

    public long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public Optional<Story> getStory() {
        return story;
    }

    public Optional<Category> getCategory() {
        return category;
    }

    public Collection<Entity> getEntities() {
        return entities;
    }

    public Collection<Source> getSources() {
        return sources;
    }

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
