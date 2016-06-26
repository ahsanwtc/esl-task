package me.aboutjaved.ESLGaming;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *  Deserialization class for Ranking
 *
 * Created by jsan on 6/25/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Ranking {

    /**
     * Position of the team.
     */
    @JsonProperty("position")
    private int position;

    /**
     * Team associated with the position.
     */
    @JsonProperty("team")
    private Team team;

    public int getPosition() {
        return position;
    }

    public Team getTeam() {
        return team;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "Ranking{" +
                "position=" + position +
                ", team=" + team +
                '}';
    }
}