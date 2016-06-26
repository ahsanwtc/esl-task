package me.aboutjaved.ESLGaming;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by jsan on 6/25/2016.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Cup {

    @JsonProperty("ranking")
    private List<Ranking> ranking;

    public List<Ranking> ranking() {
        return ranking;
    }
    @JsonProperty("type")
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Cup{" +
                "ranking=" + ranking +
                ", type='" + type + '\'' +
                '}';
    }
}
