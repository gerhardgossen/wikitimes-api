package de.l3s.wikitimes;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/** A larger news story containing multiple sub-events. */
public class Story {
    private final long id;
    private final String name;
    private final String wikipediaUrl;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final Optional<String> category;
    private final List<Event> events;

    @JsonCreator
    public Story(@JsonProperty("id") long id, @JsonProperty("name") String name,
            @JsonProperty("wikipediaUrl") String wikipediaUrl,
            @JsonProperty("startDate") LocalDate startDate,
            @JsonProperty("endDate") LocalDate endDate,
            @JsonProperty("category") Optional<String> category,
            @JsonProperty("event") List<Event> events) {
        this.id = id;
        this.name = name;
        this.wikipediaUrl = wikipediaUrl;
        this.startDate = startDate;
        this.endDate = endDate;
        this.category = category;
        this.events = events;
    }

    /** Internal ID of the story */
    public long getId() {
        return id;
    }

    /** Name of the story */
    public String getName() {
        return name;
    }

    /** URL of the Wikipedia page about this story */
    public String getWikipediaUrl() {
        return wikipediaUrl;
    }

    /** Date of the first event of the story timeline */
    public LocalDate getStartDate() {
        return startDate;
    }

    /** Date of the last event of the story timeline */
    public LocalDate getEndDate() {
        return endDate;
    }

    /** the category of the story (if it exists) */
    public Optional<String> getCategory() {
        return category;
    }

    /**
     * Chronologically ordered list of events that constitute the story
     * timeline.
     */
    public List<Event> getEvents() {
        return events;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Story)) {
            return false;
        }
        return id == ((Story) obj).id;
    }

    @Override
    public String toString() {
        return "Story[" + name + "]";
    }
}
