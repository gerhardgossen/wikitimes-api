package de.l3s.wikitimes;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Story {
    /** WikiTimes internal ID of the story */
    private final long id;
    /** the name of the story */
    private final String name;
    /** the URL of the wikipedia page of this story */
    private final String wikipediaUrl;
    /** the date of the first event of the story timeline */
    private final LocalDate startDate;
    /** the date of the last event of the story timeline */
    private final LocalDate endDate;
    /** the category of the story (if exist, otherwise null) */
    private final Optional<String> category;
    /** a chronologically ordered list of events that constitute the story timeline */
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

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getWikipediaUrl() {
        return wikipediaUrl;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Optional<String> getCategory() {
        return category;
    }

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
