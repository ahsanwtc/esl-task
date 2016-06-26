package me.aboutjaved.ESLGaming;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jsan on 6/25/2016.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class CupList {

    @JsonProperty("id")
    private int id;

    @JsonProperty("type")
    private String type;

    @JsonProperty("teamSize")
    private int teamSize;

    @JsonProperty("contestants")
    private Contestant contestant;

    public Contestant getContestant() {
        return contestant;
    }

    public int getTeamSize() {
        return teamSize;
    }

    public String getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTeamSize(int teamSize) {
        this.teamSize = teamSize;
    }

    public void setContestant(Contestant contestants) {
        this.contestant = contestants;
    }


}
