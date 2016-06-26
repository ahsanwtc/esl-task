package me.aboutjaved.ESLGaming;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jsan on 6/25/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Team {

    @JsonProperty("id")
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                '}';
    }
}
