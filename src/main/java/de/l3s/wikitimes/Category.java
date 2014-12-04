package de.l3s.wikitimes;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Category of an event, e.g. “Armed conflicts and attacks” or “Disasters and
 * accidents”.
 */
public class Category {
    private final long id;
    private final String name;

    @JsonCreator
    public Category(@JsonProperty("id") long id, @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }

    /** Internal ID of the category. */
    public long getId() {
        return id;
    }

    /**
     * Name of the category, e.g. “Armed conflicts and attacks” or “Disasters
     * and accidents”.
     */
    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Category)) {
            return false;
        }
        return id == ((Category) obj).id;
    }

    @Override
    public String toString() {
        return "Category[" + name + "]";
    }
}
