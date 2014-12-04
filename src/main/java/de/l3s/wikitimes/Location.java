package de.l3s.wikitimes;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/** TODO document */
public class Location {
    private final String id;
    private final String name;

    @JsonCreator
    public Location(@JsonProperty("id") String id, @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Location)) {
            return false;
        }
        return id.equals(((Location) obj).id);
    }

    @Override
    public String toString() {
        return "Location[" + name + "]";
    }

}
