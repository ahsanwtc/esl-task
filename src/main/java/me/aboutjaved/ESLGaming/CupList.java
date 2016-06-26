package me.aboutjaved.ESLGaming;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *  Deserialization class for cups.
 *
 * Created by jsan on 6/25/2016.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class CupList {

    /**
     * Id of the Cup.
     */
    @JsonProperty("id")
    private int id;

    /**
     * Type of the cup.
     */
    @JsonProperty("type")
    private String type;

    /**
     * TeamSize
     */
    @JsonProperty("teamSize")
    private int teamSize;

    /**
     * Information regarding the contestants.
     */
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
