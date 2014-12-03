package de.l3s.wikitimes;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Entity {
    private final long id;
    private final String name;
    private final String wikipediaUrl;

    @JsonCreator
    public Entity(@JsonProperty("id") long id, @JsonProperty("name") String name,
            @JsonProperty("wikiURL") String wikipediaUrl) {
        this.id = id;
        this.name = name;
        this.wikipediaUrl = wikipediaUrl;
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

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Entity)) {
            return false;
        }
        return id == ((Entity) obj).id;
    }

    @Override
    public String toString() {
        return "Entity[" + name + "]";
    }
}
