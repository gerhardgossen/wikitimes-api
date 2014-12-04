package de.l3s.wikitimes;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/** External resources (i.e. online news articles) for an event. */
public class Source {
    private final long id;
    private final String url;
    private final String type;
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

    /** URL of the online news article */
    public String getUrl() {
        return url;
    }

    /** e.g. article */
    public String getType() {
        return type;
    }

    /** e.g. cnn, bbc, twitter, youtube */
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
