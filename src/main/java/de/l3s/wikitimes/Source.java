package de.l3s.wikitimes;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Source {
    private final long id;
    /** URL of the online news article */
    private final String url;
    /** e.g. article */
    private final String type;
    /** e.g. cnn, bbc, twitter, youtube */
    private final String source;

    @JsonCreator
    public Source(@JsonProperty("id") long id, @JsonProperty("url") String url,
            @JsonProperty("type") String type, @JsonProperty("sourceName") String source) {
        this.id = id;
        this.url = url;
        this.type = type;
        this.source = source;
    }

    public long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getType() {
        return type;
    }

    public String getSource() {
        return source;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Source)) {
            return false;
        }
        return id == ((Source) obj).id;
    }

    @Override
    public String toString() {
        return "Source[" + url + "]";
    }
}
